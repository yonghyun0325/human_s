package com.human.hms.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.UserEntity;

public interface MyPageRepository extends JpaRepository<UserEntity, Integer> {
	
	@Modifying
    @Transactional
	@Query("DELETE FROM UserEntity u WHERE u.userEmail = :email")
    void deleteById(@Param("idx") String email);
	
}
