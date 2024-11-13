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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "a_idx",  nullable = false)
	public int addIdx;
	@Column(name = "a_post",  nullable = false)
	public String addPost;
	@Column(name = "a_add1",  nullable = false)
	public String add1;
	@Column(name = "a_add2",  nullable = false)
	public String add2;
	@Column(name = "a_date",  columnDefinition="DATETIME DEFAULT NOW()")
	public Date addDate;
	@Column(name = "a_status",  columnDefinition = "TINYINT DEFAULT 1")
	public int addStatus;
	
	@ManyToOne
	@JoinColumn(name="user_idx", updatable=false)
	private UserEntity userEntity;
	
	@Builder
	public AddressEntity(String addPost, String add1, String add2) {
		this.addPost = addPost;
		this.add1 = add1;
		this.add2 = add2;
		
		this.addDate = new Date();
		this.addStatus = 1;
	}
	
	//a_idx에 대한 값의 변경 메소드
	public void updateAddIdx(int addIdx) {
		this.addIdx = addIdx;
	}
	
	//UserEntity필드에 대한 값의 변경 메소드
	public void updateUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
