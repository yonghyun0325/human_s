package com.human.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{



	@Query("select u from UserEntity u where u.userEmail = :userEmail and u.userPw = :userPw")
	UserEntity login(@Param("userEmail")String userEmail, @Param("userPw")String userPw);

	@Query("select count(*) from UserEntity u where u.userEmail = ?1")
	int sameIdcheck(String userEmail);

	//세션에 저장
	@Query("select u from UserEntity u where u.userEmail = :userEmail")
	UserEntity equalsUser(@Param("userEmail")String email);
	
	//아이디 찾기
	@Query("select u from UserEntity u where u.userName = :userName and u.userPhone = :userPhone")
	UserEntity findUserId(@Param("userName") String name, @Param("userPhone") String phone);
	
	//비밀번호 찾기
	@Query("select u from UserEntity u where u.userEmail = :userEmail and u.userName = :userName")
	UserEntity findUserPw(@Param("userEmail") String email, @Param("userName")String name);
	
	//비밀번호 변경 전 세션객체에 가입된 아이디일 경우 인증번호 보내기 위한 이메일 조회
	@Query("select u from UserEntity u where u.userEmail = :userEmail")
	UserEntity changeCheckEmail(@Param("userEmail") String userEmail);

	//비밀번호 변경
	@Transactional
	@Modifying
	@Query("update UserEntity u set u.userPw = ?2 where u.userEmail = ?1")
	int changePassword(String userEmail, String userPw);
	

	
}
