package com.allways.domain.post.dto;

import com.allways.common.feign.user.UserByPostFeignResponse;
import com.allways.domain.post.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostsMainResponse {

	private Long postSeq;
	private String postTitle;
	private String subtitle;
	private Integer postView;
	private String categoryName;
	private String themeName;

	private Long userSeq;
	private String nickname;

	public PostsMainResponse(Post post, UserByPostFeignResponse userByPostFeignResponse) {
		this.postSeq = post.getPostSeq();
		this.postTitle = post.getPostTitle();
		this.subtitle =  generateSubtitleFromContent(post.getPostContent(),20);
		this.postView = post.getPostView();
		this.categoryName = post.getCategory().getCategoryName();
		this.themeName = post.getCategory().getTheme().getThemeName();
		this.postView = post.getPostView();

		this.userSeq = userByPostFeignResponse.getUserSeq();
		this.nickname = userByPostFeignResponse.getNickname();
	}

	private String generateSubtitleFromContent(String content, int maxLength) {
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
