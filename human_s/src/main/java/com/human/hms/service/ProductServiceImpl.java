package com.human.hms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.BasketEntity;
import com.human.hms.entity.PinquiryEntity;
import com.human.hms.entity.PriceRealEntity;
import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.ProductImgEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.BasketRepository;
import com.human.hms.repository.DmsjPriceRealRepository;
import com.human.hms.repository.PinquiryRepository;
import com.human.hms.repository.ProductImgRepository;
import com.human.hms.repository.ProductRepository;
import com.human.hms.repository.ReviewRepository;
import com.human.hms.util.ProductFileManager;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private DmsjPriceRealRepository priceRealRepository;
	private ProductFileManager fileManager;
	private ProductRepository productRepository;
	private ProductImgRepository productImgRepository;
	private ReviewRepository reviewRepository;		 	//상품리뷰
	private BasketRepository basketRepository;
	private PinquiryRepository pinquiryRepository;		//상품문의


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
		
		PriceRealEntity pEntity = priceRealRepository
				.getPricebyCode(entity.getPdtLargeCode(), entity.getPdtMidCode(), entity.getPdtSmallCode());
		entity.updateCodeName(pEntity.getLargeName(), pEntity.getMidName(), pEntity.getSmallName());
		
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
	
	//상품 상세보기
	@Override
	public ProductEntity findbyId(int idx) {
		ProductEntity entity = null;
		Optional<ProductEntity> optional = productRepository.findById(idx);
		if(optional.isPresent()) {
			entity = optional.get();
		}
		List<ProductImgEntity> imgList = productImgRepository.getImgList(idx);
		entity.updateAttachedList(imgList);
		return entity;
	}

	//상품 리뷰 목록 조회
	@Override
	public List<ReviewEntity> getReviewList(int idx) {
		return reviewRepository.getReviewList(idx);
	}

	//상품목록 전체 조회
	@Override
	public List<ProductEntity> findAll() {
		return productRepository.findAll();
	}

	//헤더 검색창에 따른 리스트 조회
	@Override
	public List<ProductEntity> getSelectList(String select) {
		return productRepository.getSelectList(select);
	}

	//메인화면 지역별 상품 1개씩 조회
	@Override
	public List<ProductEntity> getProductAreaList() {
		return productRepository.getProductAreaList();
	}

	//지역별 체크 선택에 따른 상품 리스트 조회
	@Override
	public List<ProductEntity> checkAreaList(List<String> checkeds) {
		return productRepository.checkAreaList(checkeds);
	}

	//분류별 체크 선택에 따른 상품 리스트 조회
	@Override
	public List<ProductEntity> checkCategoryList(List<String> checkeds) {
		return productRepository.checkCategoryList(checkeds);
	}
	
	//상품수정하기
	@Transactional
	@Override
	public int updateProduct(ProductEntity entity, HttpServletRequest request) {
	
		int result = 0;
		
		PriceRealEntity pEntity = priceRealRepository
				.getPricebyCode(entity.getPdtLargeCode(), entity.getPdtMidCode(), entity.getPdtSmallCode());
		entity.updateCodeName(pEntity.getLargeName(), pEntity.getMidName(), pEntity.getSmallName());
		
		//기존에 저장된 상세내용 이미지 파일이 있는지 조회
		List<ProductImgEntity> haveImg = productImgRepository.getImgList(entity.getPdtIdx());
		if(haveImg != null && !haveImg.isEmpty()) {
			productImgRepository.deleteByPdtIdx(entity.getPdtIdx());			
		}
		
		entity = fileManager.pdtFile(entity, request);
		
		if(entity.getUploadFiles() !=  null && entity.getUploadFiles().length != 0) {
			result = productRepository.updateProduct(entity);
			entity = fileManager.handleFile(entity, request);
			
			List<ProductImgEntity> imgList = entity.getAttachedList();
			for(ProductImgEntity imgEntity : imgList) {
				imgEntity.updateProductEntity(entity);
				
				productImgRepository.save(imgEntity);
			}
		}else {
			result = productRepository.updateProduct(entity);
		}
		
		return result;
	}

	//상품 삭제하기
	@Transactional
	@Override
	public int deleteProduct(int idx) {
		
		int result = 0;
		
		//기존에 저장된 상세내용 이미지 파일이 있는지 조회
		List<ProductImgEntity> haveImg = productImgRepository.getImgList(idx);
		if(haveImg != null && !haveImg.isEmpty()) {
			productImgRepository.deleteByPdtIdx(idx);			
		}
		
		result = productRepository.deleteProduct(idx);
		
		return result;
	}

	//상품을 장바구니에 등록하기
	@Override
	public int productInCart(BasketEntity entity) {
		int result = 0;
		
		if(basketRepository.checkCart(entity) == 0) { //장바구니에 없으면
			if(basketRepository.save(entity) != null) {
				result = 1;
			}
		}else { //장바구니에 이미 존재하면 : 기존 수량 + 현재 수량
			if(basketRepository.updateCart(entity.getQty(), entity.getUserEntity().getUserIdx(), 
					entity.getProductEntity().getPdtIdx()) == 1) {
				result = 1;
			}
		}
		
		return result;
	}

	//상품 문의 목록 조회
	@Override
	public List<PinquiryEntity> getPinquiryList(int idx) {
		return pinquiryRepository.getPinquiryList(idx);
	}

}
