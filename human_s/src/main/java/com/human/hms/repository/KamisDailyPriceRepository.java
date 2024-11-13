package com.human.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.human.hms.entity.DailyPriceByCategoryEntity;

public interface KamisDailyPriceRepository extends JpaRepository<DailyPriceByCategoryEntity, String>{

	@Query("select d from DailyPriceByCategoryEntity d "
			+ " where d.item_code = :itemCode and d.kind_code = :kindCode and d.rank_code = :rankCode")
	DailyPriceByCategoryEntity selectData(@Param("itemCode") String selectedItemCode, 
			@Param("kindCode") String selectedKindCode, @Param("rankCode") String selectedRankCode);

}
