package com.human.hms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.entity.DailyPriceByCategoryEntity;
import com.human.hms.entity.NoticeEntity;
import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.StoryEntity;
import com.human.hms.service.ApiProductService;
import com.human.hms.service.NoticeService;
import com.human.hms.service.ProductService;
import com.human.hms.service.StoryService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private ApiProductService apiProductServiceImpl; //api
	private ProductService productServiceImpl;		 //상품
	private StoryService storyServiceImpl;			 //팜스토리
	private NoticeService noticeServiceImpl;		 //공지사항
	
	@GetMapping("/")
	public ModelAndView home(ModelAndView mav) {
		
		mav.setViewName("home");
		
		//그래프에 필요한 일별 금액 목록 가져오기
		List<DailyPriceByCategoryEntity> dailyPriceList = apiProductServiceImpl.getDailyPriceList();
		mav.addObject("dailyPriceList",dailyPriceList);
		
		//Best 상품 목록 가져오기
		List<ProductEntity> popList = productServiceImpl.getPopList();
		mav.addObject("popList", popList);
		
		//따끈따끈한 신상 목록 가져오기
		List<ProductEntity> newList = productServiceImpl.getNewList();
		mav.addObject("newList", newList);
		
		//지역 특산물 목록 가져오기
		List<ProductEntity> areaList = productServiceImpl.getProductAreaList();
		mav.addObject("areaList", areaList);
		
		//팜스토리 목록 가져오기
		List<StoryEntity> storyList = storyServiceImpl.getAllStories();
		mav.addObject("storyList", storyList);
        
		//공지사항 목록 가져오기
		List<NoticeEntity> noticeList = noticeServiceImpl.searchNotices("", "", 1, 10);
		mav.addObject("noticeList", noticeList);
		
		return mav;
	}
	
	@GetMapping("/index.no")
	public ModelAndView index(ModelAndView mav) {
		
		mav.setViewName("home");
		
		//그래프에 필요한 일별 금액 목록 가져오기
		List<DailyPriceByCategoryEntity> dailyPriceList = apiProductServiceImpl.getDailyPriceList();
		mav.addObject("dailyPriceList",dailyPriceList);
		
		//Best 상품 목록 가져오기
		List<ProductEntity> popList = productServiceImpl.getPopList();
		mav.addObject("popList", popList);
		
		//따끈따끈한 신상 목록 가져오기
		List<ProductEntity> newList = productServiceImpl.getNewList();
		mav.addObject("newList", newList);
		
		//지역 특산물 목록 가져오기
		List<ProductEntity> areaList = productServiceImpl.getProductAreaList();
		mav.addObject("areaList", areaList);
		
		//팜스토리 목록 가져오기
		List<StoryEntity> storyList = storyServiceImpl.getAllStories();
		mav.addObject("storyList", storyList);
        
		//공지사항 목록 가져오기
		List<NoticeEntity> noticeList = noticeServiceImpl.searchNotices("", "", 1, 10);
		mav.addObject("noticeList", noticeList);
		
		return mav;
	}
	
	//농산물 가격동향 내용 가져오기
	@GetMapping("/getgraphData.no")
	@ResponseBody
	public DailyPriceByCategoryEntity getGraphData(String selectedItemCode, String selectedKindCode, 
													String selectedRankCode) {
		
		return apiProductServiceImpl.getGraphData(selectedItemCode, selectedKindCode, selectedRankCode);
	}
	
}
