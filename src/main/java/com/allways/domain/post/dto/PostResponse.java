package com.allways.domain.post.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.allways.common.feign.user.UserByPostFeignResponse;
import com.allways.common.feign.user.UserFeignResponse;
import com.allways.domain.category.domain.Category;
import com.allways.domain.post.entity.Post;

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
	private LocalDateTime postDate;

	private String categoryName;
	private String themeName;

	private String userId;
	private Long userSeq;
	private String nickname;


	private String thumbImg;
	private String profileImg;

	public PostResponse(Post post, UserFeignResponse userFeignResponse) {
		this.postSeq = post.getPostSeq();
		this.postTitle = post.getPostTitle();
		this.postContent = post.getPostContent();
		this.postDate = post.getCreatedAt();
		this.postView = post.getPostView();
		this.categoryName = post.getCategory().getCategoryName();
		this.themeName = post.getCategory().getTheme().getThemeName();

		this.userSeq = post.getUserSeq();
		this.userId = userFeignResponse.getUserId();
		this.nickname = userFeignResponse.getNickname();

		//수정
		this.profileImg = userFeignResponse.getProfileImgSeq();
		this.thumbImg = getThumbImg();
	}


}
