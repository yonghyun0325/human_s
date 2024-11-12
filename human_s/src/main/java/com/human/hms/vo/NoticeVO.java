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
public class NoticeVO {
    private Long id;
    private String author;
    private Date createdDate;
    private String title;
    private Integer views;
    private Long noticeIdx;
    private Integer noticeCnt;
    private String noticeContent;
    private Date noticeDate;
    private String noticeTitle;
    private Long userIdx;
}

  
