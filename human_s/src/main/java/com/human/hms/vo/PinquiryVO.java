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
public class PinquiryVO {
    private Long id;
    private String author;
    private Date createdDate;
    private String title;
    private Integer views;
    private Long pinquiryIdx;
    private Integer pinquiryCnt;
    private String pinquiryContent;
    private Date pinquiryDate;
    private String pinquiryTitle;
    private Long userIdx;
}

  
