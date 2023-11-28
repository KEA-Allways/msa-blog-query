package com.allways.common.feign.filedb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileFeignResponse {
    private Long postSeq;
    private Long userSeq;
    private String thumbImg;
    private String profileImg;
}
