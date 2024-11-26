package com.human.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.human.hms.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
	
	//userIdx값으로 주소 불러오기
	@Query("SELECT a FROM AddressEntity a WHERE a.userEntity.userIdx = :userIdx and a.addStatus = 0 ")
	AddressEntity getAddress(@Param("userIdx") int userIdx);


}
