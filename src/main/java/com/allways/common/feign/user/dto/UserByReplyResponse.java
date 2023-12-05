package com.allways.common.feign.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserByReplyResponse {
	private Long replySeq;
	private Long userSeq;
	private String userId;
	private String nickname;
}