package com.allways.common.feign.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserByPostFeignRequest {
	private Long postSeq;
	private Long userSeq;
}