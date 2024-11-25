package com.human.hms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name="basket")
public class BasketEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_idx", updatable=false)
	private int cartIdx;
	
	@Column(name="qty", nullable=false)
	private int qty; //수량
	
	@ManyToOne
	@JoinColumn(name="user_idx", updatable=false)
	private UserEntity userEntity;
	
	@ManyToOne
	@JoinColumn(name="pdt_idx", updatable=false)
	private ProductEntity productEntity;
	
	@Builder
	public BasketEntity(int qty) {
		this.qty = qty;
	}
	
	public void updateUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	public void updateProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}
	
}
