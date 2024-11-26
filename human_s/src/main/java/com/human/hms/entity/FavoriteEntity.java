package com.human.hms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "favoritething")
public class FavoriteEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT 기본 키
    private Long id;

//    @Column(name = "image_url", length = 255) // 사진 URL
//    private String imageUrl;
//
//    @Column(name = "product_name", length = 255, nullable = false) // 상품명
//    private String productName;
//
//    @Column(name = "option", length = 255) // 옵션
//    private String option;
//
//    @Column(name = "reward_points", nullable = false) // 적립 포인트
//    private int rewardPoints;
//
//    @Column(name = "price", nullable = false) // 상품 가격
//    private int price;
//
//    @Column(name = "in_cart", nullable = false) // 장바구니 여부
//    private boolean inCart;
//
//    @Temporal(TemporalType.TIMESTAMP) // 시간 정보
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private Date createdAt;
    
    @ManyToOne //조인 관계
	@JoinColumn(name="user_idx", updatable = false) //회원번호(외래키)
	private UserEntity userEntity;
    
    @ManyToOne
    @JoinColumn(name = "pdt_idx", updatable = false) // 상품번호
    private ProductEntity productEntity;

//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = new Date(); // 생성 시 자동으로 현재 시간 설정
//    }
    
    public void updateUser(UserEntity userEntity) {
    	this.userEntity = userEntity;
    }
    
    public void updateProduct(ProductEntity productEntity) {
    	this.productEntity = productEntity;
    }
}
