package com.human.hms.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class StoryVO {
    private Long id;
    private String author;
    private String storyTitle;
    private String storyContent;
    private String taggedItemTitle;
    private String taggedItemPrice;
    private String profileImage;
    private MultipartFile image; // 단일 파일 업로드를 위한 필드
    private MultipartFile[] uploadFiles; // 여러 파일 업로드 처리
}

