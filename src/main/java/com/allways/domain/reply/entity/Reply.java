package com.allways.domain.reply.entity;

import javax.persistence.*;

import com.allways.common.EntityDate;
import com.allways.domain.post.entity.Post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends EntityDate  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long replySeq;

	@Column
	private String replyContent;

	@Column(nullable = false)
	private boolean deleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_seq", nullable = false)
	private Post post;

	//추후에 주석 해제 필요
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "user_seq", nullable = false)
	// private User user;

	//user 없이 임시로 둔 것
	@Column
	private Long userSeq;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_seq")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Reply parent;

//	@OneToMany(mappedBy = "parent")
//	private List<Reply> children = new ArrayList<>();

	public Reply(String content, Post post, Long UserSeq) {
		this.replyContent = content;
		//this.member = member;	//추후에 주석 해제 필요
		this.post = post;
		this.userSeq = UserSeq;
		this.parent = parent;
//		this.deleted = false;

	}
}
