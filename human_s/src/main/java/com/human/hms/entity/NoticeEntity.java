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
@Table(name = "notice")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "created_date", columnDefinition = "DATETIME DEFAULT NOW()")
    private Date createdDate;

    @Column(name = "notice_content")
    private String noticeContent;

    @Column(name = "notice_title")
    private String noticeTitle;

    @Column(name = "views", columnDefinition = "INT DEFAULT 0")
    private int views;
    
    @ManyToOne
    @JoinColumn(name = "user_idx", updatable = false)
    private UserEntity userEntity;
    
    @Builder
	public NoticeEntity(String author, String noticeContent, String noticeTitle) {
		this.author = author;
		this.noticeContent = noticeContent;
		this.noticeTitle = noticeTitle;
		
		//컬럼의 기본값으로 정의된 것은 생성자를 이용해서 필드를 수동으로 초기화함
		this.createdDate = new Date();
		this.views = 0;
	}

	public void updateUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
		
	}
	
	public void updateNoticeContent(String noticeContent) {
	    this.noticeContent = noticeContent;
	}

	public void updateNoticeTitle(String noticeTitle) {
	    this.noticeTitle = noticeTitle;
	}

	
	
	
	
}  	

