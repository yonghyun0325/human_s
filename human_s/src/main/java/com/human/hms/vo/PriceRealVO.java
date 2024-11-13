package com.human.hms.vo;

import java.util.List;

import lombok.Data;

@Data
public class PriceRealVO {
	
	private int totCnt;
    private String pageNo;
    private String statusText;
    private String errorCode;
    private int dataCnt;
    private String status;
    private String errorText;
    private List<PriceReal> data;
    
    @Data
    public static class PriceReal{
    	private int rn;
    	private String saleDate; 	//경락일자
    	private String whsalCd; 	//도매시장코드
    	private String whsalName; 	//도매시장명
    	private String cmpCd; 		//법인코드
    	private String cmpName; 	//법인명
    	private String large;		//대분류코드
    	private String largeName; 	//대분류명
    	private String mid; 		//중분류코드
    	private String midName;		//중분류멍
    	private String small;		//소분류코드
    	private String smallName;	//소분류명
    	private String sanCd;		//산지코드
    	private String sanName;		//산지명
    	private int cost;			//경락가
    	private int qty;			//물량
    	private String std;			//규격(단량, 단위, 포장)
    	private String sbidtime;	//경락일시
    }

}
