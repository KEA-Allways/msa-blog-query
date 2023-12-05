package com.allways.common.feign.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserByReplyFeignRequest {
	private Long replySeq;
	private Long userSeq;
}