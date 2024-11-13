package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import com.human.hms.entity.NoticeEntity;
import com.human.hms.vo.NoticeVO;
import com.mysql.cj.protocol.x.Notice;

public interface NoticeService {

    int getTotalNoticeCount();

    NoticeEntity createNotice(NoticeVO noticeVO); // NoticeVO를 받아서 처리

    Optional<NoticeEntity> getNoticeById(long noticeIdx); // long 타입 사용

    int updateNotice(NoticeVO noticeVO); // NoticeVO를 받아서 처리

    int deleteNotice(long noticeId); // long 타입 사용

    void updateReadCount(long noticeId); // 조회수 증가 메서드에서 long 타입 사용

  

	List<NoticeEntity> getAllNotices();

	List<NoticeEntity> searchNotices(String searchType, String searchKeyword, int page, int pageSize);

	NoticeEntity insertNotice(NoticeEntity entity, HttpServletRequest request);

	



	

}
