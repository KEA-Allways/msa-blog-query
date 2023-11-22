package com.allways.common.feign.post;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostFeignService {

	private final PostFeignClient postFeignClient;

	public void increasePostView(Long userSeq) {
		postFeignClient.increasePostView(userSeq);
	}
}
