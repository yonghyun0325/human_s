package com.human.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.human.hms.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	@Query("select u from UserEntity u where u.userEmail = :userEmail and u.userPw = :userPw")
	UserEntity login(@Param("userEmail")String userEmail, @Param("userPw")String userPw);

}
