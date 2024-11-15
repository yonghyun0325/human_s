package com.human.hms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.entity.ProductEntity;
import com.human.hms.service.ProductService;
import com.human.hms.vo.ProductVO;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
	
	private ProductService productServiceImpl;
	
	//인기순/최신순 페이지
	@GetMapping("/popNewList.no")
	public ModelAndView popNewList(ModelAndView mav) {
		mav.setViewName("product/popNewList");
		return mav;
	}
	
	//지역특산물/전체상품 페이지
	@GetMapping("/checkBoxList.no")
	public ModelAndView checkBoxList(ModelAndView mav) {
		mav.setViewName("product/checkBoxList");
		return mav;
	}
	
	//상품 상세보기 페이지
	@GetMapping("/viewDetail.no")
	public ModelAndView viewDetail(ModelAndView mav) {
		mav.setViewName("product/viewDetail");
		return mav;
	}
	
	//판매자 상품등록 페이지
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
	
	//상품등록 페이지의 그래프 데이터 가져오기
	@GetMapping("/getGraphData.do")
	@ResponseBody
	public List<Map<String, String>> getGraphData(String largeCode, String midCode, String smallCode) {
		List<Map<String, String>> result = new ArrayList<>();
		
		result = productServiceImpl.getGraphData(largeCode, midCode, smallCode);
		
		return result;
	}
	
	//상품등록하기
	@PostMapping("/writeProduct.do")
	public ModelAndView insertProduct(ProductVO vo, ModelAndView mav, HttpServletRequest request) {
		String viewName = "product/writeDetail";
		
		ProductEntity entity = ProductEntity.builder()
								.img("")
								.pdtTitle(vo.getPdtTitle())
								.pdtPrice(vo.getPdtPrice())
								.pdtLargeCode(vo.getPdtLargeCode())
								.pdtMidCode(vo.getPdtMidCode())
								.pdtSmallCode(vo.getPdtSmallCode())
								.pdtArea(vo.getPdtArea())
								.pdtArea2(vo.getPdtArea2())
								.pdtWriter(vo.getPdtWriter())
								.pdtFile(vo.getPdtFile())
								.uploadFiles(vo.getUploadFiles())
								.build();
		
		int result = productServiceImpl.insertProduct(entity, request);
		if(result == 1) {
			viewName = "redirect:/index.no";
		}
		mav.setViewName(viewName);
		
		return mav;
	}
	
}
