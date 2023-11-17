package com.allways.common.feign.user;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFeignService {
	private final UserFeignClient userFeignClient;

	//인터페이스 구체화
	public List<UserByPostResponse> queryUsersByPost(List<UserByPostFeignRequest> userFeignList) {
		List<UserByPostResponse> userByPostResponse = userFeignClient.queryUsersByPost(userFeignList);
		return userByPostResponse;
	}

	public UserFeignResponse queryUser(Long userSeq) {
		return userFeignClient.queryUser(userSeq);
	}


}
