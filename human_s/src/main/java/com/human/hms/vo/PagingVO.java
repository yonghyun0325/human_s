package com.human.hms.vo;

import lombok.Data;

@Data
public class PagingVO {
    private int currentPage;    // 현재 페이지
    private int pageSize;       // 한 페이지당 게시물 수
    private int totalCount;     // 총 게시물 수
    private int totalPages;     // 총 페이지 수
    private int startPage;      // 시작 페이지 번호
    private int endPage;        // 끝 페이지 번호

    public PagingVO(int currentPage, int pageSize, int totalCount) {
        this.currentPage = Math.max(currentPage, 1); // 최소 1 페이지로 설정
        this.pageSize = pageSize;
        this.totalCount = totalCount;

        // 총 페이지 수 계산
        this.totalPages = (int) Math.ceil((double) totalCount / pageSize);

        // 페이지네이션 범위 계산
        int pageBlockSize = 5; // 한 화면에 표시할 페이지 수
        this.startPage = ((this.currentPage - 1) / pageBlockSize) * pageBlockSize + 1;
        this.endPage = Math.min(startPage + pageBlockSize - 1, totalPages);
    }
}
