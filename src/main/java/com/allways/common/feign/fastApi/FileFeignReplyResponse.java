package com.allways.common.feign.fastApi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileFeignReplyResponse {
    private Long replySeq;
    private Long userSeq;
    private String profileImg;

}
