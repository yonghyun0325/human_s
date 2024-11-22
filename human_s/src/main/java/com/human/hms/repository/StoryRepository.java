package com.human.hms.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.StoryEntity;
import com.human.hms.entity.UserEntity;

public interface StoryRepository extends JpaRepository<StoryEntity, Long> {
	@Transactional
	@Modifying
	@Query("UPDATE StoryEntity n SET n.views = n.views + 1 WHERE n.id = :id")
    void plusviews(@Param("id") Long id);
	
    @Query("SELECT s FROM StoryEntity s JOIN FETCH s.product")
    List<StoryEntity> findAll();

    // 이름으로 검색
    @Query("SELECT n FROM StoryEntity n WHERE n.author LIKE %:keyword%")
    List<StoryEntity> findByAuthor(@Param("keyword") String keyword, Pageable pageable);

    // 제목으로 검색
    @Query("SELECT n FROM StoryEntity n WHERE n.storyTitle LIKE %:keyword%")
    List<StoryEntity> findByTitle(@Param("keyword") String keyword, Pageable pageable);

    // 내용으로 검색
    @Query("SELECT n FROM StoryEntity n WHERE n.storyContent LIKE %:keyword%")
    List<StoryEntity> findByContent(@Param("keyword") String keyword, Pageable pageable);

    // 태그상품 입력
//    @Modifying
//    @Transactional
//    @Query("INSERT INTO StoryEntity (mainImage, contentImage, taggedItemTitle, taggedItemPrice, " +
//           "profileImage, author, storyTitle, storyContent, userEntity, product) " +
//           
//           "SELECT :mainImage, :contentImage, p.pdtTitle, p.pdtPrice, " +
//           ":profileImage, :author, :storyTitle, :storyContent, :userEntity, p " + 
//    		"FROM ProductEntity p WHERE p.pdtIdx = :productId")
//    void saveStory(
//            @Param("mainImage") String mainImage,
//            @Param("contentImage") String contentImage,
//            @Param("profileImage") String profileImage,
//            @Param("author") String author,
//            @Param("storyTitle") String storyTitle,
//            @Param("storyContent") String storyContent,
//            @Param("userEntity") UserEntity userEntity,
//            @Param("productId") Integer productId);
    
    @Modifying
    @Transactional
    @Query(" INSERT INTO StoryEntity (mainImage, contentImage, taggedItemTitle, taggedItemPrice, " +
           " profileImage, author, storyTitle, storyContent, userEntity, product) " +
           
           " SELECT ?1, ?2, p.pdtTitle, p.pdtPrice, ?3, ?4, ?5, ?6, ?7, p " + 
    		" FROM ProductEntity p WHERE p.pdtIdx = ?8 ")
    void saveStory(String mainImage,
            String contentImage,
            String profileImage,
            String author,
            String storyTitle,
            String storyContent,
            UserEntity userEntity,
            Integer productId);


}
