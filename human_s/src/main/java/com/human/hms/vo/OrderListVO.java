package com.human.hms.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListVO {
	private String orIdx;          // 주문번호
    private String orUserPoint;    // 적립금 사용 내역
    private String orPayAmount;    // 결제 금액
    private String orPayType;      // 결제 구분
    private String orStatus;       // 주문 상태
    private Date orPayDate;        // 주문일
    private String orPostDate;     // 배송일자
    private String orName;         // 상품명
    private String orCount;        // 개수
	private String orHuman; //주문자 이름
	private String orphone; //주문자 전화번호
	private String orEmail; //주문자 이메일
	private String orRecHuman;//받는분이름 
	private String orRecPhone;//받는분전화번호
	private String orMessage;//배송메시지
	private String orCardNum;//카드번호 
	private String orCvc;//cvc번호 
	private String orBankName;//은행명
	private String orBackNum;//계좌번호
	private String orPost;//우편번호
	private String orAdd1;//상세주소1
	private String orAdd2;//상세주소2
}
