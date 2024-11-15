package com.human.hms.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CinquiryVO {
    private Long id;
    private String author;
    private Date createdDate;
    private String title;
    private Integer views;
    private Long cinquiryIdx;
    private Integer cinquiryCnt;
    private String cinquiryContent;
    private Date cinquiryDate;
    private String cinquiryTitle;
    private Long userIdx;
}

  
