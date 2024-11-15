package com.human.hms.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.CinquiryEntity;

public interface CinquiryRepository extends JpaRepository<CinquiryEntity, Long> {
	@Transactional
	@Modifying
	@Query("UPDATE CinquiryEntity n SET n.views = n.views + 1 WHERE n.id = :id")
    void plusviews(@Param("id") Long id);

    // 이름으로 검색
    @Query("SELECT n FROM CinquiryEntity n WHERE n.author LIKE %:keyword%")
    List<CinquiryEntity> findByAuthor(@Param("keyword") String keyword, Pageable pageable);

    // 제목으로 검색
    @Query("SELECT n FROM CinquiryEntity n WHERE n.cinquiryTitle LIKE %:keyword%")
    List<CinquiryEntity> findByTitle(@Param("keyword") String keyword, Pageable pageable);

    // 내용으로 검색
    @Query("SELECT n FROM CinquiryEntity n WHERE n.cinquiryContent LIKE %:keyword%")
    List<CinquiryEntity> findByContent(@Param("keyword") String keyword, Pageable pageable);


	
}
