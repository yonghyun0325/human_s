package com.human.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.human.hms.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    // UserEntity 대신 userIdx(int)로 조회
	@Query(value = "SELECT * FROM product p WHERE p.user_idx = :userIdx", nativeQuery = true)
	List<ProductEntity> findByUserEntity_UserIdx(@Param("userIdx") int userIdx);


	
}
