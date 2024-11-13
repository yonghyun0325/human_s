package com.human.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="price_real")
public class PriceRealEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//기본키에 대한 값 정의: auto_increment
	@Column(name="pr_idx", updatable=false)
	private int prIdx;
	
	@Column(name="sale_date", nullable=false)
	private String saleDate; 	//경락일자
	
	@Column(name="whsal_cd", nullable=false)
	private String whsalCd; 	//도매시장코드
	
	@Column(name="whsal_name", nullable=false)
	private String whsalName; 	//도매시장명
	
	@Column(name="cmp_cd", nullable=false)
	private String cmpCd; 		//법인코드
	
	@Column(name="cmp_name", nullable=false)
	private String cmpName; 	//법인명
	
	@Column(name="large", nullable=false)
	private String large;		//대분류코드
	
	@Column(name="large_name", nullable=false)
	private String largeName; 	//대분류명
	
	@Column(name="mid", nullable=false)
	private String mid; 		//중분류코드
	
	@Column(name="mid_name", nullable=false)
	private String midName;		//중분류멍
	
	@Column(name="small", nullable=false)
	private String small;		//소분류코드
	
	@Column(name="small_name", nullable=false)
	private String smallName;	//소분류명
	
	@Column(name="san_cd", nullable=false)
	private String sanCd;		//산지코드
	
	@Column(name="san_name")
	private String sanName;		//산지명
	
	@Column(name="cost", nullable=false)
	private int cost;			//경락가
	
	@Column(name="qty", nullable=false)
	private int qty;			//물량
	
	@Column(name="std", nullable=false)
	private String std;			//규격(단량, 단위, 포장)
	
	@Column(name="sbid_time", nullable=false)
	private String sbidtime;	//경락일시
	
	@Builder
	public PriceRealEntity(String saleDate, String whsalCd, String whsalName, String cmpCd, String cmpName, String large,
			String largeName, String mid, String midName, String small, String smallName, String sanCd, String sanName, 
			int cost, int qty, String std, String sbidtime) {
		
		this.saleDate = saleDate;
		this.whsalCd = whsalCd;
		this.whsalName = whsalName;
		this.cmpCd = cmpCd;
		this.cmpName = cmpName;
		this.large = large;
		this.largeName = largeName;
		this.mid = mid;
		this.midName = midName;
		this.small = small;
		this.smallName = smallName;
		this.sanCd = sanCd;
		this.sanName = sanName;
		this.cost = cost;
		this.qty = qty;
		this.std = std;
		this.sbidtime = sbidtime;
		
	}

}
