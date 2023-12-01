package com.allways.common.feign.user;

import java.util.List;

import com.allways.common.feign.user.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msa-user-query", url ="${env.user-feign-url}" )
public interface UserFeignClient {

	@PostMapping
	List<UserByPostResponse> queryUsersByPost(@RequestBody List<UserByPostFeignRequest> userByPostFeignRequestList);

	@PostMapping("/reply")
	List<UserByReplyResponse> queryUsersByReply(@RequestBody List<UserByReplyFeignRequest> userByReplyFeignRequest);

	@GetMapping("/{userSeq}")
	UserFeignResponse queryUser(@PathVariable("userSeq") Long userSeq);
}