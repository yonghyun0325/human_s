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

import com.human.hms.entity.ReviewEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.ReviewRepository;
import com.human.hms.vo.ReviewVO;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<ReviewEntity> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public ReviewEntity createReview(ReviewVO reviewVO) {
        ReviewEntity entity = convertToEntity(reviewVO);
        return reviewRepository.save(entity);
    }

    @Override
    public Optional<ReviewEntity> getReviewById(long reviewIdx) {
        return reviewRepository.findById(reviewIdx);
    }

    @Transactional
    public int updateReview(ReviewVO vo) {
        Optional<ReviewEntity> optional = reviewRepository.findById(vo.getReviewIdx());
        
        if (optional.isPresent()) {
            ReviewEntity entity = optional.get();
            entity.updateReviewTitle(vo.getReviewTitle());
            entity.updateReviewContent(vo.getReviewContent());
            entity.updateRating(vo.getRating());
            reviewRepository.save(entity); // 수정된 엔티티를 저장하여 업데이트 쿼리를 발생시킴
            return 1; // 성공 시 1 반환
        }
        
        return 0; // 수정할 데이터가 없을 경우 0 반환
    }

    @Override
    public int deleteReview(long reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
            return 1;
        }
        return 0;
    }
    


    @Override
    public void updateReadCount(long reviewIdx) {
        reviewRepository.findById(reviewIdx).ifPresent(entity -> {        
            reviewRepository.plusviews(entity.getId());
        });
    }

    private ReviewEntity convertToEntity(ReviewVO vo) {
        return ReviewEntity.builder()
                .id(vo.getId())
                .author(vo.getAuthor())
                .createdDate(vo.getCreatedDate())                
                .views(vo.getViews())
                .reviewContent(vo.getReviewContent())               
                .reviewTitle(vo.getReviewTitle())
                .rating(vo.getRating())
                .build();
    }

    // 페이징 처리된 공지사항 목록 조회
  

    @Override
    public int getTotalReviewCount() {
        return (int) reviewRepository.count();
    }

    @Override
    public List<ReviewEntity> searchReviews(String searchType, String searchKeyword,int page, int pageSize) {
    	
    	 Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id")); // id 기준 내림차순 정렬
        // searchType에 맞는 메서드를 호출
        switch (searchType) {
            case "name":
                return reviewRepository.findByAuthor(searchKeyword,pageable); // 작성자 검색
            case "title":
                return reviewRepository.findByTitle(searchKeyword,pageable);  // 제목 검색
            case "content":
                return reviewRepository.findByContent(searchKeyword,pageable);  // 내용 검색
            default:
                return reviewRepository.findAll(pageable).getContent();
        }
    }

	//글등록
	@Override
	public ReviewEntity insertReview(ReviewEntity vo, HttpServletRequest request) {
		//BoardEntity의 필드인 MemberEntity가 null값을 가지고 입력되므로
		//MemberEntity에 실제 객체를 세팅해주어야함
		//request객체를 이용해서 session에 저장된 MemberEntity객체를 가져와서
		//세팅해줌
		HttpSession session = request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		vo.updateUserEntity(userEntity);

		return reviewRepository.save(vo);//JPA에서 제공되는 메소드 이용
	}
    

	
}
