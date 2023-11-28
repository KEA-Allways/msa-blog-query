package com.allways.common.feign.filedb;


import com.allways.common.feign.user.dto.UserByPostFeignRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "fastApi", url = "http://localhost:8001")
public interface FileFeignClient {

    @PostMapping("/receive_thumbnail_and_profile")
    List<FileFeignResponse> queryImageUrlByPost(@RequestBody List<UserByPostFeignRequest> urlByPostFeignRequestList);

    @PostMapping("/receive_thumbnail")
    FileFeignResponse queryThumbnailUrlByPost(@RequestBody UserByPostFeignRequest fileFeignRequest);


}

