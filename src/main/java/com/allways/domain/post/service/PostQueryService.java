package com.allways.domain.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allways.common.feign.UserFeignClientService;
import com.allways.common.feign.UserFeignRequest;
import com.allways.common.feign.UserFeignResponse;
import com.allways.domain.post.dto.PostFindMainResponse;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.repository.PostQueryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostQueryService {

	private final PostQueryRepository postQueryRepository;
	private final UserFeignClientService userFeignClientService;

	@Transactional
	public List<PostFindMainResponse> findMainPosts() {

		List<Post> posts = postQueryRepository.findTop10ByOrderByCreatedAtAsc();

		List<UserFeignRequest> userFeignList = new ArrayList<>();

		for (Post post : posts) {
			UserFeignRequest userFeignRequest = new UserFeignRequest(post.getPostSeq(), post.getUserSeq());
			userFeignList.add(userFeignRequest);
		}

		//user feign userid, nickname
		List<UserFeignResponse> userFeignResponseList = userFeignClientService.queryUserFeignDto(userFeignList);

		//file feign 추가하기
		// thumbImg, profileImg
		String thumbImg = "https://allways-image.s3.ap-northeast-2.amazonaws.com/test-img/main-img/thailand.jpg";
		String profileImg = "https://allways-image.s3.ap-northeast-2.amazonaws.com/test-img/main-img/thailand.jpg";

		List<PostFindMainResponse> postFindMainResponse = new ArrayList<>();

		for (Post post : posts) {
			for (UserFeignResponse userFeignResponse : userFeignResponseList) {
				if (post.getPostSeq() == userFeignResponse.getPostSeq()) {
					postFindMainResponse.add(new PostFindMainResponse(post, userFeignResponse.getUserId(),
						userFeignResponse.getNickname(), thumbImg, profileImg));
				}
			}
		}

		return postFindMainResponse;
	}


	@Transactional
	public Page<Post> findAllPosts(Long userSeq, Pageable pageable) {
		Page<Post> posts = postQueryRepository.findAllByUserSeq(userSeq, pageable);
		return posts;
	}
}
