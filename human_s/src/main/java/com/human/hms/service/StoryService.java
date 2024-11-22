package com.human.hms.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.human.hms.entity.StoryEntity;
import com.human.hms.vo.StoryVO;

public interface StoryService {

    // 전체 스토리 개수 반환
    int getTotalStoryCount();

    // 새 스토리 생성 (VO 기반)
    StoryEntity createStory(StoryVO storyVO);

    // ID로 스토리 조회
    Optional<StoryEntity> getStoryById(long storyId);

    // 스토리 업데이트
    int updateStory(StoryVO storyVO);

    // 스토리 삭제
    int deleteStory(long storyId);

    // 조회수 업데이트
    void updateReadCount(long storyId);

    // 전체 스토리 조회
    List<StoryEntity> getAllStories();

    // 스토리 저장 (Entity 기반)
    void insertStory(StoryEntity entity, HttpServletRequest request);
}
