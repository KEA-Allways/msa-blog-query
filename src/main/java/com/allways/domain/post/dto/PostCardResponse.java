package com.allways.domain.post.dto;

import java.time.LocalDateTime;

import com.allways.common.Constants;
import com.allways.domain.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCardResponse {
	private Long postSeq;
	private String postTitle;
	private String subTitle;

	private String userId;
	private String nickName;

	private LocalDateTime postDate;

	private String profileImg;


	// private
	public PostCardResponse(Post post,String userId, String nickName, String profileImg) {
		this.postSeq = post.getPostSeq();
		this.postTitle = post.getPostTitle();
		this.postDate = post.getCreatedAt();
		this.subTitle = generateSubtitleFromContent(post.getPostContent(), Constants.SUBTITLE_LENGTH);
		this.userId = userId;
		this.nickName = nickName;
		this.profileImg = profileImg;
	}

	public static PostCardResponse toResponse(Post post,String userId, String nickName, String profileImg) {
		return PostCardResponse.builder()
			.postSeq(post.getPostSeq())
			.postTitle(post.getPostTitle())
			.postDate(post.getCreatedAt())
			.subTitle(generateSubtitleFromContent(post.getPostContent(), Constants.SUBTITLE_LENGTH))
			.userId(userId)
			.nickName(nickName)
			.profileImg(profileImg)
			.build();
	}

	public static String generateSubtitleFromContent(String content, int maxLength) {
		if (content == null) {
			return null; // 입력이 null인 경우에 대한 처리
		}

		if (content.length() <= maxLength) {
			return content + "..."; // 문자열의 길이가 maxLength 이하인 경우 그대로 반환
		} else {
			return content.substring(0, maxLength) + "..."; // 문자열을 maxLength까지 자르고 반환
		}
	}
}
