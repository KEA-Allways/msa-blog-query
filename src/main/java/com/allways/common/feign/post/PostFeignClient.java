package com.allways.common.feign.post;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "msa-post-command", url ="http://localhost:8085/api/feign/post" )
public interface PostFeignClient {

	@PostMapping("/post-view/{postView}")
	void increasePostView(@PathVariable("postView") Long postView);

}
