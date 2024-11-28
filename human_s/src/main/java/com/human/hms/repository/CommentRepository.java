package com.human.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.human.hms.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	//스토리에 맞는 댓글 조회
	@Query("select c from CommentEntity c "
			+ "where c.cinquiryEntity.id = :cinquiryId ")
	List<CommentEntity> getCommentBycinquiry(@Param("cinquiryId")Long cinquiryId);

}
