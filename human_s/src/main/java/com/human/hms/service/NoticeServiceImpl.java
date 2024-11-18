package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.NoticeEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.NoticeRepository;
import com.human.hms.vo.NoticeVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	
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

    @Transactional
    public int updateNotice(NoticeVO vo) {
        Optional<NoticeEntity> optional = noticeRepository.findById(vo.getNoticeIdx());
        
        if (optional.isPresent()) {
            NoticeEntity entity = optional.get();
            entity.updateNoticeTitle(vo.getNoticeTitle());
            entity.updateNoticeContent(vo.getNoticeContent());
            noticeRepository.save(entity); // 수정된 엔티티를 저장하여 업데이트 쿼리를 발생시킴
            return 1; // 성공 시 1 반환
        }
        
        return 0; // 수정할 데이터가 없을 경우 0 반환
    }

    @Override
    public int deleteNotice(long noticeId) {
        if (noticeRepository.existsById(noticeId)) {
            noticeRepository.deleteById(noticeId);
            return 1;
        }
        return 0;
    }
    


    @Override
    public void updateReadCount(long noticeIdx) {
        noticeRepository.findById(noticeIdx).ifPresent(entity -> {        
            noticeRepository.plusviews(entity.getId());
        });
    }

    private NoticeEntity convertToEntity(NoticeVO vo) {
        return NoticeEntity.builder()
                .id(vo.getId())
                .author(vo.getAuthor())
                .createdDate(vo.getCreatedDate())                
                .views(vo.getViews())
                .noticeContent(vo.getNoticeContent())               
                .noticeTitle(vo.getNoticeTitle())
                .build();
    }

    // 페이징 처리된 공지사항 목록 조회
  

    @Override
    public int getTotalNoticeCount() {
        return (int) noticeRepository.count();
    }

    @Override
    public List<NoticeEntity> searchNotices(String searchType, String searchKeyword,int page, int pageSize) {
    	
    	 Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id")); // id 기준 내림차순 정렬
        // searchType에 맞는 메서드를 호출
        switch (searchType) {
            case "name":
                return noticeRepository.findByAuthor(searchKeyword,pageable); // 작성자 검색
            case "title":
                return noticeRepository.findByTitle(searchKeyword,pageable);  // 제목 검색
            case "content":
                return noticeRepository.findByContent(searchKeyword,pageable);  // 내용 검색
            default:
                return noticeRepository.findAll(pageable).getContent();
        }
    }

  //글등록
    @Override
    public NoticeEntity insertNotice(NoticeEntity vo, HttpServletRequest request) {
        // 세션에서 UserEntity를 가져옴
        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        // 세션에 UserEntity가 없는 경우 처리
        if (userEntity == null) {
            throw new IllegalStateException("로그인된 사용자 정보가 없습니다."); // 예외 처리
        }

        // ReviewEntity에 UserEntity 설정
        vo.updateUserEntity(userEntity);
        

        // ReviewEntity를 저장
        return noticeRepository.save(vo); // JPA의 save 메서드를 사용하여 저장
    }
    

		
}
