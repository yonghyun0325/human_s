package com.human.hms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.entity.DailyPriceByCategoryEntity;
import com.human.hms.service.ApiProductService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	
	private ApiProductService apiProductServiceImpl;
	
	@GetMapping("/")
	public ModelAndView home(ModelAndView mav) {
		
		mav.setViewName("home");
		
		//그래프에 필요한 일별 금액 목록 가져오기
		List<DailyPriceByCategoryEntity> dailyPriceList = apiProductServiceImpl.getDailyPriceList();
		mav.addObject("dailyPriceList",dailyPriceList);
		
		return mav;
	}
	
	@GetMapping("/index.no")
	public ModelAndView index(ModelAndView mav) {
		
		mav.setViewName("home");
		
		//그래프에 필요한 일별 금액 목록 가져오기
		List<DailyPriceByCategoryEntity> dailyPriceList = apiProductServiceImpl.getDailyPriceList();
		mav.addObject("dailyPriceList",dailyPriceList);
		
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
