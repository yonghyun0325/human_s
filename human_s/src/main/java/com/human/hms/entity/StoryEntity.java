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

    @Column(name = "main_image")
    private String mainImage; // 메인 이미지 경로

    @Column(name = "content_image")
    private String contentImage; // 다중 컨텐츠 이미지 경로 (콤마로 구분된 문자열)

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
    private MultipartFile mainImageFile; // 메인 이미지 업로드 파일 (JPA 매핑 제외)

    @Transient
    private MultipartFile contentImageFiles; // 다중 컨텐츠 이미지 업로드 파일 (JPA 매핑 제외)

    @Builder
    public StoryEntity(String author, String storyContent, String storyTitle, String mainImage,
                       String taggedItemTitle, String contentImage) {
        this.author = author;
        this.storyContent = storyContent;
        this.storyTitle = storyTitle;
        this.mainImage = mainImage;
        this.contentImage = contentImage;
        this.taggedItemTitle = taggedItemTitle;

        // 기본값 초기화
        this.createdDate = new Date();
        this.views = 0;
    }
}
