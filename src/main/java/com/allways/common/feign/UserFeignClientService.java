package com.allways.common.feign;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserFeignClientService {
	private final UserFeignClient userFeignClient;

	//인터페이스 구체화
	public List<UserFeignResponse> queryUserFeignDto(List<UserFeignRequest> userFeignList) {
		List<UserFeignResponse> userFeignResponse = userFeignClient.queryUserFeignDto(userFeignList);
		return userFeignResponse;
	}
}
