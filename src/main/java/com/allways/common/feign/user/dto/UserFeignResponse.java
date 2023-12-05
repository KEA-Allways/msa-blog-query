package com.allways.common.feign.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserFeignResponse {
	private String userId;
	private String nickname;
	private String email;
	private String profileImgSeq;
}