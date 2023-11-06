package com.allways.domain.reply.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.allways.common.EntityDate;
import com.allways.domain.post.domain.Post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends EntityDate  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long replySeq;

	@Column
	private String replyContent;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_seq", nullable = false)
	private Post post;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "user_seq", nullable = false)
	// private User user;


}
