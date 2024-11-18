package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;

import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.StoryEntity;
import com.human.hms.vo.StoryVO;

public interface StoryService {

    int getTotalStoryCount(); // 전체 스토리 개수 반환

    StoryEntity createStory(StoryVO storyVO); // StoryVO를 받아 새로운 스토리 생성

    Optional<StoryEntity> getStoryById(long storyId); // ID로 특정 스토리 조회

    int updateStory(StoryVO storyVO); // StoryVO를 받아 스토리 업데이트

    int deleteStory(long storyId); // 스토리를 삭제하고 성공 시 1, 실패 시 0 반환

    void updateReadCount(long storyId); // 스토리 조회수 증가

    List<StoryEntity> getAllStories(); // 전체 스토리 목록 반환

    List<StoryEntity> searchStories(String searchType, String searchKeyword, int page, int pageSize); // 검색 조건으로 스토리 조회

    StoryEntity insertStory(StoryEntity entity, HttpServletRequest request); // 사용자 정보를 포함해 스토리 생성
    
    List<StoryEntity> getStories(Pageable pageable);

	void save(StoryEntity story);

}
