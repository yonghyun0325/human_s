package com.human.hms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.ProductImgEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.DmsjPriceRealRepository;
import com.human.hms.repository.ProductImgRepository;
import com.human.hms.repository.ProductRepository;
import com.human.hms.util.ProductFileManager;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private DmsjPriceRealRepository priceRealRepository;
	private ProductFileManager fileManager;
	private ProductRepository productRepository;
	private ProductImgRepository productImgRepository;


	//대분류코드 조회
	@Override
	public List<Object[]> getLargeList() {
		return priceRealRepository.getLargeList();
	}
	
	//중분류코드 조회
	@Override
	public List<Object[]> getMidList() {
		return priceRealRepository.getMidList();
	}

	//소분류코드조회
	@Override
	public List<Object[]> getSmallList() {
		return priceRealRepository.getSmallList();
	}

	//경매가 최대,최소,평균값과 단위 조회
	@Override
	public List<Map<String, String>> getGraphData(String largeCode, String midCode, String smallCode) {
		
		List<Object[]> result = priceRealRepository.getGraphData(largeCode, midCode, smallCode);
		List<Map<String, String>> responseList = new ArrayList<>();
	    
	    for (Object[] item : result) {
	        Map<String, String> response = new HashMap<>();
	        response.put("max", item[0].toString());
	        response.put("min", item[1].toString());
	        response.put("avg", item[2].toString());
	        response.put("std", item[3].toString());
	        responseList.add(response);
	    }

	    return responseList;
	}

	//상품등록하기
	@Transactional
	@Override
	public int insertProduct(ProductEntity entity, HttpServletRequest request) {
		int result = 0;
		
		HttpSession session = request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		entity.updateUserEntity(userEntity);
		
		entity = fileManager.pdtFile(entity, request);
		
		if(entity.getUploadFiles() !=  null && entity.getUploadFiles().length != 0) {
			productRepository.save(entity);
			
			entity = fileManager.handleFile(entity, request);
			
			List<ProductImgEntity> imgList = entity.getAttachedList();
			for(ProductImgEntity imgEntity : imgList) {
				imgEntity.updateProductEntity(entity);
				
				productImgRepository.save(imgEntity);
			}
			result = 1;
		}else {
			productRepository.save(entity);
			
			result = 1;
		}
		
		return result;
	}

	public List<ProductEntity> getProductsByUserId(int userIdx) {
	    List<ProductEntity> products = productRepository.findByUserEntity_UserIdx(userIdx);
	    return products;
	}

    // 상품 ID로 상품 조회
    public ProductEntity getProductById(Integer pdtIdx) {
        return productRepository.findById(pdtIdx)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));
    }

    //인기순 리스트 조회
	@Override
	public List<ProductEntity> getPopList() {
		return productRepository.getPopList();
	}

	//신상품 리스트 조회
	@Override
	public List<ProductEntity> getNewList() {
		return productRepository.getNewList();
	}

	//지역코드 조회
	@Override
	public List<Object[]> getAreaList() {
		return productRepository.getAreaList();
	}
	
	//지역코드 조회
	@Override
	public List<Object[]> getArea2List() {
		return productRepository.getArea2List();
	}
	
}
