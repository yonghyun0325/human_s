package com.human.hms.vo;

import lombok.Data;
import java.util.Date;

@Data
public class ReviewVO {
    private Long id;
    private int rating;
    private String author;
    private String reviewTitle;
    private String reviewContent;
    private Date createdDate;
    private int views;
    private Long reviewIdx;
    private Long userIdx; // UserEntity ID를 위한 필드
	
	}

	

