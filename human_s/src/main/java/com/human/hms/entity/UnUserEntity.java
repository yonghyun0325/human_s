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
	
	@Column(name = "un_email", nullable = false)
	private String unEmail;
		
	@ManyToOne
	@JoinColumn(name = "o_idx", updatable = false)
	private OrderListEntity orderListEntity;

	@Builder
	public UnUserEntity(String unName, String unPhone, String unEmail) {
		
		this.unName = unName;
		this.unPhone = unPhone;
		this.unEmail = unEmail;

	}
	
	public void updateUnidx(int unIdx) {
		this.unIdx = unIdx;
	}
	
	public void updateOrderList(OrderListEntity orderListEntity) {
		this.orderListEntity = orderListEntity;
	}
	
}
