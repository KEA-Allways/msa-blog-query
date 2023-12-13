package com.allways.domain.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.allways.common.feign.fastApi.FileFeignResponse;
import com.allways.common.feign.fastApi.FileFeignService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allways.common.feign.post.PostFeignService;
import com.allways.common.feign.user.dto.UserFeignResponse;
import com.allways.common.feign.user.UserFeignService;
import com.allways.common.feign.user.dto.UserByPostFeignRequest;
import com.allways.common.feign.user.dto.UserByPostResponse;
import com.allways.domain.post.dto.UserAllPostListResponse;
import com.allways.domain.post.dto.PostCardResponse;
import com.allways.domain.post.dto.PostResponse;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.exception.PostNotFoundException;
import com.allways.domain.post.repository.PostQueryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostQueryService {

	private final PostQueryRepository postQueryRepository;
	private final UserFeignService userFeignService;
	private final PostFeignService postFeignService;
	private final FileFeignService fileFeignService;

	@Transactional
	public List<PostCardResponse> readMainPosts() {

		List<Post> posts = postQueryRepository.findTop12ByOrderByCreatedAtDesc();

		for(Post post: posts){
			System.out.println(post.getPostSeq());
		}
		System.out.println("11111111111111111");

		List<UserByPostFeignRequest> userFeignList = new ArrayList<>(); // [postSeq, userSeq] 10개

		for (Post post : posts) {
			UserByPostFeignRequest userByPostFeignRequest = new UserByPostFeignRequest(post.getPostSeq(), post.getUserSeq());
			userFeignList.add(userByPostFeignRequest);
		}

		// postSeq userSeq userId nickname
		//user feign userid, nickname
		List<UserByPostResponse> userByPostResponseList = userFeignService.queryUsersByPost(userFeignList);

		//post seq 와 user seq 보내기
		List<FileFeignResponse> fileFeignResponsesList = fileFeignService.queryImageUrlByPost(userFeignList);

		System.out.println(fileFeignResponsesList);
		List<PostCardResponse> postCardResponse = new ArrayList<>();

		for (Post post : posts) {

			for (UserByPostResponse userByPostResponse : userByPostResponseList) {
				if (Objects.equals(post.getPostSeq(), userByPostResponse.getPostSeq())) {
					postCardResponse.add(new PostCardResponse(post, userByPostResponse.getUserId(),
							userByPostResponse.getNickname()));
				}
			}

			for (FileFeignResponse fileFeignResponse : fileFeignResponsesList) {
				if (Objects.equals(post.getPostSeq(), fileFeignResponse.getPostSeq())) {

					for (PostCardResponse cardResponse : postCardResponse) {
						if (Objects.equals(cardResponse.getPostSeq(), post.getPostSeq())) {
							cardResponse.setThumbImg(fileFeignResponse.getThumbImg()); // Set the thumb image
							cardResponse.setProfileImg(fileFeignResponse.getProfileImg()); // Set the user image
						}
					}
				}
			}
		}




		return postCardResponse;
	}

	@Transactional
	public PostResponse readPost(Long postSeq) {
		Post post = postQueryRepository.findById(postSeq).orElseThrow(PostNotFoundException::new);

		UserFeignResponse userFeignResponse = userFeignService.queryUser(post.getUserSeq());
		UserByPostFeignRequest fileFeignRequest = new UserByPostFeignRequest(postSeq, post.getUserSeq());
		FileFeignResponse fileFeignResponse = fileFeignService.queryThumbnailUrlByPost(fileFeignRequest);
		postFeignService.increasePostView(postSeq);

		PostResponse postResponse = new PostResponse(post, userFeignResponse, fileFeignResponse);

		return postResponse;
	}

	@Transactional
	public PostResponse readPostDetail(Long postSeq) {
		Post post = postQueryRepository.findById(postSeq).orElseThrow(PostNotFoundException::new);

		UserFeignResponse userFeignResponse = userFeignService.queryUser(post.getUserSeq());
		UserByPostFeignRequest fileFeignRequest = new UserByPostFeignRequest(postSeq, post.getUserSeq());
		FileFeignResponse fileFeignResponse = fileFeignService.queryThumbnailUrlByPost(fileFeignRequest);

		PostResponse postResponse = new PostResponse(post, userFeignResponse, fileFeignResponse);

		return postResponse;
	}


	@Transactional
	public Page<UserAllPostListResponse> readAllPosts(Long userSeq, Pageable pageable) {

		//프론트 페이지는 1번부터 백엔드에서 pageable 객체는 0번 인덱스 부터 시작하기 때문에 프론트에서 넘어오는 페이지 값의 1을 빼야한다.!
		pageable = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize());
		Page<Post> posts = postQueryRepository.findAllByUserSeq(userSeq, pageable);
		Page<UserAllPostListResponse> postResponse = posts.map(m -> UserAllPostListResponse.toDto(m));

		return postResponse;
	}


	@Transactional
	public Page<PostCardResponse> readPostsInCategory(Long userSeq,Long categorySeq,Pageable pageable) {


		UserFeignResponse userFeignResponse = userFeignService.queryUser(userSeq);
		String userId = userFeignResponse.getUserId();
		String nickname = userFeignResponse.getNickname();

		//msa-file-query open feign

		pageable = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize());
		Page<Post> posts = postQueryRepository.findAllByUserSeqAndCategory_CategorySeqOrderByCreatedAt(userSeq,categorySeq,pageable);


		List<UserByPostFeignRequest> userFeignList = new ArrayList<>(); // [postSeq, userSeq] 10개

		for (Post post : posts) {
			UserByPostFeignRequest userByPostFeignRequest = new UserByPostFeignRequest(post.getPostSeq(), userSeq);
			userFeignList.add(userByPostFeignRequest);
		}

		//post seq 와 user seq 보내기
		List<FileFeignResponse> fileFeignResponsesList = fileFeignService.queryImageUrlByPost(userFeignList);
		System.out.println(fileFeignResponsesList);
		List<PostCardResponse> postCardResponse = new ArrayList<>();

		for (Post post : posts) {
			for (FileFeignResponse fileFeignResponse : fileFeignResponsesList) {
				if(Objects.equals(post.getPostSeq(), fileFeignResponse.getPostSeq())){
					String postProfileImg = fileFeignResponse.getProfileImg();
					String postThumbImg = fileFeignResponse.getThumbImg();
					PostCardResponse newCardResponse = PostCardResponse.toResponse(post, userId, nickname, postProfileImg, postThumbImg);
					postCardResponse.add(newCardResponse);
				}

			}
		}

		Page<PostCardResponse> postResponse = new PageImpl<>(postCardResponse);


		return postResponse;

	}


}