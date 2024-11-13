package com.human.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//일별 부류별 도.소매가격정보
@Getter
@NoArgsConstructor
@Entity
@Table(name="daily_price")
public class DailyPriceByCategoryEntity {
	
	@Id
	@Column(name="id", updatable=false)
	private String itemKindRankCode; //기본키로 쓰기 위한 품종+상태코드 조합
	
	@Column(name="item_name", nullable=false)
	private String item_name; //품목명
	
	@Column(name="item_code", nullable=false)
    private String item_code; //품목코드
	
	@Column(name="kind_name", nullable=false)
    private String kind_name; //품종명
	
	@Column(name="kind_code", nullable=false)
    private String kind_code; //품종코드
	
	@Column(name="rank_name", nullable=false)
    private String rank;	  //상태(상품,중품)
	
	@Column(name="rank_code", nullable=false)
    private String rank_code; //상태 코드?
	
	@Column(name="unit", nullable=false)
    private String unit; 	  //단위
	
	@Column(name="day1", nullable=false)
    private String day1; 	  //일자(조회일자)
	
	@Column(name="dpr1", nullable=false)
    private String dpr1; 	  //조회일자 가격
	
	@Column(name="day2", nullable=false)
    private String day2; 	  //1일전 일자(조회일자 기준)
	
	@Column(name="dpr2", nullable=false)
    private String dpr2; 	  //1일전 가격
	
	@Column(name="day3", nullable=false)
    private String day3; 	  //1주일전 일자(조회일자 기준)
	
	@Column(name="dpr3", nullable=false)
    private String dpr3; 	  //1주일전 가격
	
	@Column(name="day4", nullable=false)
    private String day4; 	  //2주일전 일자(조회일자 기준)
	
	@Column(name="dpr4", nullable=false)
    private String dpr4; 	  //2주일전 가격
	
	@Column(name="day5", nullable=false)
    private String day5; 	  //1개월전 일자(조회일자 기준)
	
	@Column(name="dpr5", nullable=false)
    private String dpr5; 	  //1개월전 가격
	
	@Column(name="day6", nullable=false)
    private String day6; 	  //1년전 일자(조회일자 기준)
	
	@Column(name="dpr6", nullable=false)
    private String dpr6; 	  //1년전 가격
	
	@Column(name="day7", nullable=false)
    private String day7; 	  //평년일자
	
	@Column(name="dpr7", nullable=false)
    private String dpr7; 	  //평년 가격
	
	@Builder
	public DailyPriceByCategoryEntity(String itemKindRankCode, String item_name, String item_code, String kind_name,
			String kind_code, String rank, String rank_code, String unit, String day1, String dpr1,
			String day2, String dpr2, String day3, String dpr3, String day4, String dpr4, String day5, String dpr5,
			String day6, String dpr6, String day7, String dpr7) {
		
		this.itemKindRankCode = itemKindRankCode;
		this.item_name = item_name;
		this.item_code = item_code;
		this.kind_name = kind_name;
		this.kind_code = kind_code;
		this.rank = rank;
		this.rank_code = rank_code;
		this.unit = unit;
		this.day1 = day1;
		this.dpr1 = dpr1;
		this.day2 = day2;
		this.dpr2 = dpr2;
		this.day3 = day3;
		this.dpr3 = dpr3;
		this.day4 = day4;
		this.dpr4 = dpr4;
		this.day5 = day5;
		this.dpr5 = dpr5;
		this.day6 = day6;
		this.dpr6 = dpr6;
		this.day7 = day7;
		this.dpr7 = dpr7;
		
	}
    
}
