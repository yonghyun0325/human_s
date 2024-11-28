package com.human.hms.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {
    private Long cinquiryId;   // 게시물 ID
    private String content;    // 댓글 내용
    private String author;
}
