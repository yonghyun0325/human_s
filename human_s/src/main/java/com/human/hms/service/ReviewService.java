package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.human.hms.entity.ReviewEntity;
import com.human.hms.vo.ReviewVO;

public interface ReviewService {

    int getTotalReviewCount();

    ReviewEntity createReview(ReviewVO reviewVO); // ReviewVO를 받아서 처리

    Optional<ReviewEntity> getReviewById(long reviewIdx); // long 타입 사용

    int updateReview(ReviewVO reviewVO); // ReviewVO를 받아서 처리

    int deleteReview(long reviewId); // long 타입 사용

    void updateReadCount(long reviewId); // 조회수 증가 메서드에서 long 타입 사용

  

	List<ReviewEntity> getAllReviews();

	List<ReviewEntity> searchReviews(String searchType, String searchKeyword, int page, int pageSize);

	ReviewEntity insertReview(ReviewEntity entity, HttpServletRequest request);
	
	
}

	
