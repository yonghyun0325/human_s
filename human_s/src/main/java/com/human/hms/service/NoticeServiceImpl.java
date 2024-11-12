package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.human.hms.entity.NoticeEntity;
import com.human.hms.repository.NoticeRepository;
import com.human.hms.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public List<NoticeEntity> getAllNotices() {
        return noticeRepository.findAll();
    }

    @Override
    public NoticeEntity createNotice(NoticeVO noticeVO) {
        NoticeEntity entity = convertToEntity(noticeVO);
        return noticeRepository.save(entity);
    }

    @Override
    public Optional<NoticeEntity> getNoticeById(long noticeIdx) {
        return noticeRepository.findById(noticeIdx);
    }

    @Override
    public int updateNotice(NoticeVO noticeVO) {
        Optional<NoticeEntity> optional = noticeRepository.findById(noticeVO.getNoticeIdx());
        if (optional.isPresent()) {
            NoticeEntity entity = optional.get();
            entity.setId(noticeVO.getId());
            entity.setAuthor(noticeVO.getAuthor());
            entity.setCreatedDate(noticeVO.getCreatedDate());
            entity.setTitle(noticeVO.getTitle());
            entity.setViews(noticeVO.getViews());
            entity.setNoticeIdx(noticeVO.getNoticeIdx());
            entity.setNoticeCnt(noticeVO.getNoticeCnt());
            entity.setNoticeContent(noticeVO.getNoticeContent());
            entity.setNoticeDate(noticeVO.getNoticeDate());
            entity.setNoticeTitle(noticeVO.getNoticeTitle());
            entity.setUserIdx(noticeVO.getUserIdx());
            noticeRepository.save(entity);
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteNotice(long noticeIdx) {
        if (noticeRepository.existsById(noticeIdx)) {
            noticeRepository.deleteById(noticeIdx);
            return 1;
        }
        return 0;
    }

    @Override
    public void updateReadCount(long noticeIdx) {
        noticeRepository.findById(noticeIdx).ifPresent(entity -> {
            entity.setNoticeCnt(entity.getNoticeCnt() + 1);
            noticeRepository.save(entity);
        });
    }

    private NoticeEntity convertToEntity(NoticeVO vo) {
        return NoticeEntity.builder()
                .id(vo.getId())
                .author(vo.getAuthor())
                .createdDate(vo.getCreatedDate())
                .title(vo.getTitle())
                .views(vo.getViews())
                .noticeIdx(vo.getNoticeIdx())
                .noticeCnt(vo.getNoticeCnt())
                .noticeContent(vo.getNoticeContent())
                .noticeDate(vo.getNoticeDate())
                .noticeTitle(vo.getNoticeTitle())
                .userIdx(vo.getUserIdx())
                .build();
    }

    // 페이징 처리된 공지사항 목록 조회
    @Override
    public List<NoticeEntity> getNotices(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id")); // id 기준 내림차순 정렬
        Page<NoticeEntity> noticePage = noticeRepository.findAll(pageable);
        return noticePage.getContent();
    }

    @Override
    public int getTotalNoticeCount() {
        return (int) noticeRepository.count();
    }
    

		
}
