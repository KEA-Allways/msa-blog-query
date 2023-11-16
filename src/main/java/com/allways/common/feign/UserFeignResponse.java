package com.allways.common.feign;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserFeignResponse {
	private Long postSeq;
	private Long userSeq;
	private String userId;
	private String nickname;
}
