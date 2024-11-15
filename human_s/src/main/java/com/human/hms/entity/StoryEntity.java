package com.human.hms.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

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
    private String image; // 썸네일 이미지 경로

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

    // 생성 시 createdDate 설정
    @PrePersist
    protected void onCreate() {
        if (createdDate == null) {
            createdDate = new Date();
        }
    }

    // Update methods
    public void updateUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void updateStoryContent(String storyContent) {
        this.storyContent = storyContent;
    }

    public void updateStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }
}
