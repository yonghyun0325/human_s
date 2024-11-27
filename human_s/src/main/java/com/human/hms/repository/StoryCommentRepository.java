package com.human.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.human.hms.entity.StoryCommentEntity;

public interface StoryCommentRepository extends JpaRepository<StoryCommentEntity, Integer>{

	//스토리에 맞는 댓글 조회
	@Query("select sc from StoryCommentEntity sc "
			+ "where sc.storyEntity.id = :storyId ")
	List<StoryCommentEntity> getCommentBystory(@Param("storyId")Long storyId);

}
