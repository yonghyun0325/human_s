package com.human.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.BasketEntity;

public interface BasketRepository extends JpaRepository<BasketEntity, Integer>{

	//장바구니에 이미 존재하는지 체크
	@Query("select count(b) from BasketEntity b "
			+ "where b.userEntity.userIdx = :#{#entity.userEntity.userIdx} "
			+ "and b.productEntity.pdtIdx = :#{#entity.productEntity.pdtIdx} ")
	int checkCart(@Param("entity") BasketEntity entity);

	//장바구니 수량 수정하기
	@Transactional
	@Modifying
	@Query("update BasketEntity b "
			+ "set b.qty = b.qty+:qty "
			+ "where b.userEntity.userIdx = :userIdx "
			+ "and b.productEntity.pdtIdx = :pdtIdx ")
	int updateCart(@Param("qty") int qty, @Param("userIdx") int userIdx, @Param("pdtIdx") int pdtIdx);

	//장바구니 목록 불러오기
	@Query("select b from BasketEntity b "
			+ "where b.userEntity.userIdx = :userIdx ")
	List<BasketEntity> getBasketByUser(@Param("userIdx") int userIdx);

}
