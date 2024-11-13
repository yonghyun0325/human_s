package com.human.hms.vo;

import java.util.List;

import lombok.Data;

@Data
public class DailyPriceByCategoryVO {
	private List<Condition> condition; //요청메세지
	private DataContent data; //응답메세지

    @Data
    public static class Condition {
    	private String p_cert_key; //인증키
    	private String p_cert_id; //요청자 id
    	private String p_returntype; //json
        private String p_product_cls_code; //구분 ( 01:소매, 02:도매, default:02 )
        private String p_category_code; //부류코드(100:식량작물, 200:채소류, 300:특용작물, 400:과일류, 500:축산물, 600:수산물, default:100)
        private List<String> p_country_code; //가격 선택 지역
        private String p_regday; //조사일자
        private String p_convert_kg_yn; //y:1kg 단위표시, n:정보조사 단위표시
    }

    @Data
    public static class DataContent {
        private String error_code;
        private List<DailyPriceByCategory> item;
    }

    @Data
    public static class DailyPriceByCategory {
    	
    	private String itemKindRankCode; //기본키로 쓰기 위한 품종+상태코드 조합
        private String item_name; //품목명
        private String item_code; //품목코드
        private String kind_name; //품종명
        private String kind_code; //품종코드
        private String rank;	  //상태(상품,중품)
        private String rank_code; //상태 코드?
        private String unit; 	  //단위
        private String day1; 	  //일자(조회일자)
        private String dpr1; 	  //조회일자 가격
        private String day2; 	  //1일전 일자(조회일자 기준)
        private String dpr2; 	  //1일전 가격
        private String day3; 	  //1주일전 일자(조회일자 기준)
        private String dpr3; 	  //1주일전 가격
        private String day4; 	  //2주일전 일자(조회일자 기준)
        private String dpr4; 	  //2주일전 가격
        private String day5; 	  //1개월전 일자(조회일자 기준)
        private String dpr5; 	  //1개월전 가격
        private String day6; 	  //1년전 일자(조회일자 기준)
        private String dpr6; 	  //1년전 가격
        private String day7; 	  //평년일자
        private String dpr7; 	  //평년 가격
        
    }
}
