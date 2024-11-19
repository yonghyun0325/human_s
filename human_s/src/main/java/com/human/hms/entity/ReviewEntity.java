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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "review")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private int rating; // 평점

    @Column(name = "author")
    private String author;
    
    @Column(name = "review_title")
    private String reviewTitle;
    
    @Column(name = "review_content")
    private String reviewContent;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date", columnDefinition = "DATETIME DEFAULT NOW()")
    private Date createdDate;

    @Column(name = "views", columnDefinition = "INT DEFAULT 0")
    private int views;
    
    @ManyToOne
    @JoinColumn(name = "user_idx", updatable = false)
    private UserEntity userEntity;
    
	@Builder
	public ReviewEntity(String author, String reviewContent, String reviewTitle, int rating) {
		this.author = author;
		this.reviewContent = reviewContent;
		this.reviewTitle = reviewTitle;
		this.rating = rating;
		
		//컬럼의 기본값으로 정의된 것은 생성자를 이용해서 필드를 수동으로 초기화함
		this.createdDate = new Date();
		this.views = 0;
	}

	public void updateUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
		
	}
	
	public void updateReviewContent(String reviewContent) {
	    this.reviewContent = reviewContent;
	}

	public void updateReviewTitle(String reviewTitle) {
	    this.reviewTitle = reviewTitle;
	}

	public void updateRating(int rating) {
		this.rating = rating;
		
	}

   
}
