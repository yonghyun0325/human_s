package com.human.hms.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.ReviewEntity;

public interface ProductService {

	List<Object[]> getLargeList();

	List<Object[]> getMidList();

	List<Object[]> getSmallList();

	List<Map<String, String>> getGraphData(String largeCode, String midCode, String smallCode);

	int insertProduct(ProductEntity entity, HttpServletRequest request);

	List<ProductEntity> getProductsByUserId(int userIdx);

	ProductEntity getProductById(Integer pdtIdx);

	List<ProductEntity> getPopList();

	List<ProductEntity> getNewList();

	List<Object[]> getAreaList();

	List<Object[]> getArea2List();

	ProductEntity findbyId(int idx);

	List<ReviewEntity> getReviewList(int idx);

	List<ProductEntity> findAll();

	List<ProductEntity> getSelectList(String select);

	List<ProductEntity> getProductAreaList();

	List<ProductEntity> checkAreaList(List<String> checkeds);

	List<ProductEntity> checkCategoryList(List<String> checkeds);

}
