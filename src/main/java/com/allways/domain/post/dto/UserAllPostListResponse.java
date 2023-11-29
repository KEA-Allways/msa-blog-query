package com.allways.domain.post.dto;

import java.time.LocalDateTime;

import com.allways.domain.post.entity.Post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAllPostListResponse {

	private Long postSeq;
	private String postTitle;
	private String postContent;
	private Integer postView;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDateTime postDate;
	private String categoryName;
	private String themeName;


	public static UserAllPostListResponse toDto(Post post) {
		return UserAllPostListResponse.builder()
			.postSeq(post.getPostSeq())
			.postTitle(post.getPostTitle())
			.postContent(post.getPostContent())
			.postDate(post.getCreatedAt())
			.postView(post.getPostView())
			.categoryName(post.getCategory().getCategoryName())
			.themeName(post.getCategory().getTheme().getThemeName())
			.build();
	}



}
