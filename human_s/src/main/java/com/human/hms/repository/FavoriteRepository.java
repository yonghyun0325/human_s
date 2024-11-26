package com.human.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.FavoriteEntity;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

	@Query("SELECT f FROM FavoriteEntity f WHERE f.userEntity.userIdx = :userIdx")
	List<FavoriteEntity> select(@Param("userIdx") int userIdx);

	//찜 목록에 이미 존재하는지 체크
	@Query("select count(f) from FavoriteEntity f "
			+ "where f.userEntity.userIdx = :#{#entity.userEntity.userIdx} "
			+ "and f.productEntity.pdtIdx = :#{#entity.productEntity.pdtIdx} ")
	int checkFavorite(@Param("entity") FavoriteEntity entity);

	//찜 삭제하기
	@Transactional
	@Modifying
	@Query(value="delete from favoritething "
			+ "where user_idx = ?1 "
			+ "and pdt_idx = ?2 ", nativeQuery = true)
	void deleteFavorite(int userIdx, int pdtIdx);

	
}
