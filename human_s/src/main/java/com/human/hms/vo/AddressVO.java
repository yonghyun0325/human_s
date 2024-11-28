package com.human.hms.vo;

import lombok.Data;

@Data
public class AddressVO {
	
	private int addIdx; // 주소 ID
    private String addPost; // 우편번호
    private String add1; // 기본 주소
    private String add2; // 상세 주소
    private String addressName; // 배송지 이름
    private String receiverName; // 받는 사람 이름
    private String phoneNumber; // 연락처
    private String orderMessage; // 주문 메시지
    private int addStatus; // 기본/추가 배송지 상태 (0: 기본 배송지, 1: 추가 주소)
}
