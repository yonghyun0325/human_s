package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.human.hms.entity.PinquiryEntity;
import com.human.hms.vo.PinquiryVO;

public interface PinquiryService {

    int getTotalPinquiryCount();

    PinquiryEntity createPinquiry(PinquiryVO pinquiryVO); // PinquiryVO를 받아서 처리

    Optional<PinquiryEntity> getPinquiryById(long pinquiryIdx); // long 타입 사용

    int updatePinquiry(PinquiryVO pinquiryVO); // PinquiryVO를 받아서 처리

    int deletePinquiry(long pinquiryId); // long 타입 사용

    void updateReadCount(long pinquiryId); // 조회수 증가 메서드에서 long 타입 사용

  

	List<PinquiryEntity> getAllPinquirys();

	List<PinquiryEntity> searchPinquirys(String searchType, String searchKeyword, int page, int pageSize);

	PinquiryEntity insertPinquiry(PinquiryEntity entity, HttpServletRequest request);

	PinquiryEntity updateComment(String comment, Long id);

	



	

}
