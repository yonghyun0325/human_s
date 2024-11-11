package com.human.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join")
public class JoinController {
	
	//회원가입 약관동의 페이지
	@GetMapping("/agreement.no")
	public String joinAgreement() {
		
		return "join/agreement";
	}
	
	//회원가입 유형선택 페이지
	@GetMapping("/jointype.no")
	public String jointype() {
		
		return "join/jointype";
	}
	
	//판매자 등록가입
	@GetMapping("/join_register.no")
	public String join_register() {
		
		return "join/join_register";
	}
	
	//일반회원가입
	@GetMapping("/join_normal.no")
	public String join_normal() {
		
		return "join/join_normal";
	}
	
}
	

