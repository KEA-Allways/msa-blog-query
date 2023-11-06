package com.allways.domain.theme.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.allways.common.EntityDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theme extends EntityDate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long themeSeq;

	@Column
	private String themeName;

	@Column
	private Long themeOrder;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "user_seq")
	// private User user;

	//이미지 추가


}
