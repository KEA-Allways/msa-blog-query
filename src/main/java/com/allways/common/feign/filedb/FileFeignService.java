package com.allways.common.feign.filedb;

import com.allways.common.feign.user.dto.UserByPostFeignRequest;
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
        return fileFeignClient.queryImageUrlByPost(urlByPostFeignRequestList);
    }

    public FileFeignResponse queryThumbnailUrlByPost(UserByPostFeignRequest fileFeignRequest){
        return fileFeignClient.queryThumbnailUrlByPost(fileFeignRequest);
    }


}
