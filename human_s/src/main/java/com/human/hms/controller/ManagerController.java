package com.human.hms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.human.hms.api.DmsjCodeWhsalApiExplorer;
import com.human.hms.api.DmsjPriceRealApiExplorer;
import com.human.hms.api.KamisDailyPriceApiExplorer;
import com.human.hms.api.NsrLocalSpcprdApiExplorer;
import com.human.hms.entity.CodeWhsalEntity;
import com.human.hms.service.ApiProductService;
import com.human.hms.vo.CodeWhsalVO;
import com.human.hms.vo.DailyPriceByCategoryVO;
import com.human.hms.vo.PriceRealVO;
import com.human.hms.vo.ProductLocalVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
	
	private final ApiProductService apiProductServiceImpl;
	
	//관리자 페이지 이동
	@GetMapping("/manager.do")
	public String manager() {
		return "manager/manager";
	}
	
	//도매시장 통합홈페이지
	private String dmsjServiceKey = "68EC452C124441539CEF42F5C02D6EB4";
	private String codeWhsalUrl = "https://at.agromarket.kr/openApi/code/whsal.do"; //도매시장 코드 조회
	private String priceRealUrl = "https://at.agromarket.kr/openApi/price/real.do"; //도매시장 실시간 경락 정보
	
	//KAMIS 홈페이지
	private String kamisServiceKey = "4f101904-de7a-4c79-8b9e-2e39f221cb51";
	private String kamisId = "4938";
	private String dailyPriceUrl = "http://www.kamis.or.kr/service/price/xml.do?action=dailyPriceByCategoryList"; //일별 부류별 도.소매가격정보
	private String[] categoryCode = {"100", "200", "300", "400"}; //100:식량작물, 200:채소류, 300:특용작물, 400:과일류
	
	//농사로 홈페이지
	private String nsrServiceKey = "20241114NTDXNVEZ84P1OXUHJMQ";
	private String localSpcprdUrl = "http://api.nongsaro.go.kr/service/localSpcprd/localSpcprdLst";
	
	
	//도매시장 코드 저장
	@GetMapping("/codeWhsal.do")
	public ModelAndView insertCodeWhsal(ModelAndView mav, RedirectAttributes redirectAttributes) {
		int result = 0;
		mav.setViewName("redirect:/manager/manager.do?select=api");
		
		try {
			Class<CodeWhsalVO> vo = CodeWhsalVO.class;
						
			CodeWhsalVO data = (CodeWhsalVO)DmsjCodeWhsalApiExplorer.getApiJsonData(dmsjServiceKey, codeWhsalUrl, vo);
			result = apiProductServiceImpl.insertCodeWhsal(data);
			
			if(result == 0) {
				redirectAttributes.addFlashAttribute("msg", "도매시장 코드 저장에 실패했습니다.");
			}else{
				redirectAttributes.addFlashAttribute("msg", "도매시장 코드 저장에 성공했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("도매시장 통합홈페이지의 도매시장 코드 저장 중 오류 발생");
			e.printStackTrace();
		}
		
		return mav;
	}
	
	//도매시장 실시간 경락 정보 저장
	@GetMapping("/priceReal.do")
	public ModelAndView insertPriceReal(ModelAndView mav, RedirectAttributes redirectAttributes) {
		int result = 0;
		boolean morePages = true;
		mav.setViewName("redirect:/manager/manager.do?select=api");
		

		//저장되어있는 내용을 모두 삭제하고 오늘 날짜로 새 데이터를 받기 위함
		apiProductServiceImpl.priceRealDeleteAll();
		
		try {
			Class<PriceRealVO> vo = PriceRealVO.class;
			
			List<CodeWhsalEntity> code = apiProductServiceImpl.getCodeWhsalList();
			for(CodeWhsalEntity entity : code) {
				int pageNo = 1;
				
				while(morePages) {
					PriceRealVO data = (PriceRealVO)DmsjPriceRealApiExplorer.getApiJsonData(dmsjServiceKey, priceRealUrl, 
							String.valueOf(pageNo), entity.getCodeId(), vo);
					morePages = data.getData().size() > 0;
					if(!morePages) break;
					
					result += apiProductServiceImpl.insertPriceReal(data);
					
					pageNo++;
				}
			}
			
			if(result == 0) {
				redirectAttributes.addFlashAttribute("msg", "실시간 경락 정보 저장에 실패했습니다.");
			}else{
				redirectAttributes.addFlashAttribute("msg", "실시간 경락 정보 저장에 성공했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("도매시장 통합홈페이지의 실시간 경락 정보 저장 중 오류 발생");
			e.printStackTrace();
		}
		
		
		return mav;
	}
	
	//지정된 시각에 도매시장 실시간 경락 정보 저장
	public void insertTimePriceReal() {
		boolean morePages = true;

		//저장되어있는 내용을 모두 삭제하고 오늘 날짜로 새 데이터를 받기 위함
		apiProductServiceImpl.priceRealDeleteAll();
		
		try {
			Class<PriceRealVO> vo = PriceRealVO.class;
			
			List<CodeWhsalEntity> code = apiProductServiceImpl.getCodeWhsalList();
			for(CodeWhsalEntity entity : code) {
				int pageNo = 1;
				
				while(morePages) {
					PriceRealVO data = (PriceRealVO)DmsjPriceRealApiExplorer.getApiJsonData(dmsjServiceKey, priceRealUrl, 
							String.valueOf(pageNo), entity.getCodeId(), vo);
					morePages = data.getData().size() > 0;
					if(!morePages) break;
					
					apiProductServiceImpl.insertPriceReal(data);
					
					pageNo++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//KAMIS 일별 부류별 도.소매가격정보 저장
	@GetMapping("/dailyPrice.do")
	public ModelAndView insertDailyPrice(ModelAndView mav, @RequestParam("regDate") String regDate, 
											RedirectAttributes redirectAttributes) {
		int result = 0;
		mav.setViewName("redirect:/manager/manager.do?select=api");
		
		//저장되어있는 내용을 모두 삭제하고 오늘 날짜로 새 데이터를 받기 위함
		apiProductServiceImpl.dailyPriceDeleteAll();
		
		try {
			Class<DailyPriceByCategoryVO> vo = DailyPriceByCategoryVO.class;
			
			for (String code : categoryCode) {
				DailyPriceByCategoryVO data = (DailyPriceByCategoryVO)KamisDailyPriceApiExplorer.getApiJsonData(
						kamisServiceKey, kamisId, dailyPriceUrl, code, regDate, vo);
				
				result += apiProductServiceImpl.insertDailyPrice(data);
			}
			
			if(result == 0) {
				redirectAttributes.addFlashAttribute("msg", "일별 부류별 도.소매가격정보 저장에 실패했습니다.");
			}else{
				redirectAttributes.addFlashAttribute("msg", "일별 부류별 도.소매가격정보 저장에 성공했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("KANIS 홈페이지의 일별 부류별 도.소매가격정보 저장 중 오류 발생");
			e.printStackTrace();
		}
		
		return mav;
	}
	
	//지정된 시각에 KAMIS 일별 부류별 도.소매가격정보 저장
	public void insertTimeDailyPrice(String regDate) {
		
		//저장되어있는 내용을 모두 삭제하고 오늘 날짜로 새 데이터를 받기 위함
		apiProductServiceImpl.dailyPriceDeleteAll();
		
		try {
		Class<DailyPriceByCategoryVO> vo = DailyPriceByCategoryVO.class;
		
		for (String code : categoryCode) {
			DailyPriceByCategoryVO data = (DailyPriceByCategoryVO)KamisDailyPriceApiExplorer.getApiJsonData(
					kamisServiceKey, kamisId, dailyPriceUrl, code, regDate, vo);
			
			apiProductServiceImpl.insertDailyPrice(data);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//농사로 지역특산물 저장(상품 여러개 넣어놓기 위함)
	@GetMapping("areaProduct.do")
	public ModelAndView insertAreaProduct(ModelAndView mav, RedirectAttributes redirectAttributes, 
												HttpServletRequest request) {
		
		int result = 0;
		boolean morePages = true;
		String numOfRows = "100";
		mav.setViewName("redirect:/manager/manager.do?select=api");
		
		//농사로로 저장한 내용을 모두 삭제하고 새 데이터를 받기 위함
		apiProductServiceImpl.deleteLocalSpcprdAll();
		
		try {
			Class<ProductLocalVO> vo = ProductLocalVO.class;
			
			int pageNo = 1;
			
			while(morePages) {
				ProductLocalVO data = (ProductLocalVO)NsrLocalSpcprdApiExplorer
						.getApiJsonData(nsrServiceKey, localSpcprdUrl, String.valueOf(pageNo), numOfRows, vo);
			
				if (data != null && data.getBody() != null && data.getBody().getItems() != null) {
				    morePages = data.getBody().getItems().getItem() != null && data.getBody().getItems().getItem().size() > 0;
				} else {
				    morePages = false;
				}
				if(!morePages) break;
				
				result += apiProductServiceImpl.insertAreaProduct(data, request);
				
				pageNo++;
			}
			
			
			
			if(result == 0) {
				redirectAttributes.addFlashAttribute("msg", "지역특산물 저장에 실패했습니다.");
			}else{
				redirectAttributes.addFlashAttribute("msg", "지역특산물 저장에 성공했습니다.");
			}
			
		} catch (Exception e) {
			System.out.println("농사로 홈페이지의 지역특산물 저장 중 오류 발생");
			e.printStackTrace();
		}
		
		return mav;
	}

}
