package com.human.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name="product_img")
@Entity
public class ProductImgEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pi_idx", updatable=false)
	private int piIdx;		 //사진번호
	
	@Column(name="pi_origin", nullable=false)
	private String piOrigin; //이미지 원본이름
	
	@Column(name="pi_save", nullable=false)
	private String piSave;	 //이미지 저장이름
	
	@ManyToOne
	@JoinColumn(name="pdt_idx", updatable=false)
	private ProductEntity productEntity;
	
	//별도로 필드값을 변경할 메소드 정의
	public void updatePiOrigin(String piOrigin) {
		this.piOrigin = piOrigin;
	}
	
	public void updatePiSave(String piSave) {
		this.piSave = piSave;
	}
	
	//noticeEntity 필드 값 변경 메소드
	public void updateProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}	
	
}
