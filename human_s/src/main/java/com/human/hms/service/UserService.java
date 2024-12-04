package com.human.hms.service;

import java.util.List;

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

	UserEntity changeCheckEmail(String userEmail);//비밀번호 변경 전 이메일이 세션객체에 저장됐는지 확인

	int changePassword(String userEmail, String userPw);//비밀번호 변경

	void deleteUser(int userIdx); // 회원 탈퇴

	List<UserEntity> getUserList();

	List<UserEntity> getSellerList();


}
