package com.human.hms.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.StoryEntity;

public interface StoryRepository extends JpaRepository<StoryEntity, Long> {
	@Transactional
	@Modifying
	@Query("UPDATE StoryEntity n SET n.views = n.views + 1 WHERE n.id = :id")
    void plusviews(@Param("id") Long id);

    // 이름으로 검색
    @Query("SELECT n FROM StoryEntity n WHERE n.author LIKE %:keyword%")
    List<StoryEntity> findByAuthor(@Param("keyword") String keyword, Pageable pageable);

    // 제목으로 검색
    @Query("SELECT n FROM StoryEntity n WHERE n.storyTitle LIKE %:keyword%")
    List<StoryEntity> findByTitle(@Param("keyword") String keyword, Pageable pageable);

    // 내용으로 검색
    @Query("SELECT n FROM StoryEntity n WHERE n.storyContent LIKE %:keyword%")
    List<StoryEntity> findByContent(@Param("keyword") String keyword, Pageable pageable);

}
