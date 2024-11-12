package com.human.hms.entity;

import javax.persistence.*;
import lombok.*;
import java.sql.Date;

@Entity
@Table(name = "notice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "notice_cnt")
    private int noticeCnt;

    @Column(name = "notice_content")
    private String noticeContent;

    @Column(name = "notice_date")
    private Date noticeDate;

    // 필요한 경우 필드 추가
    @Column(name = "notice_idx")
    private Long noticeIdx;

    @Column(name = "notice_title")
    private String noticeTitle;

    @Column(name = "title")
    private String title;

    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "views")
    private int views;
}
