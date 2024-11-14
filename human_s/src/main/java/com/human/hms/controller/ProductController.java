package com.human.hms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.service.ProductService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
	
	private ProductService productServiceImpl;
	
	@GetMapping("/popNewList.no")
	public ModelAndView popNewList(ModelAndView mav) {
		mav.setViewName("product/popNewList");
		return mav;
	}
	
	@GetMapping("/checkBoxList.no")
	public ModelAndView checkBoxList(ModelAndView mav) {
		mav.setViewName("product/checkBoxList");
		return mav;
	}
	
	@GetMapping("/viewDetail.no")
	public ModelAndView viewDetail(ModelAndView mav) {
		mav.setViewName("product/viewDetail");
		return mav;
	}
	
	@GetMapping("/writeDetail.do")
	public ModelAndView wirteDetail(ModelAndView mav) {
		mav.setViewName("product/writeDetail");
		
		//대.중.소 븐류코드 조회
		List<Object[]> largeList = productServiceImpl.getLargeList();
		List<Object[]> midList = productServiceImpl.getMidList();
		List<Object[]> smallList = productServiceImpl.getSmallList();
		mav.addObject("largeList", largeList);
		mav.addObject("midList", midList);
		mav.addObject("smallList", smallList);
		
		return mav;
	}
	
	@GetMapping("/getGraphData.do")
	@ResponseBody
	public List<Map<String, String>> getGraphData(String largeCode, String midCode, String smallCode) {
		List<Map<String, String>> result = new ArrayList<>();
		
		result = productServiceImpl.getGraphData(largeCode, midCode, smallCode);
		
		return result;
	}
	
}
