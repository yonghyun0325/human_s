package com.human.hms.repository;

import java.time.LocalDate;
import java.util.Date;
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
	List<OrderListEntity> getDateShow(@Param("userIdx") Long userIdx, 
            @Param("startDate") LocalDate startDate, 
            @Param("endDate") LocalDate endDat);

	//오늘 날짜 주문 개수 체크하기
	@Query("select count(o) from OrderListEntity o "
			+ "where FUNCTION('DATE_FORMAT', o.orPayDate, '%Y%m%d') = :formattedDate")
	int countOrder(@Param("formattedDate")String formattedDate);

	@Query("SELECT o FROM OrderListEntity o WHERE o.orPayDate BETWEEN :startDate AND :endDate and o.userEntity.userIdx = :userIdx ")
	List<OrderListEntity> findByOrPayDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, 
			@Param("userIdx") int userIdx);

	//판매내역 조회하기
	@Query("SELECT o FROM OrderListEntity o WHERE o.productEntity.userEntity.userIdx = :userIdx")
	List<OrderListEntity> getSellList(@Param("userIdx") int userIdx);

	//판매내역 일자별 조회
	@Query("SELECT o FROM OrderListEntity o WHERE o.orPayDate BETWEEN :startDate AND :endDate "
			+ "and o.productEntity.userEntity.userIdx = :userIdx ")
	List<OrderListEntity> findBySellDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, 
			@Param("userIdx") int userIdx);

	//비회원 주문조회
	@Query("select o from OrderListEntity o where o.orIdx = :orIdx")
	List<OrderListEntity> getUnUserOrderList(@Param("orIdx") String orIdx);
	
}
