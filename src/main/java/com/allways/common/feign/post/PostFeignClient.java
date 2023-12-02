package com.allways.common.feign.post;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "msa-blog-command", url ="${env.post-command-feign-url}" )
public interface PostFeignClient {

	@PostMapping("/post/post-view/{postView}")
	void increasePostView(@PathVariable("postView") Long postView);

}
