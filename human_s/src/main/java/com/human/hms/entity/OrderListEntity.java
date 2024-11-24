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
import javax.sound.midi.Soundbank;

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
	public Date  orPostDate;//배송일자
	
	@Column(name = "o_name", nullable = false)
    private String orName; //상품명

    @Column(name = "o_count", nullable = false)
    private String orCount; //개수

	@Column(name = "o_post", nullable = false)
	private String orPost;//도로명주소
	
	@Column(name = "o_add1", nullable = false)
	private String orAdd1;//상세주소1
	
	@Column(name = "o_add2", nullable = false)
	private String orAdd2;//상세주소2
	
	@Column(name = "o_rechuman", nullable = false)
	private String orRecHuman;//받는사람
    
	@Column(name = "o_recPhone", nullable = false)
	private  String orRecPhone;//받는사람 전화번호
    
	@Column(name = "o_message")
	private String orMessage;//배송메시지
	
	@Column(name = "o_cardnum")
	private String orCardNum; //카드번호
	
	@Column(name = "o_cvc" )
	private String orCvc;//카드 cvc번호
	
	@Column(name = "o_bankname")
	private String orBankName;//은행명
	
	@Column(name = "o_backnum")
	private String orBackNum;//계좌번호
	
	@ManyToOne
	@JoinColumn(name="user_idx", updatable = false)
	private UserEntity userEntity;
	
	
	@ManyToOne
	@JoinColumn(name="pdt_idx", updatable = false)
	private ProductEntity productEntity;
	
	@Builder
	public OrderListEntity(String orIdx,String orUserPoint, String orPayAmount, 
			String  orPayType, String  orStatus, String orPostDate, 
			String orName, String orCount, 
			String orPost, String orAdd1, String orAdd2,
			String orRecHuman, String orRecPhone, String orMessage,
			String orCardNum, String orCvc, String orBackNum,
			String orBankName) {
		this.orIdx = orIdx;
		this.orUserPoint = orUserPoint;
		this.orPayAmount = orPayAmount;
		this.orPayType = orPayType;
		this.orStatus = orStatus;
		this.orPostDate = new Date();
		this.orName = orName;
		this.orCount = orCount;
		this.orPost = orPost;
		this.orAdd1 = orAdd1;
		this.orAdd2 = orAdd2;
		this.orRecHuman = orRecHuman;
		this.orRecPhone = orRecPhone;
		this.orMessage = orMessage;
		this.orCardNum = orCardNum;
		this.orCvc = orCvc;
		this.orBankName = orBankName;
		this.orBackNum = orBackNum;
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
