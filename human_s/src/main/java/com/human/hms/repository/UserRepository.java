package com.human.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
	UserEntity findUserId(@Param("userName")String name,@Param("userPhone") String phone);
	
	//비밀번호 찾기
	@Query("select u from UserEntity u where u.userEmail = :userEmail and u.userName = :userName")
	UserEntity findUserPw(@Param("userEmail")String email, @Param("userName")String name);

	
}
