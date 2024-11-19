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
	
}
