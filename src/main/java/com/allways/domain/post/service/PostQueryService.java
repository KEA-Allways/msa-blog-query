package com.allways.domain.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allways.common.feign.user.UserFeignResponse;
import com.allways.common.feign.user.UserFeignService;
import com.allways.common.feign.user.UserByPostFeignRequest;
import com.allways.common.feign.user.UserByPostResponse;
import com.allways.domain.post.dto.PostCardResponse;
import com.allways.domain.post.dto.PostDetailResponse;
import com.allways.domain.post.dto.PostMainResponse;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.exception.PostNotFoundException;
import com.allways.domain.post.repository.PostQueryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostQueryService {

	private final PostQueryRepository postQueryRepository;
	private final UserFeignService userFeignService;

	@Transactional
	public List<PostCardResponse> findMainPosts() {

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
						userByPostResponse.getNickname(), thumbImg, profileImg));
				}
			}
		}

		return postCardResponse;
	}

	@Transactional
	public PostDetailResponse readPostDetail(Long postSeq) {
		Post post = postQueryRepository.findById(postSeq).orElseThrow(PostNotFoundException::new);
		UserFeignResponse userFeignResponse = userFeignService.queryUser(post.getUserSeq());

		PostDetailResponse postDetailResponse = new PostDetailResponse(
			post,userFeignResponse
		);

		return postDetailResponse;
	}


	@Transactional
	public Page<Post> findPostsByUser(Long userSeq, Pageable pageable) {
		Page<Post> posts = postQueryRepository.findAllByUserSeq(userSeq, pageable);
		return posts;
	}


	@Transactional
	public Page<Post> readPostsByCategory(Long userSeq,Long categorySeq,Pageable pageable) {

		Page<Post> posts = postQueryRepository.findAllByUserSeqAndCategory(userSeq,categorySeq,pageable);
		// UserFeignResponse userFeignResponse = userFeignService.queryUser(userSeq);
		return posts;

	}


}
