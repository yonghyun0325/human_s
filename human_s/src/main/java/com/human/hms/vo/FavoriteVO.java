package com.human.hms.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteVO {
	private String imageUrl;      // 사진 URL
    private String productName;   // 상품명
    private String option;        // 옵션 정보
    private int price;            // 상품 가격
}
