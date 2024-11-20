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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "story")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image")
    private String image; // 단일 파일 경로

    @Column(name = "tagged_item_title")
    private String taggedItemTitle;

    @Column(name = "tagged_item_price")
    private String taggedItemPrice;

    @Column(name = "profile_image")
    private String profileImage; // 프로필 이미지 경로

    @Column(name = "author")
    private String author;

    @Column(name = "story_title")
    private String storyTitle;

    @Column(name = "story_content")
    private String storyContent;

    @Column(name = "created_date")
    @Builder.Default
    private Date createdDate = new Date();

    @Column(name = "views", columnDefinition = "INT DEFAULT 0")
    private int views;

    @ManyToOne
    @JoinColumn(name = "user_idx", updatable = false)
    private UserEntity userEntity;

    

    @Transient
    private MultipartFile[] uploadFiles; // JPA와 관련 없는 다중 파일
    
    @Builder
	public StoryEntity(String author, String storyContent, String storyTitle, String image,
						String taggedItemTitle) {
		this.author = author;
		this.storyContent = storyContent;
		this.storyTitle = storyTitle;
		this.image = image;
		this.taggedItemTitle = taggedItemTitle;
		
		//컬럼의 기본값으로 정의된 것은 생성자를 이용해서 필드를 수동으로 초기화함
		this.createdDate = new Date();
		this.views = 0;
	}
}
