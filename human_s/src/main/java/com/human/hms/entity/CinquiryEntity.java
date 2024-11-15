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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cinquiry")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinquiryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "created_date", columnDefinition = "DATETIME DEFAULT NOW()")
    private Date createdDate;

    @Column(name = "cinquiry_content")
    private String cinquiryContent;

    @Column(name = "cinquiry_title")
    private String cinquiryTitle;

    @Column(name = "views", columnDefinition = "INT DEFAULT 0")
    private int views;
    
    @ManyToOne
    @JoinColumn(name = "user_idx", updatable = false)
    private UserEntity userEntity;
    
	@Builder
	public CinquiryEntity(String author, String cinquiryContent, String cinquiryTitle) {
		this.author = author;
		this.cinquiryContent = cinquiryContent;
		this.cinquiryTitle = cinquiryTitle;
		
		//컬럼의 기본값으로 정의된 것은 생성자를 이용해서 필드를 수동으로 초기화함
		this.createdDate = new Date();
		this.views = 0;
	}

	public void updateUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
		
	}
	
	public void updateCinquiryContent(String cinquiryContent) {
	    this.cinquiryContent = cinquiryContent;
	}

	public void updateCinquiryTitle(String cinquiryTitle) {
	    this.cinquiryTitle = cinquiryTitle;
	}

	
	
	
	
}  	

