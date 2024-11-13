package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.human.hms.entity.NoticeEntity;
import com.human.hms.entity.UserEntity;
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
        
        optional.ifPresent(entity ->{
        	noticeRepository.save(entity);
        });
        return 1;
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
		//BoardEntity의 필드인 MemberEntity가 null값을 가지고 입력되므로
		//MemberEntity에 실제 객체를 세팅해주어야함
		//request객체를 이용해서 session에 저장된 MemberEntity객체를 가져와서
		//세팅해줌
		HttpSession session = request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		vo.updateUserEntity(userEntity);

		return noticeRepository.save(vo);//JPA에서 제공되는 메소드 이용
	}
    

		
}
