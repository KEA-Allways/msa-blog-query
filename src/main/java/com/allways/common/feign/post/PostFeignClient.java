package com.allways.common.feign.post;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// @FeignClient(name = "msa-post-command", url ="${env.post-command-feign-url}" )
@FeignClient(name = "blog-command-service")
public interface PostFeignClient {

	@PostMapping("api/feign/post/post-view/{postView}")
	void increasePostView(@PathVariable("postView") Long postView);

}