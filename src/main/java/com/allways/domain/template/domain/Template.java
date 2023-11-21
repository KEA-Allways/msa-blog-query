package com.allways.domain.template.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.allways.common.EntityDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Template extends EntityDate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long templateSeq;

	@Column
	private String templateTitle;

	@Column
	@Lob
	private String templateContent;

	@Column(nullable = false)
	private Long userSeq;

	// 안쓰면 지워도 됨 나중에 필요할까봐 냅둠
	public Template(String templateTitle, String templateContent, Long userSeq) {
		this.templateTitle = templateTitle;
		this.templateContent = templateContent;
		this.userSeq = userSeq;
	}
}
