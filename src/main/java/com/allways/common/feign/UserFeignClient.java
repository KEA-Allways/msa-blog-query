package com.allways.common.feign;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msa-user-query", url ="http://localhost:8084/api/users/feign" )
public interface UserFeignClient {
	@PostMapping
	List<UserFeignResponse> queryUserFeignDto(@RequestBody List<UserFeignRequest> userFeignRequestList);
}