package com.human.hms.vo;

import lombok.Data;

@Data
public class ProductImgVO {
	
	private int piIdx;		 //사진번호
	private String piOrigin; //이미지 원본이름
	private String piSave;	 //이미지 저장이름
	private int pdtIdx; 	 //상품번호

}
