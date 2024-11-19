package com.human.hms.entity;

import java.util.Date;

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
@Table(name = "orderlist")
public class OrderListEntity {

	@Id
	@Column(name = "o_idx", nullable = false)
	public String orIdx;//주문번호
	
	@Column(name = "o_userpoint", nullable = false)
	public String orUserPoint;//적립금 사용내역
	
	@Column(name = "o_pay_amount", nullable = false)
	public String  orPayAmount;//결제금액
	
	@Column(name = "o_pay_type", nullable = false)
	public String  orPayType; //결제구분
    
	@Column(name = "o_status", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT '1'")
    private String orStatus; // 주문 상태 (1: "결제완료", 2: "배송중", 3: "완료")
	
    @Column(name = "o_pay_date", columnDefinition="DATETIME DEFAULT NOW()")
	public Date orPayDate;//주문일
	
	@Column(name = "o_post_date", nullable = false)
	public String  orPostDate;//배송일자
	
	@Column(name = "o_name", nullable = false)
    private String orName; //상품명

    @Column(name = "o_count", nullable = false)
    private String orCount; //개수

	@ManyToOne
	@JoinColumn(name="user_idx", updatable = false)
	private UserEntity userEntity;
	
	@ManyToOne
	@JoinColumn(name="pdt_idx", updatable = false)
	private ProductEntity productEntity;
	
	@Builder
	public OrderListEntity(String orIdx,String orUserPoint, String orPayAmount, 
			String  orPayType, String  orStatus, String orPostDate, String orName, String orCount) {
		this.orIdx = orIdx;
		this.orUserPoint = orUserPoint;
		this.orPayAmount = orPayAmount;
		this.orPayType = orPayType;
		this.orStatus = orStatus;
		this.orPostDate = orPostDate;
		this.orName = orName;
		this.orCount = orCount;
		this.orPayDate = new Date();
	}
		
	//UserEntity필드에 대한 값의 변경 메소드
	public void updateUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	//ProductEntity필드에 대한 값의 변경 메소드
	public void updateProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
	
}
