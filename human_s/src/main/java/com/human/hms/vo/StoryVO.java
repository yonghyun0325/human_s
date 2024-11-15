package com.human.hms.vo;

import lombok.Data;
import java.util.Date;

@Data
public class StoryVO {
    private Long id;
    private String author;
    private String storyTitle;
    private String storyContent;
    private Date createdDate;
    private int views;
    private Long storyIdx;
    private Long userIdx; // UserEntity ID를 위한 필드
    private String taggedItemTitle;  // 태그된 상품 제목
    private String taggedItemPrice;  // 태그된 상품 가격
    private String profileImage; // 프로필 사진
    private String image;        // 게시물 이미지 
	
	}

	

