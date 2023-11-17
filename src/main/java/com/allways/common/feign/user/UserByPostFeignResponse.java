package com.allways.common.feign.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserByPostFeignResponse {

	private Long userSeq;
	private String userId;
	private String password;
	private String nickname;
	private String email;
	private String profileImgSeq;

}
