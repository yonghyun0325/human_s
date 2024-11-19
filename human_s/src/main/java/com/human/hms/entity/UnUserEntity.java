package com.human.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "un_member")
public class UnUserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동증가
	@Column(name = "un_idx", nullable = false)
	private int unIdx;//비회원번호
	
	@Column(name = "un_name", nullable = false)
	private String unName;
	
	@Column(name = "un_phone", nullable = false)
	private String unPhone;
	
	@Column(name = "un_birth", nullable = false)
	private String unBirth;
	
	@Column(name = "un_email", nullable = false)
	private String unEmail;
	
	@Column(name = "un_post", nullable = false)
	private String unPost;
	
	@Column(name = "un_add1", nullable = false)
	private String unAdd1;
	
	@Column(name = "un_add2", nullable = false)
	private String unAdd2;
	
	@ManyToOne
	@JoinColumn(name = "o_idx", updatable = false)
	private OrderListEntity orderListEntity;

	@Builder
	public UnUserEntity(String unName, String unPhone, String unBirth, 
			String unEmail, String unPost, String unAdd1, String unAdd2) {
		
		this.unName = unName;
		this.unPhone = unPhone;
		this.unBirth = unBirth;
		this.unEmail = unEmail;
		this.unPost = unPost;
		this.unAdd1 = unAdd1;
		this.unAdd2 = unAdd2;
				
	}
	
	public void updateUnidx(int unIdx) {
		this.unIdx = unIdx;
	}
	
}
