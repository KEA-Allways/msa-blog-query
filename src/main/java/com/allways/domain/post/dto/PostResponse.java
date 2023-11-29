package com.allways.domain.post.dto;

import java.time.LocalDateTime;

import com.allways.common.feign.filedb.FileFeignResponse;
import com.allways.common.feign.user.dto.UserFeignResponse;
import com.allways.domain.post.entity.Post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostResponse {

	private Long postSeq;
	private String postTitle;
	private String postContent;
	private Integer postView;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDateTime postDate;

	private String categoryName;
	private String themeName;
	private Long themeSeq;

	private String userId;
	private Long userSeq;
	private String nickname;


	private String thumbImg;
	private String profileImg;

	public PostResponse(Post post, UserFeignResponse userFeignResponse, FileFeignResponse fileFeignResponse) {
		this.postSeq = post.getPostSeq();
		this.postTitle = post.getPostTitle();
		this.postContent = post.getPostContent();
		this.postDate = post.getCreatedAt();
		this.postView = post.getPostView();
		this.categoryName = post.getCategory().getCategoryName();
		this.themeName = post.getCategory().getTheme().getThemeName();
		this.themeSeq = post.getCategory().getTheme().getThemeSeq();

		this.userSeq = post.getUserSeq();
		this.userId = userFeignResponse.getUserId();
		this.nickname = userFeignResponse.getNickname();

		this.profileImg = fileFeignResponse.getProfileImg();
		this.thumbImg = fileFeignResponse.getThumbImg();
	}


}
