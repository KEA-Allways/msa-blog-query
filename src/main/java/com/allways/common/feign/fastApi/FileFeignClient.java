package com.allways.common.feign.fastApi;


import com.allways.common.feign.user.dto.UserByPostFeignRequest;
import com.allways.common.feign.user.dto.UserByReplyFeignRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// @FeignClient(name = "fastApi", url = "${env.file-feign-url}")
@FeignClient(name = "file-query-service")
public interface FileFeignClient {

    @PostMapping("api/feign/post")
    FileFeignResponse queryImageUrlByPost(@RequestBody UserByPostFeignRequest fileFeignRequest);

    @PostMapping("api/feign/post/list")
    List<FileFeignResponse> queryImageUrlListByPost(@RequestBody List<UserByPostFeignRequest> urlByPostFeignRequestList);

    @PostMapping("api/feign/reply/list")
    List<FileFeignReplyResponse> queryImageUrlByReply(@RequestBody List<UserByReplyFeignRequest> userByReplyFeignRequestList);

}

