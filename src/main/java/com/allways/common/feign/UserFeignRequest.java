package com.allways.common.feign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserFeignRequest {
	private Long postSeq;
	private Long userSeq;
}
