package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.human.hms.entity.NoticeEntity;
import com.human.hms.vo.NoticeVO;

public interface NoticeService {

    int getTotalNoticeCount();

    NoticeEntity createNotice(NoticeVO noticeVO); // NoticeVO를 받아서 처리

    Optional<NoticeEntity> getNoticeById(long noticeIdx); // long 타입 사용

    int updateNotice(NoticeVO noticeVO); // NoticeVO를 받아서 처리

    int deleteNotice(long noticeIdx); // long 타입 사용

    void updateReadCount(long noticeIdx); // 조회수 증가 메서드에서 long 타입 사용

    List<NoticeEntity> getNotices(int page, int pageSize);


	List<NoticeEntity> getAllNotices();

	



	

}
