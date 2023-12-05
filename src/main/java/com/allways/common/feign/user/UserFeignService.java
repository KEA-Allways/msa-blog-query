package com.allways.common.feign.user;

import java.util.List;

import com.allways.common.feign.user.dto.*;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFeignService {
	private final UserFeignClient userFeignClient;

	public List<UserByPostResponse> queryUsersByPost(List<UserByPostFeignRequest> userFeignList) {
		List<UserByPostResponse> userByPostResponse = userFeignClient.queryUsersByPost(userFeignList);
		return userByPostResponse;
	}

	public List<UserByReplyResponse> queryUsersByReply(List<UserByReplyFeignRequest> userFeignList) {
		List<UserByReplyResponse> userByReplyResponse = userFeignClient.queryUsersByReply(userFeignList);
		return userByReplyResponse;
	}

	public UserFeignResponse queryUser(Long userSeq) {
		return userFeignClient.queryUser(userSeq);
	}
}