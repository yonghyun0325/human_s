package com.human.hms.vo;

import lombok.Data;

@Data
public class UnUserVO {
	public int unIdx;//비회원번호
	public String unName;//비회원이름
	public String unPhone;//전화번호
	public String unBirth;
	public String unEmail;
	public String unPost;
	public String unAdd1;
	public String unAdd2;
	public String orIdx;//주문번호
}
