package com.allways.domain.post.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.allways.common.EntityDate;
import com.allways.domain.category.domain.Category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends EntityDate  {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postSeq;

	@Column(nullable = false)
	private String postTitle;

	@Column(nullable = false)
	@Lob
	private String postContent;

	@Column
	private Integer postView;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_seq", nullable = false)
	// @OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;

	@Column
	private Long userSeq;

}
