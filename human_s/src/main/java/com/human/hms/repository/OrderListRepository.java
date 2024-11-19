package com.human.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.human.hms.entity.OrderListEntity;

public interface OrderListRepository extends JpaRepository<OrderListEntity, Long> {

	@Query("SELECT o FROM OrderListEntity o WHERE o.userEntity.userIdx = :userIdx")
    List<OrderListEntity> select(@Param("userIdx") int userIdx);
	
	@Query("SELECT o FROM OrderListEntity o " +
		       "WHERE o.userEntity.userIdx = :userIdx " +
		       "AND o.orPayDate BETWEEN :startDate AND :endDate")
	List<OrderListEntity> getDateShow(int userIdx, String startDate, String endDate);

}
