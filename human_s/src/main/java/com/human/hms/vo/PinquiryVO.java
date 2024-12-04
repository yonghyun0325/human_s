package com.human.hms.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
    private int pdtIdx;
}

  
