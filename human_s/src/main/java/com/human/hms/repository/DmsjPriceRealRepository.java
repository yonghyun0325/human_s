package com.human.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.human.hms.entity.PriceRealEntity;

public interface DmsjPriceRealRepository extends JpaRepository<PriceRealEntity, String>{

	//대분류코드 조회
	@Query("select distinct pr.large, pr.largeName from PriceRealEntity pr")
	List<Object[]> getLargeList();
	
	//중분류코드 조회
	@Query("select distinct pr.large, pr.mid, pr.midName from PriceRealEntity pr")
	List<Object[]> getMidList();
	
	//소분류코드 조회
	@Query("select distinct pr.large, pr.mid, pr.small, pr.smallName from PriceRealEntity pr")
	List<Object[]> getSmallList();

	//최대,최소,평균 금액과 단위 조회
	@Query("select max(pr.cost), min(pr.cost), avg(pr.cost), pr.std " +
		       " from PriceRealEntity pr " +
		       " where pr.large = ?1 and pr.mid = ?2 and pr.small = ?3 " +
		       " group by pr.std")
	List<Object[]> getGraphData(String largeCode, String midCode, String smallCode);

}
