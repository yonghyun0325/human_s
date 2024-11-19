package com.human.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.human.hms.entity.FavoriteEntity;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

	@Query("SELECT f FROM FavoriteEntity f WHERE f.user.userIdx = :userIdx")
	List<FavoriteEntity> select(@Param("userIdx") int userIdx);

	
}
