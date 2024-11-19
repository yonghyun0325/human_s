package com.human.hms.service;

import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.SellerEntity;
import com.human.hms.entity.UserEntity;

public interface UserService {

	UserEntity login(String userEmail, String userPw);

	//회원가입 처리
	UserEntity save(UserEntity vo);

	AddressEntity a_save(AddressEntity a_vo, UserEntity vo);

	int sameIdcheck(String userEmail);

	String authEmail(String email);//메일인증

	int sellerInfoCheck(String seIdNum);//사업자조회

	UserEntity equalsUser(String email);//음
	
	UserEntity findUserId(String name, String phone);//아이디 찾기

	UserEntity findUserPw(String email, String name);//비밀번호 찾기

	SellerEntity s_save(SellerEntity s_entity, UserEntity entity);//판매자 회원가입

}
