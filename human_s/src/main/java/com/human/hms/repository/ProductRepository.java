package com.human.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.human.hms.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    // UserEntity 대신 userIdx(int)로 조회
	@Query(value = "SELECT * FROM product p WHERE p.user_idx = :userIdx", nativeQuery = true)
	List<ProductEntity> findByUserEntity_UserIdx(@Param("userIdx") int userIdx);

	//api 새로 받기 전 기존에 등록한 거 빼고 삭제
	@Modifying
	@Query("delete from ProductEntity p where p.pdtOrigin is null")
	void deleteLocalSpcprdAll();

	//인기순 리스트 조회
	@Query(value = "select * from product p limit 16 ", nativeQuery = true)
	List<ProductEntity> getPopList();

	//신상품 리스트 조회
	@Query(value = "select * from product p order by pdt_date desc limit 16 ", nativeQuery = true)
	List<ProductEntity> getNewList();

	//지역 조회
	@Query("select distinct p.pdtArea from ProductEntity p")
	List<Object[]> getAreaList();

	//상세지역 조회
	@Query("select distinct p.pdtArea, p.pdtArea2 from ProductEntity p")
	List<Object[]> getArea2List();

	//검색어 조회
	@Query("select p from ProductEntity p "
			+ "where pdtLargeName like %:select% or pdtMidName like %:select% or pdtSmallName like %:select% "
			+ "or pdtTitle like %:select%")
	List<ProductEntity> getSelectList(@Param("select") String select);

	//지역상품 목록 조회
	@Query("select p from ProductEntity p "
			+ "group by pdtArea")
	List<ProductEntity> getProductAreaList();

	//지역별 체크 선택에 따른 상품 리스트 조회
	@Query("select p from ProductEntity p "
			+ "where p.pdtArea in :checkeds ")
	List<ProductEntity> checkAreaList(@Param("checkeds") List<String> checkeds);

	//분류별 체크 선택에 따른 상품 리스트 조회
	@Query("select p from ProductEntity p "
			+ "where p.pdtLargeCode in :checkeds ")
	List<ProductEntity> checkCategoryList(@Param("checkeds") List<String> checkeds);
	
}
