package com.allways.domain.category.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.allways.common.EntityDate;
import com.allways.domain.theme.entity.Theme;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends EntityDate  {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categorySeq;

	@Column
	private String categoryName;

	@Column
	private Long categoryOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theme_seq")
	private Theme theme;


}
