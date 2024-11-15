package com.human.hms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.hms.entity.StoryEntity;
import com.human.hms.service.StoryService;

@Controller
@RequestMapping("/story")
public class StoryController {

    @Autowired
    private StoryService storyService;

    // 모든 스토리 조회
    @GetMapping("/farmstory.no")
    public String getStories(Model model) {
        List<StoryEntity> stories = storyService.getAllStories();
        model.addAttribute("stories", stories);
        return "story/story_list";  // 뷰 파일 경로가 board/story_list.jsp인지 확인
    }
    
 // 무한 스크롤용 페이징 처리된 스토리 목록 가져오기
    @GetMapping("/loadStories")
    @ResponseBody
    public List<StoryEntity> loadStories(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return storyService.getStories(pageable);
    }
    
    @GetMapping("/view")
    public String viewStory(@RequestParam("storyId") Long storyId, Model model) {
        Optional<StoryEntity> story = storyService.getStoryById(storyId);
        if (story.isPresent()) {
            model.addAttribute("story", story.get());
            return "story/story_view";
        } else {
            return "error/404"; // 스토리가 없으면 404 페이지로 이동
        }
    }
}
