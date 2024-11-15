package com.human.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.human.hms.entity.SellerEntity;

public interface SellerRepository extends JpaRepository<SellerEntity, String>{

	@Query("select count(*) from SellerEntity s where s.seIdNum =?1")
	int sellerInfoCheck(String seIdNum);

}
