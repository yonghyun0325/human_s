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

import com.human.hms.entity.CinquiryEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.CinquiryRepository;
import com.human.hms.vo.CinquiryVO;

@Service
public class CinquiryServiceImpl implements CinquiryService {

    @Autowired
    private CinquiryRepository cinquiryRepository;

    @Override
    public List<CinquiryEntity> getAllCinquirys() {
        return cinquiryRepository.findAll();
    }

    @Override
    public CinquiryEntity createCinquiry(CinquiryVO cinquiryVO) {
        CinquiryEntity entity = convertToEntity(cinquiryVO);
        return cinquiryRepository.save(entity);
    }

    @Override
    public Optional<CinquiryEntity> getCinquiryById(long cinquiryIdx) {
        return cinquiryRepository.findById(cinquiryIdx);
    }

    @Transactional
    public int updateCinquiry(CinquiryVO vo) {
        Optional<CinquiryEntity> optional = cinquiryRepository.findById(vo.getCinquiryIdx());
        
        if (optional.isPresent()) {
            CinquiryEntity entity = optional.get();
            entity.updateCinquiryTitle(vo.getCinquiryTitle());
            entity.updateCinquiryContent(vo.getCinquiryContent());
            cinquiryRepository.save(entity); // 수정된 엔티티를 저장하여 업데이트 쿼리를 발생시킴
            return 1; // 성공 시 1 반환
        }
        
        return 0; // 수정할 데이터가 없을 경우 0 반환
    }

    @Override
    public int deleteCinquiry(long cinquiryId) {
        if (cinquiryRepository.existsById(cinquiryId)) {
            cinquiryRepository.deleteById(cinquiryId);
            return 1;
        }
        return 0;
    }
    


    @Override
    public void updateReadCount(long cinquiryIdx) {
        cinquiryRepository.findById(cinquiryIdx).ifPresent(entity -> {        
            cinquiryRepository.plusviews(entity.getId());
        });
    }

    private CinquiryEntity convertToEntity(CinquiryVO vo) {
        return CinquiryEntity.builder()
                .id(vo.getId())
                .author(vo.getAuthor())
                .createdDate(vo.getCreatedDate())                
                .views(vo.getViews())
                .cinquiryContent(vo.getCinquiryContent())               
                .cinquiryTitle(vo.getCinquiryTitle())
                .build();
    }

    // 페이징 처리된 공지사항 목록 조회
  

    @Override
    public int getTotalCinquiryCount() {
        return (int) cinquiryRepository.count();
    }

    @Override
    public List<CinquiryEntity> searchCinquirys(String searchType, String searchKeyword,int page, int pageSize) {
    	
    	 Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id")); // id 기준 내림차순 정렬
        // searchType에 맞는 메서드를 호출
        switch (searchType) {
            case "name":
                return cinquiryRepository.findByAuthor(searchKeyword,pageable); // 작성자 검색
            case "title":
                return cinquiryRepository.findByTitle(searchKeyword,pageable);  // 제목 검색
            case "content":
                return cinquiryRepository.findByContent(searchKeyword,pageable);  // 내용 검색
            default:
                return cinquiryRepository.findAll(pageable).getContent();
        }
    }

	//글등록
	@Override
	public CinquiryEntity insertCinquiry(CinquiryEntity vo, HttpServletRequest request) {
		//BoardEntity의 필드인 MemberEntity가 null값을 가지고 입력되므로
		//MemberEntity에 실제 객체를 세팅해주어야함
		//request객체를 이용해서 session에 저장된 MemberEntity객체를 가져와서
		//세팅해줌
		HttpSession session = request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		vo.updateUserEntity(userEntity);

		return cinquiryRepository.save(vo);//JPA에서 제공되는 메소드 이용
	}
    

		
}
