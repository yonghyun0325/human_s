package com.human.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.human.hms.entity.UnUserEntity;

public interface UnUserRepository extends JpaRepository<UnUserEntity, Integer>{
	
	
	@Query(value = "SELECT * FROM un_member un WHERE un.o_idx = ?1 AND un.un_phone = ?2", nativeQuery = true)
	UnUserEntity checkOrderNum(String orderNum, String unPhone);

}
