package com.human.hms.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductVO {
	
	private int pdtIdx;			 //상품번호
	private String pdtOrigin;	 //상품이미지 원본이름
	private String pdtSave;		 //상품이미지 저장이름
	private String pdtTitle;	 //상품 등록 제목
	private String pdtPrice;	 //가격
	private String pdtLargeCode; //상품대분류코드
	private String pdtMidCode;	 //상품중분류코드
	private String pdtSmallCode; //상품소분류코드
	private String pdtArea;		 //지역
	private String pdtArea2;	 //상세지역
	private String pdtWriter;	 //상품등록 닉네임
	private int userIdx;		 //회원번호

	
	private MultipartFile[] uploadFiles;
	private List<ProductImgVO> attachedList;
}
