package com.human.hms.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name="product")
@Entity
public class ProductEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pdt_idx", updatable=false)
	private int pdtIdx;			 //상품번호
	
	@Column(name="pdt_img")
	private String img;			 //상품이미지링크 -> API에서 대량의 상품 목록 저장을 위함
	
	@Column(name="pdt_origin")
	private String pdtOrigin;	 //상품이미지 원본이름
	
	@Column(name="pdt_save")
	private String pdtSave;		 //상품이미지 저장이름
	
	@Column(name="pdt_title", nullable=false)
	private String pdtTitle;	 //상품 등록 제목
	
	@Column(name="pdt_price", nullable=false)
	private String pdtPrice;	 //가격
	
	@Column(name="pdt_largecode", nullable=false)
	private String pdtLargeCode; //상품대분류코드
	
	@Column(name="pdt_midcode", nullable=false)
	private String pdtMidCode;	 //상품중분류코드
	
	@Column(name="pdt_smallcode", nullable=false)
	private String pdtSmallCode; //상품소분류코드
	
	@Column(name="pdt_largename", nullable=false)
	private String pdtLargeName; //상품대분류이름
	
	@Column(name="pdt_midname", nullable=false)
	private String pdtMidName;	 //상품중분류이름
	
	@Column(name="pdt_smallname", nullable=false)
	private String pdtSmallName; //상품소분류이름
	
	@Column(name="pdt_area", nullable=false)
	private String pdtArea;		 //지역
	
	@Column(name="pdt_area2", nullable=false)
	private String pdtArea2;	 //상세지역
	
	@Column(name="pdt_writer", nullable=false)
	private String pdtWriter;	 //상품등록 닉네임
	
	@Column(name="pdt_kg", nullable=false)
	private String pdtKg;	 //상품 무게
	
	@Column(name="pdt_g_price", nullable=false)
	private String pdtGPrice;	 //상품 100g당 가격
	
	@Column(name="pdt_date", columnDefinition = "DATETIME DEFAULT NOW()")
	private Date pdtDate;
	
	@ManyToOne //조인 관계
	@JoinColumn(name="user_idx", updatable = false) //회원번호(외래키)
	private UserEntity userEntity; 
	
	@Transient
	private MultipartFile pdtFile;
	
	@Transient //테이블의 컬럼과 매핑되지 않는 영속 제외 필드
	private MultipartFile[] uploadFiles;
	
	@Transient
	private List<ProductImgEntity> attachedList;
	
	@Builder
	public ProductEntity(String img, String pdtTitle, String pdtPrice, String pdtLargeCode, String pdtMidCode,
			 String pdtSmallCode, String pdtArea, String pdtArea2, String pdtWriter, String pdtKg, String pdtGPrice,
			 MultipartFile pdtFile, MultipartFile[] uploadFiles) {
		this.img = img;
		this.pdtTitle = pdtTitle;
		this.pdtPrice = pdtPrice;
		this.pdtLargeCode = pdtLargeCode;
		this.pdtMidCode = pdtMidCode;
		this.pdtSmallCode = pdtSmallCode;
		this.pdtArea = pdtArea;
		this.pdtArea2 = pdtArea2;
		this.pdtWriter = pdtWriter;
		this.pdtKg = pdtKg;
		this.pdtGPrice = pdtGPrice;
		this.pdtDate = new Date();
		this.pdtFile = pdtFile;
		this.uploadFiles = uploadFiles;
	}
	
	//attachedList 값의 변경을 위한 메소드
	public void updateAttachedList(List<ProductImgEntity> attachedList) {
		this.attachedList = attachedList;
	}
	
	//별도로 필드값을 변경할 메소드
	public void updatePdtOrigin(String pdtOrigin) {
		this.pdtOrigin = pdtOrigin;
	}
	
	public void updatePdtSave(String pdtSave) {
		this.pdtSave = pdtSave;
	}
	
	//memberEntity필드에 대한 값의 변경 메소드
	public void updateUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	public void updateCodeName(String pdtLargeName, String pdtMidName, String pdtSmallName) {
		this.pdtLargeName = pdtLargeName;
		this.pdtMidName = pdtMidName;
		this.pdtSmallName = pdtSmallName;
	}
	
	public void updatePdtIdx(int pdtIdx) {
		this.pdtIdx = pdtIdx;
	}

}
