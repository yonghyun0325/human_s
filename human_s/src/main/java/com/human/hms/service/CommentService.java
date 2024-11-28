package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import com.human.hms.entity.CinquiryEntity;
import com.human.hms.entity.CommentEntity;

public interface CommentService {



    //고객문의 댓글 저장
	CommentEntity insertComment(CommentEntity entity);

	//고객문의에 맞는 댓글 조회
	List<CommentEntity> getCommentBycinquiry(Long cinquiryId);

	//고객문의 댓글 삭제
	void deleteComment(int ccIdx);
}
	