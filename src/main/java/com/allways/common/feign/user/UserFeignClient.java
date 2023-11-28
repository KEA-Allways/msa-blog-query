package com.allways.common.feign.user;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.allways.common.feign.user.dto.UserByPostFeignRequest;
import com.allways.common.feign.user.dto.UserByPostResponse;
import com.allways.common.feign.user.dto.UserFeignResponse;

@FeignClient(name = "msa-user-query", url ="http://localhost:8084/api/users/feign" )
public interface UserFeignClient {

	@PostMapping
	List<UserByPostResponse> queryUsersByPost(@RequestBody List<UserByPostFeignRequest> userByPostFeignRequestList);

	@GetMapping("/{userSeq}")
	UserFeignResponse queryUser(@PathVariable("userSeq") Long userSeq);
}