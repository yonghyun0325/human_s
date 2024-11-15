package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.human.hms.entity.CinquiryEntity;
import com.human.hms.vo.CinquiryVO;

public interface CinquiryService {

    int getTotalCinquiryCount();

    CinquiryEntity createCinquiry(CinquiryVO cinquiryVO); // CinquiryVO를 받아서 처리

    Optional<CinquiryEntity> getCinquiryById(long cinquiryIdx); // long 타입 사용

    int updateCinquiry(CinquiryVO cinquiryVO); // CinquiryVO를 받아서 처리

    int deleteCinquiry(long cinquiryId); // long 타입 사용

    void updateReadCount(long cinquiryId); // 조회수 증가 메서드에서 long 타입 사용

  

	List<CinquiryEntity> getAllCinquirys();

	List<CinquiryEntity> searchCinquirys(String searchType, String searchKeyword, int page, int pageSize);

	CinquiryEntity insertCinquiry(CinquiryEntity entity, HttpServletRequest request);

	



	

}
