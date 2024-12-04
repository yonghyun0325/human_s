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
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.PinquiryEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.PinquiryRepository;
import com.human.hms.vo.PinquiryVO;

@Service
public class PinquiryServiceImpl implements PinquiryService {

    @Autowired
    private PinquiryRepository pinquiryRepository;

    @Override
    public List<PinquiryEntity> getAllPinquirys() {
        return pinquiryRepository.findAll();
    }

    @Override
    public PinquiryEntity createPinquiry(PinquiryVO pinquiryVO) {
        PinquiryEntity entity = convertToEntity(pinquiryVO);
        return pinquiryRepository.save(entity);
    }

    @Override
    public Optional<PinquiryEntity> getPinquiryById(long pinquiryIdx) {
        return pinquiryRepository.findById(pinquiryIdx);
    }

    @Transactional
    public int updatePinquiry(PinquiryVO vo) {
        Optional<PinquiryEntity> optional = pinquiryRepository.findById(vo.getPinquiryIdx());
        
        if (optional.isPresent()) {
            PinquiryEntity entity = optional.get();
            entity.updatePinquiryTitle(vo.getPinquiryTitle());
            entity.updatePinquiryContent(vo.getPinquiryContent());
            pinquiryRepository.save(entity); // 수정된 엔티티를 저장하여 업데이트 쿼리를 발생시킴
            return 1; // 성공 시 1 반환
        }
        
        return 0; // 수정할 데이터가 없을 경우 0 반환
    }

    @Override
    public int deletePinquiry(long pinquiryId) {
        if (pinquiryRepository.existsById(pinquiryId)) {
            pinquiryRepository.deleteById(pinquiryId);
            return 1;
        }
        return 0;
    }
    


    @Override
    public void updateReadCount(long pinquiryIdx) {
        pinquiryRepository.findById(pinquiryIdx).ifPresent(entity -> {        
            pinquiryRepository.plusviews(entity.getId());
        });
    }

    private PinquiryEntity convertToEntity(PinquiryVO vo) {
        return PinquiryEntity.builder()
                .id(vo.getId())
                .author(vo.getAuthor())
                .createdDate(vo.getCreatedDate())                
                .views(vo.getViews())
                .pinquiryContent(vo.getPinquiryContent())               
                .pinquiryTitle(vo.getPinquiryTitle())
                .build();
    }

    // 페이징 처리된 공지사항 목록 조회
  

    @Override
    public int getTotalPinquiryCount() {
        return (int) pinquiryRepository.count();
    }

    @Override
    public List<PinquiryEntity> searchPinquirys(String searchType, String searchKeyword,int page, int pageSize) {
    	
    	 Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id")); // id 기준 내림차순 정렬
        // searchType에 맞는 메서드를 호출
        switch (searchType) {
            case "name":
                return pinquiryRepository.findByAuthor(searchKeyword,pageable); // 작성자 검색
            case "title":
                return pinquiryRepository.findByTitle(searchKeyword,pageable);  // 제목 검색
            case "content":
                return pinquiryRepository.findByContent(searchKeyword,pageable);  // 내용 검색
            default:
                return pinquiryRepository.findAll(pageable).getContent();
        }
    }

	//글등록
    @Override
    public PinquiryEntity insertPinquiry(PinquiryEntity vo, HttpServletRequest request) {
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
        return pinquiryRepository.save(vo); // JPA의 save 메서드를 사용하여 저장
    }

    //고객문의 답변
    @Transactional
	@Override
	public PinquiryEntity updateComment(String comment, Long id) {
    	PinquiryEntity pinquriy = null; 
    	
    	if(pinquiryRepository.updateComment(comment, id) == 1) {
    		pinquriy = pinquiryRepository.findById(id).get();
    	}
    	
		return pinquriy;
	}
    

		
}
