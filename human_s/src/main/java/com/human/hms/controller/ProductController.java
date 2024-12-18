package com.human.hms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.entity.BasketEntity;
import com.human.hms.entity.FavoriteEntity;
import com.human.hms.entity.PinquiryEntity;
import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.service.ProductService;
import com.human.hms.vo.ProductVO;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
	
	private ProductService productServiceImpl;	 //상품
	
	//인기순/최신순 페이지
	@GetMapping("/popNewList.no")
	public ModelAndView popNewList(ModelAndView mav, @RequestParam String select) {
		mav.setViewName("product/popNewList");
		
		if(select.equals("pop")) {
			List<ProductEntity> popList = productServiceImpl.getPopList();
			mav.addObject("popNewList", popList);
		}else {
			List<ProductEntity> newList = productServiceImpl.getNewList();
			mav.addObject("popNewList", newList);
		}
		return mav;
	}
	
	//지역특산물/전체상품 페이지
	@GetMapping("/checkBoxList.no")
	public ModelAndView checkBoxList(ModelAndView mav, @RequestParam String select) {
		mav.setViewName("product/checkBoxList");
		List<ProductEntity> productList = null;
		
		if(select.equals("areaSelect")) {
			//지역코드 조회
			List<Object[]> areaList = productServiceImpl.getAreaList();
			mav.addObject("areaList", areaList);
			
			productList = productServiceImpl.findAll();
		}else if(select.equals("category")){
			//대븐류코드 조회
			List<Object[]> largeList = productServiceImpl.getLargeList();
			mav.addObject("largeList", largeList);	
			
			productList = productServiceImpl.findAll();
		}else {
			productList = productServiceImpl.getSelectList(select);
			mav.addObject("select", select);
		}
		
		mav.addObject("productList", productList);
		
		return mav;
	}
	
	//상품 상세보기 페이지
	@GetMapping("/viewDetail.no")
	public ModelAndView viewDetail(ModelAndView mav, @RequestParam int idx) {
		mav.setViewName("product/viewDetail");
		
		//해당 상품 조회
		ProductEntity product = productServiceImpl.findbyId(idx);
		mav.addObject("product", product);
		
		//해당 상품 문의 조회
		List<PinquiryEntity> pinquiryList = productServiceImpl.getPinquiryList(idx);
		mav.addObject("pinquiryList", pinquiryList);
		
		//해당 상품 후기 조회
		List<ReviewEntity> reviewList = productServiceImpl.getReviewList(idx);
		mav.addObject("reviewList", reviewList);
		
		return mav;
	}
	
	//판매자 상품등록 페이지
	@GetMapping("/writeDetail.do")
	public ModelAndView wirteDetail(ModelAndView mav) {
		mav.setViewName("product/writeDetail");
		
		//지역코드 조회
		List<Object[]> areaList = productServiceImpl.getAreaList();
		List<Object[]> area2List = productServiceImpl.getArea2List();
		mav.addObject("areaList", areaList);
		mav.addObject("area2List", area2List);
		
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
		
		// 제품 가격과 무게를 Double로 처리
		Double pdtPrice = Double.valueOf(vo.getPdtPrice());  // 가격
		Double pdtKg = Double.valueOf(vo.getPdtKg());       // 무게
		// 100g당 가격 계산 (소수 첫 번째 자리에서 반올림)
		Double pricePer100g = pdtPrice / pdtKg / 10;   // 가격 계산 (100g당 가격)
		Double roundedPrice = Math.round(pricePer100g * 10.0) / 10.0;  // 반올림
		System.out.println("large:"+vo.getPdtLargeCode());
		
		ProductEntity entity = ProductEntity.builder()
								.pdtTitle(vo.getPdtTitle())
								.pdtPrice(vo.getPdtPrice())
								.pdtLargeCode(vo.getPdtLargeCode())
								.pdtMidCode(vo.getPdtMidCode())
								.pdtSmallCode(vo.getPdtSmallCode())
								.pdtArea(vo.getPdtArea())
								.pdtArea2(vo.getPdtArea2())
								.pdtWriter(vo.getPdtWriter())
								.pdtKg(vo.getPdtKg())
								.pdtGPrice(String.valueOf(roundedPrice))
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
	
	//체크박스 리스트 ajax를 이용해 다양한 조건으로 상품 리스트 받기
	@ResponseBody
	@GetMapping("getProductList.no")
	public List<ProductEntity> getProductList(@RequestParam String select, @RequestParam("checkeds[]") List<String> checkeds) {
		List<ProductEntity> productList = null;
		
		System.out.println(checkeds);
		
		if(checkeds.size() == 1 && checkeds.get(0).equals("all")) {
			productList = productServiceImpl.findAll();
		}else {
			if(select.equals("areaSelect")) {
				productList = productServiceImpl.checkAreaList(checkeds);
			}else {
				productList = productServiceImpl.checkCategoryList(checkeds);
			}			
		}
		
		return productList;
	}
	
	//판매자의 상품 수정 페이지
	@GetMapping("/updateDetail.do")
	public ModelAndView updateDetail(ModelAndView mav, @RequestParam int idx) {
		mav.setViewName("product/updateDetail");
		
		//지역코드 조회
		List<Object[]> areaList = productServiceImpl.getAreaList();
		List<Object[]> area2List = productServiceImpl.getArea2List();
		mav.addObject("areaList", areaList);
		mav.addObject("area2List", area2List);
		
		//대.중.소 븐류코드 조회
		List<Object[]> largeList = productServiceImpl.getLargeList();
		List<Object[]> midList = productServiceImpl.getMidList();
		List<Object[]> smallList = productServiceImpl.getSmallList();
		mav.addObject("largeList", largeList);
		mav.addObject("midList", midList);
		mav.addObject("smallList", smallList);
		
		ProductEntity product = productServiceImpl.findbyId(idx);
		mav.addObject("product", product);
		
		return mav;
	}
	
	//상품수정하기
	@PostMapping("/updateProduct.do")
	public ModelAndView updateProduct(ProductVO vo, ModelAndView mav, HttpServletRequest request) {
		String viewName = "product/updateDetail";
		
		// 제품 가격과 무게를 Double로 처리
		Double pdtPrice = Double.valueOf(vo.getPdtPrice());  // 가격
		Double pdtKg = Double.valueOf(vo.getPdtKg());       // 무게
		// 100g당 가격 계산 (소수 첫 번째 자리에서 반올림)
		Double pricePer100g = pdtPrice / pdtKg / 10;   // 가격 계산 (100g당 가격)
		Double roundedPrice = Math.round(pricePer100g * 10.0) / 10.0;  // 반올림
		System.out.println("large:"+vo.getPdtLargeCode());
		
		ProductEntity entity = ProductEntity.builder()
								.pdtTitle(vo.getPdtTitle())
								.pdtPrice(vo.getPdtPrice())
								.pdtLargeCode(vo.getPdtLargeCode())
								.pdtMidCode(vo.getPdtMidCode())
								.pdtSmallCode(vo.getPdtSmallCode())
								.pdtArea(vo.getPdtArea())
								.pdtArea2(vo.getPdtArea2())
								.pdtWriter(vo.getPdtWriter())
								.pdtKg(vo.getPdtKg())
								.pdtGPrice(String.valueOf(roundedPrice))
								.pdtFile(vo.getPdtFile())
								.uploadFiles(vo.getUploadFiles())
								.build();
		entity.updatePdtIdx(vo.getPdtIdx());
		
		int result = productServiceImpl.updateProduct(entity, request);
		if(result == 1) {
			viewName = "redirect:/index.no";
		}
		mav.setViewName(viewName);
		
		return mav;
	}
	
	//상품 삭제하기
	@GetMapping("/deleteDetail.do")
	public ModelAndView deleteProduct(@RequestParam int idx, ModelAndView mav) {
		String viewName = "product/viewDetail";
		
		int result = productServiceImpl.deleteProduct(idx);
		if(result == 1) {
			viewName = "redirect:/index.no";
		}
		
		mav.setViewName(viewName);
		
		return mav;
	}
	
	//상품을 장바구니에 등록하기
	@ResponseBody
	@GetMapping("/productInCart.do")
	public String productInCart(@RequestParam int idx, @RequestParam int qty, HttpServletRequest request) {
		
		String result = "fail";
		BasketEntity entity = BasketEntity.builder()
								.qty(qty)
								.build();
		entity.updateProductEntity(productServiceImpl.findbyId(idx));
		entity.updateUserEntity((UserEntity)request.getSession().getAttribute("user"));
		
		if(productServiceImpl.productInCart(entity) == 1) {
			result = "ok";
		}
		
		return result;
	}
	
	//상품을 찜목록에 등록하기
	@ResponseBody
	@GetMapping("/inFavorite.do")
	public String productInFavorite(@RequestParam int idx, HttpServletRequest request) {
		String result = "fail";
		FavoriteEntity entity = new FavoriteEntity();
		entity.updateUser((UserEntity)request.getSession().getAttribute("user"));
		entity.updateProduct(productServiceImpl.findbyId(idx));
		
		int count = productServiceImpl.productInFavorite(entity);
		
		if(count == 1) {
			result = "in"; //찜하기 성공
		}else if(count == 2) {
			result = "out"; //찜하기 삭제 성공
		}
		
		return result;
	}
	
	//Hidden Nav 안의 과일~기타
	@GetMapping("/hiddenList.no")
	public ModelAndView hiddenList(ModelAndView mav, @RequestParam String select, @RequestParam("checked") String checked) {
		mav.setViewName("product/checkBoxList");
		List<ProductEntity> productList = null;
		List<String> checkeds = null;
		
		switch(checked) {
			case "fruit": checkeds = Arrays.asList("06", "07", "08"); break;
			case "vagetable": checkeds = Arrays.asList("09", "10", "11", "12", "13", "14"); break;
			case "grainsNuts": checkeds = Arrays.asList("03", "05", "16", "19"); break;
			default: checkeds = Arrays.asList("17", "26"); break;
		}
		productList = productServiceImpl.checkHiddenList(checkeds);
		
		mav.addObject("productList", productList);
		
		return mav;
	}
	
}
