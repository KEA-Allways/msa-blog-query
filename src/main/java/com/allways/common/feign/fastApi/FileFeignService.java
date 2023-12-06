package com.allways.common.feign.fastApi;

import com.allways.common.feign.user.dto.UserByPostFeignRequest;
import com.allways.common.feign.user.dto.UserByReplyFeignRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileFeignService {
    private final FileFeignClient fileFeignClient;
    @Autowired
    public FileFeignService(FileFeignClient fileFeignClient) {

        this.fileFeignClient = fileFeignClient;
    }

    public List<FileFeignResponse> queryImageUrlByPost(List<UserByPostFeignRequest> urlByPostFeignRequestList) {
        return fileFeignClient.queryImageUrlListByPost(urlByPostFeignRequestList);
    }

    public FileFeignResponse queryThumbnailUrlByPost(UserByPostFeignRequest fileFeignRequest){
        return fileFeignClient.queryImageUrlByPost(fileFeignRequest);
    }

    public List<FileFeignReplyResponse> queryImageUrlByReply(List<UserByReplyFeignRequest> userByReplyFeignRequestList) {
        return fileFeignClient.queryImageUrlByReply(userByReplyFeignRequestList);
    }
}
