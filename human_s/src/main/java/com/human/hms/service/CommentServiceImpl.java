package com.human.hms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.human.hms.entity.CommentEntity;
import com.human.hms.repository.CinquiryRepository;
import com.human.hms.repository.CommentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    
    private CommentRepository commentRepository;

    //고객문의 댓글 저장
	@Override
	public CommentEntity insertComment(CommentEntity entity) {
		return commentRepository.save(entity);
	}

	//고객문의에 맞는 댓글 조회
	@Override
	public List<CommentEntity> getCommentBycinquiry(Long cinquiryId) {
		return commentRepository.getCommentBycinquiry(cinquiryId);
	}

	//고객문의 댓글 삭제
	@Override
	public void deleteComment(int cIdx) {
		commentRepository.deleteById(cIdx);
	}
}
