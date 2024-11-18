package com.human.hms.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.StoryEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.service.ProductService;
import com.human.hms.service.StoryService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/story")
@AllArgsConstructor
public class StoryController {

	private StoryService storyService;
	private ProductService productService;

	// 스토리 작성 페이지 요청
	
	/*
	 * @GetMapping("/story/write.do") public ModelAndView storyWrite(ModelAndView
	 * mav, HttpServletRequest request) { mav.setViewName("story/story_write");
	 * 
	 * HttpSession session = request.getSession(); UserEntity userEntity =
	 * (UserEntity) session.getAttribute("user");
	 * 
	 * // 해당 사용자의 상품 목록 조회 List<ProductEntity> products =
	 * productService.getProductsByUserId(userEntity.getUserIdx());
	 * 
	 * //모델에 상품 목록 추가 mav.addObject("products", products);
	 * 
	 * return mav; }
	 */

	
	
	  // 스토리 작성 페이지 요청
	  
	  @GetMapping("/story/write.do") public ModelAndView storyWrite(ModelAndView
	  mav, HttpServletRequest request) { HttpSession session =
	  request.getSession(); UserEntity userEntity = (UserEntity)
	  session.getAttribute("user");
	  
	  if (userEntity == null) { mav.setViewName("redirect:/login"); return mav; }
	  System.out.println("UserEntity: " + userEntity);
	  
	  List<ProductEntity> products =
	  productService.getProductsByUserId(userEntity.getUserIdx());
	  mav.addObject("products", products); mav.addObject("grade",
	  userEntity.getGrade()); // grade 값 전달
	  
	  System.out.println("grade: " + userEntity.getGrade());
	  
	  mav.setViewName("story/story_write"); return mav; }
	 
	 

	  @PostMapping("/create")
	  public String createStory(
	          @RequestParam("title") String title,
	          @RequestParam("content") String content,
	          @RequestParam("productId") Integer pdtIdx,
	          Principal principal,
	          RedirectAttributes redirectAttributes) {
	      try {
	          // 로그인된 사용자 ID 가져오기 (String -> int 변환)
	          int userIdx = Integer.parseInt(principal.getName()); // String을 int로 변환

	          // 선택한 상품 ID로 상품 조회
	          ProductEntity taggedProduct = productService.getProductById(pdtIdx);

	          // 새 글 생성 및 저장
	          StoryEntity story = new StoryEntity();
	          story.setStoryTitle(title);
	          story.setStoryContent(content);
	          story.setProductEntity(taggedProduct); // 태그된 상품 설정

	          // UserEntity 객체를 설정하고 userIdx를 StoryEntity에 연결
	          UserEntity userEntity = new UserEntity();
	          userEntity.updateUserIdx(userIdx); // UserEntity에서 userIdx 설정
	          story.setUserEntity(userEntity); // StoryEntity에 UserEntity 설정

	          storyService.save(story);

	          redirectAttributes.addFlashAttribute("message", "글이 성공적으로 등록되었습니다!");
	      } catch (Exception e) {
	          redirectAttributes.addFlashAttribute("error", "글 등록 중 오류가 발생했습니다.");
	      }
	      return "redirect:/story/farmstory.no"; // 등록 후 목록 페이지로 리다이렉트
	  }

	// 모든 스토리 조회
	@GetMapping("/farmstory.no")
	public String getStories(Model model) {
		List<StoryEntity> stories = storyService.getAllStories();
		model.addAttribute("stories", stories);
		return "story/story_list"; // 뷰 파일 경로가 board/story_list.jsp인지 확인
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
