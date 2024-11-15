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
@Table(name = "seller")  // 실제 데이터베이스의 테이블 이름
public class SellerEntity {

	@Id
	@Column(name = "s_id_num", nullable = false)
	public String seIdNum;//사업자 등록번호
	
	@Column(name = "s_name", nullable = false)
	public String seName; //상호명
	
	@Column(name = "s_bank", nullable = false)
	public String seBank;//은행명
	
	@Column(name = "s_banknum", nullable = false)
	public String seBankNum;//계좌번호
	
	@Column(name = "s_bankname", nullable = false)
	public String seBankName;//예금주	
	
	@ManyToOne
	@JoinColumn(name="user_idx", updatable=false)
	private UserEntity userEntity;
	
	@Builder
	public SellerEntity(String seIdNum, String seName, String seBank, String seBankNum,
			String seBankName) {
		
		this.seIdNum = seIdNum;
		this.seName = seName;
		this.seBank = seBank;
		this.seBankNum = seBankNum;
		this.seBankName = seBankName;
	}
	
	//UserEntity필드에 대한 값의 변경 메소드
	public void updateUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
}
