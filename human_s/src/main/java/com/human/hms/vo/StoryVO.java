package com.human.hms.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class StoryVO {
    private Long id;
    private String author;
    private String storyTitle;
    private String storyContent;
    private String taggeditemImage;
    private String taggedItemTitle;
    private String taggedItemPrice;
    private String profileImage;
    private MultipartFile mainImage; // 메인 이미지
    private MultipartFile contentImage; //컨텐츠 이미지
    private Integer productId;
	
}

