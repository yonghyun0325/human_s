package com.human.hms.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.StoryEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.service.ProductService;
import com.human.hms.service.StoryService;
import com.human.hms.vo.StoryVO;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/story")
@AllArgsConstructor
public class StoryController {

    private final StoryService storyService;
    private final ProductService productService;

    // 스토리 작성 페이지 요청
    @GetMapping("/story/write.do")
    public ModelAndView storyWrite(ModelAndView mav, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        if (userEntity == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }

        List<ProductEntity> products = productService.getProductsByUserId(userEntity.getUserIdx());
        mav.addObject("products", products);
        mav.setViewName("story/story_write");
        return mav;
    }

    // 글 작성 요청 처리
    @PostMapping("/create")
    public ModelAndView storyWriteProcess(StoryVO vo, HttpServletRequest request, ModelAndView mav) {
        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        if (userEntity == null) {
            mav.setViewName("redirect:/login");
            return mav;
        }

        try {
            // 업로드 디렉토리 경로
            String uploadDir = request.getServletContext().getRealPath("/resources/uploads/");
            String mainImagePath = null;
            String contentImagePath = null;

            // 메인 이미지 처리
            MultipartFile mainImage = vo.getMainImage();
            if (mainImage != null && !mainImage.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + mainImage.getOriginalFilename();
                File destination = new File(uploadDir, fileName);
                mainImage.transferTo(destination);
                mainImagePath = "/resources/uploads/" + fileName; // 저장된 메인 이미지 경로
            }

            // 컨텐츠 이미지 처리 (단일 파일)
            MultipartFile contentImage = vo.getContentImage(); // 단일 파일 처리
            if (contentImage != null && !contentImage.isEmpty()) {
                String fileName = System.currentTimeMillis() + "_" + contentImage.getOriginalFilename();
                File destination = new File(uploadDir, fileName);
                contentImage.transferTo(destination);
                contentImagePath = "/resources/uploads/" + fileName; // 저장된 컨텐츠 이미지 경로
            }

            // 엔티티 빌드 및 저장
            StoryEntity entity = StoryEntity.builder()
                    .author(vo.getAuthor())
                    .storyTitle(vo.getStoryTitle())
                    .storyContent(vo.getStoryContent())
                    .taggedItemTitle(vo.getTaggedItemTitle())
                    .taggedItemPrice(vo.getTaggedItemPrice())
                    .mainImage(mainImagePath) // 메인 이미지 경로 저장
                    .contentImage(contentImagePath) // 단일 컨텐츠 이미지 경로 저장
                    .userEntity(userEntity)
                    .build();

            storyService.insertStory(entity, request);

            mav.setViewName("redirect:/story/farmstory.no");
        } catch (Exception e) {
            e.printStackTrace();
            mav.setViewName("story/story_write");
            mav.addObject("errorMessage", "파일 업로드 중 오류가 발생했습니다.");
        }

        return mav;
    }

    // 모든 스토리 조회
    @GetMapping("/farmstory.no")
    public String getStories(Model model) {
        List<StoryEntity> stories = storyService.getAllStories();
        model.addAttribute("stories", stories);
        return "story/story_list";
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
