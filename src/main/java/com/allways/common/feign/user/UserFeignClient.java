package com.allways.common.feign.user;

import java.util.List;

import com.allways.common.feign.user.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// @FeignClient(name = "msa-user-query", url ="${env.user-feign-url}" )
@FeignClient(name = "user-query-service")
public interface UserFeignClient {

	@PostMapping("api/users/feign")
	List<UserByPostResponse> queryUsersByPost(@RequestBody List<UserByPostFeignRequest> userByPostFeignRequestList);

	@PostMapping("api/users/feign/reply")
	List<UserByReplyResponse> queryUsersByReply(@RequestBody List<UserByReplyFeignRequest> userByReplyFeignRequest);

	@GetMapping("api/users/feign/{userSeq}")
	UserFeignResponse queryUser(@PathVariable("userSeq") Long userSeq);
}