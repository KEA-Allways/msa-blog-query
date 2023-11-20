package com.allways.domain.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allways.common.feign.post.PostFeignService;
import com.allways.common.feign.user.dto.UserFeignResponse;
import com.allways.common.feign.user.UserFeignService;
import com.allways.common.feign.user.dto.UserByPostFeignRequest;
import com.allways.common.feign.user.dto.UserByPostResponse;
import com.allways.domain.post.dto.MngtPostResponse;
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

	@Transactional
	public List<PostCardResponse> readMainPosts() {

		List<Post> posts = postQueryRepository.findTop10ByOrderByCreatedAtAsc();

		List<UserByPostFeignRequest> userFeignList = new ArrayList<>();

		for (Post post : posts) {
			UserByPostFeignRequest userByPostFeignRequest = new UserByPostFeignRequest(post.getPostSeq(), post.getUserSeq());
			userFeignList.add(userByPostFeignRequest);
		}

		//user feign userid, nickname
		List<UserByPostResponse> userByPostResponseList = userFeignService.queryUsersByPost(userFeignList);

		//file feign 추가하기
		// thumbImg, profileImg
		String thumbImg = "https://allways-image.s3.ap-northeast-2.amazonaws.com/test-img/main-img/thailand.jpg";
		String profileImg = "https://allways-image.s3.ap-northeast-2.amazonaws.com/test-img/main-img/thailand.jpg";

		List<PostCardResponse> postCardResponse = new ArrayList<>();

		for (Post post : posts) {
			for (UserByPostResponse userByPostResponse : userByPostResponseList) {
				if (post.getPostSeq() == userByPostResponse.getPostSeq()) {
					postCardResponse.add(new PostCardResponse(post, userByPostResponse.getUserId(),
						userByPostResponse.getNickname(), profileImg));
				}
			}
		}

		return postCardResponse;
	}

	@Transactional
	public PostResponse readPost(Long postSeq) {
		Post post = postQueryRepository.findById(postSeq).orElseThrow(PostNotFoundException::new);

		UserFeignResponse userFeignResponse = userFeignService.queryUser(post.getUserSeq());
		postFeignService.increasePostView(postSeq);

		PostResponse postResponse = new PostResponse(
			post,userFeignResponse
		);

		return postResponse;
	}


	@Transactional
	public Page<MngtPostResponse> readAllPosts(Long userSeq, Pageable pageable) {

		//프론트 페이지는 1번부터 백엔드에서 pageable 객체는 0번 인덱스 부터 시작하기 때문에 프론트에서 넘어오는 페이지 값의 1을 빼야한다.!
		pageable = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize());
		Page<Post> posts = postQueryRepository.findAllByUserSeq(userSeq, pageable);
		Page<MngtPostResponse> postResponse = posts.map(m -> MngtPostResponse.toDto(m));

		return postResponse;
	}


	@Transactional
	public Page<PostCardResponse> readPostsInCategory(Long userSeq,Long categorySeq,Pageable pageable) {


		UserFeignResponse userFeignResponse = userFeignService.queryUser(userSeq);
		String userId = userFeignResponse.getUserId();
		String nickname = userFeignResponse.getNickname();

		//msa-file-query open feign
		String profileImg = "open fegin";

		pageable = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize());
		Page<Post> posts = postQueryRepository.findAllByUserSeqAndCategory_CategorySeqOrderByCreatedAt(userSeq,categorySeq,pageable);
		Page<PostCardResponse> postResponse = posts.map(m -> PostCardResponse.toResponse(m,userId,nickname,profileImg));

		return postResponse;

	}


}
