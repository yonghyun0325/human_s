package com.human.hms.entity;



import java.util.Date;

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
@Table(name = "address")
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본 키 자동 증가 설정
	@Column(name = "a_idx",  nullable = false)
	public int addIdx;
	@Column(name = "a_post",  nullable = false)
	public String addPost; // 우편번호
	@Column(name = "a_add1",  nullable = false)
	public String add1; // 기본 주소
	@Column(name = "a_add2",  nullable = false)
	public String add2; // 상세 주소
	@Column(name = "a_date",  columnDefinition="DATETIME DEFAULT NOW()")
	public Date addDate; // 생성일
	@Column(name = "a_status",  columnDefinition = "TINYINT DEFAULT 1")
	public int addStatus; // 기본 배송지 여부 (0: 기본 배송지, 1: 추가 주소)
	
	@ManyToOne
	@JoinColumn(name="user_idx", updatable=false)
	private UserEntity userEntity;
	
	// 새로운 필드 추가
    @Column(name = "a_name", nullable = false)
    private String addressName; // 배송지 이름

    @Column(name = "a_receiver", nullable = false)
    private String receiverName; // 받는 사람 이름

    @Column(name = "a_phone", nullable = false)
    private String phoneNumber; // 연락처

    @Column(name = "a_message", length = 300)
    private String orderMessage; // 주문 메시지

    @Column(name = "a_updated", columnDefinition = "DATETIME DEFAULT NOW() ON UPDATE NOW()")
    private Date updatedAt; // 수정일
	
	@Builder
	public AddressEntity(String addPost, String add1, String add2, int addIdx, String addressName, 
            String receiverName, String phoneNumber, String orderMessage) {
		this.addIdx = addIdx;
        this.addPost = addPost;
        this.add1 = add1;
        this.add2 = add2;
        this.addressName = addressName;
        this.receiverName = receiverName;
        this.phoneNumber = phoneNumber;
        this.orderMessage = orderMessage;
        this.addDate = new Date(); // 생성일 초기화
        this.updatedAt = new Date(); // 수정일 초기화
        this.addStatus = 1; // 기본값: 추가 주소
	}
	
	//a_idx에 대한 값의 변경 메소드
	public void updateAddIdx(int addIdx) {
		this.addIdx = addIdx;
	}
	
	//UserEntity필드에 대한 값의 변경 메소드
	public void updateUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	//배송지 기본값(0) 추가주소(1) 업데이트
	public void updateStatus(int addStatus) {
		this.addStatus = addStatus;
	}

	public void setAddPost(String addPost2) {
		this.addPost=addPost2;
		
	}

	public void setAdd1(String add12) {
		this.add1=add12;
		
	}

	public void setAdd2(String add22) {
		this.add2=add22;
		
	}

	public void setAddStatus(int addStatus) {
	    this.addStatus = addStatus;
	}
}
