package com.human.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.human.hms.entity.ProductImgEntity;

public interface ProductImgRepository extends JpaRepository<ProductImgEntity, Integer>{

	//상품 상세보기 이미지 조회하기
	@Query(value="select * from product_img where pdt_idx = ?1", nativeQuery = true)
	List<ProductImgEntity> getImgList(int idx);

	//상품 삭제하기
	@Modifying
	@Query(value="delete from product_img where pdt_idx = ?1", nativeQuery = true)
	void deleteByPdtIdx(int idx);

}
