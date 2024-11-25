package com.human.hms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.entity.CodeWhsalEntity;
import com.human.hms.entity.DailyPriceByCategoryEntity;
import com.human.hms.entity.PriceRealEntity;
import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.repository.DmsjCodeWhsalRepository;
import com.human.hms.repository.DmsjPriceRealRepository;
import com.human.hms.repository.KamisDailyPriceRepository;
import com.human.hms.repository.ProductRepository;
import com.human.hms.vo.CodeWhsalVO;
import com.human.hms.vo.CodeWhsalVO.CodeWhsal;
import com.human.hms.vo.DailyPriceByCategoryVO;
import com.human.hms.vo.DailyPriceByCategoryVO.DailyPriceByCategory;
import com.human.hms.vo.PriceRealVO;
import com.human.hms.vo.PriceRealVO.PriceReal;
import com.human.hms.vo.ProductLocalVO;
import com.human.hms.vo.ProductLocalVO.ProductLocal;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ApiProductServiceImpl implements ApiProductService {

	private DmsjCodeWhsalRepository codeWhsalRepository;
	private DmsjPriceRealRepository priceRealRepository;
	private KamisDailyPriceRepository dailyPriceRepository;
	private ProductRepository productRepository;

	//도매시장 코드 저장
	@Override
	public int insertCodeWhsal(CodeWhsalVO data) {
		int result = 0;
		
		List<CodeWhsal> items = data.getData();
		for(CodeWhsal codeWhsal : items) {
			CodeWhsalEntity entity = CodeWhsalEntity.builder()
										.codeId(codeWhsal.getCodeId())
										.codeName(codeWhsal.getCodeName())
										.build();
			if(codeWhsalRepository.save(entity) != null) {
				result++;
			}
		}
		
		return result;
	}
	
	//도매시장 실시간 경락정보를 저장하기 위해 이전에 테이블에 있던 내용을 지움
	@Override
	public void priceRealDeleteAll() {
		priceRealRepository.deleteAll();
	}

	//도매시장 코드 조회
	@Override
	public List<CodeWhsalEntity> getCodeWhsalList() {
		return codeWhsalRepository.findAll();
	}

	//도매시장 실시간 경락정보 저장
	@Override
	public int insertPriceReal(PriceRealVO data) {
		int result = 0;
		
		List<PriceReal> items = data.getData();
		for(PriceReal priceReal : items) {
			PriceRealEntity entity = PriceRealEntity.builder()
										.saleDate(priceReal.getSaleDate())
										.whsalCd(priceReal.getWhsalCd())
										.whsalName(priceReal.getWhsalName())
										.cmpCd(priceReal.getCmpCd())
										.cmpName(priceReal.getCmpName())
										.large(priceReal.getLarge())
										.largeName(priceReal.getLargeName())
										.mid(priceReal.getMid())
										.midName(priceReal.getMidName())
										.small(priceReal.getSmall())
										.smallName(priceReal.getSmallName())
										.sanCd(priceReal.getSanCd())
										.sanName(priceReal.getSanName())
										.cost(priceReal.getCost())
										.qty(priceReal.getQty())
										.std(priceReal.getStd())
										.sbidtime(priceReal.getSbidtime())
										.build();
			if(priceRealRepository.save(entity) != null) {
				result++;
			}
		}
		
		return result;
	}

	//KAMIS 일별 부류별 도소매가격을 저장하기 위해 이전에 테이블에 있던 내용을 지움
	@Override
	public void dailyPriceDeleteAll() {
		dailyPriceRepository.deleteAll();
	}

	//KAMIS 일별 부류별 도소매가격 저장
	@Override
	public int insertDailyPrice(DailyPriceByCategoryVO data) {
		int result = 0;
		
		List<DailyPriceByCategory> items = data.getData().getItem();
		for(DailyPriceByCategory dailyPrice : items) {
			
			dailyPrice.setItemKindRankCode(dailyPrice.getItem_code()+dailyPrice.getKind_code()
												+dailyPrice.getRank_code());
			
			String img = productRepository.getImg(dailyPrice.getItem_name());
			
			DailyPriceByCategoryEntity entity = DailyPriceByCategoryEntity.builder()
												.itemKindRankCode(dailyPrice.getItemKindRankCode())
												.item_name(dailyPrice.getItem_name())
												.item_code(dailyPrice.getItem_code())
												.kind_name(dailyPrice.getKind_name())
												.kind_code(dailyPrice.getKind_code())
												.rank(dailyPrice.getRank())
												.rank_code(dailyPrice.getRank_code())
												.unit(dailyPrice.getUnit())
												.day1(dailyPrice.getDay1())
												.dpr1(dailyPrice.getDpr1())
												.day2(dailyPrice.getDay2())
												.dpr2(dailyPrice.getDpr2())
												.day3(dailyPrice.getDay3())
												.dpr3(dailyPrice.getDpr3())
												.day4(dailyPrice.getDay4())
												.dpr4(dailyPrice.getDpr4())
												.day5(dailyPrice.getDay5())
												.dpr5(dailyPrice.getDpr5())
												.day6(dailyPrice.getDay6())
												.dpr6(dailyPrice.getDpr6())
												.day7(dailyPrice.getDay7())
												.dpr7(dailyPrice.getDpr7())
												.img(img)
												.build();
			if(dailyPriceRepository.save(entity) != null) {
				result++;
			}
			
		}
		
		return result;
	}

	//메인화면 그래프에 필요한 일별 금액 목록 가져오기
	@Override
	public List<DailyPriceByCategoryEntity> getDailyPriceList() {
		return dailyPriceRepository.findAll();
	}

	//농산물 가격동향 내용 가져오기
	@Override
	public DailyPriceByCategoryEntity getGraphData(String selectedItemCode, String selectedKindCode,
													String selectedRankCode) {
		return dailyPriceRepository.selectData(selectedItemCode, selectedKindCode, selectedRankCode);
	}

	//농사로 지역특산물을 저장하기 위해 이전에 등록했던 농사로 지역특산물을 삭제
	@Transactional
	@Override
	public void deleteLocalSpcprdAll() {
		productRepository.deleteLocalSpcprdAll();
	}

	//농사로 지역특산물 저장(상품 여러개 넣어놓기 위함)
	@Override
	public int insertAreaProduct(ProductLocalVO data, HttpServletRequest request) {
		int result = 0;
		
		HttpSession session = request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		
		List<ProductLocal> items = data.getBody().getItems().getItem();
		for(ProductLocal product : items) {
			//가격 가져오기
			PriceRealEntity pEntity = priceRealRepository.getPriceByMidName(product.getCntntsSj());
			
			//area 이름 각각 저장하기
			String[] areas = product.getAreaNm().split(" > ");
	        String area = areas[0];
	        String area2 = "";
	        if(areas.length > 1) {
	        	area2 = areas[1];
	        }
	        
	        if (pEntity == null || pEntity.getLarge() == null) {
	            continue;  // 코드 정보가 없으면 저장하지 않음
	        }
	        
	        //std를 kg기준 앞에만 남김
	        String std = pEntity.getStd();
	        String kgValue = std.replace("kg", "");  // "10 " (공백 포함)
	        String kgValue2 = kgValue.replaceAll("\\s.*$", "");
	        
	        // 제품 가격과 무게를 Double로 처리
			Double pdtPrice = Double.valueOf(pEntity.getCost());  // 가격
			Double pdtKg = Double.valueOf(kgValue2);       // 무게
			// 100g당 가격 계산 (소수 첫 번째 자리에서 반올림)
			Double pricePer100g = pdtPrice / pdtKg / 10;   // 가격 계산 (100g당 가격)
			Double roundedPrice = Math.round(pricePer100g * 10.0) / 10.0;  // 반올림
			String roundedPriceStr = String.valueOf(roundedPrice);
	        
			
			ProductEntity entity = ProductEntity.builder()
									.img(product.getImgUrl())
									.pdtTitle("신선한 "+product.getCntntsSj())
									.pdtPrice(pEntity != null ? String.valueOf(pEntity.getCost()) : "")
									.pdtLargeCode(pEntity != null ? pEntity.getLarge() : "")
									.pdtMidCode(pEntity != null ? pEntity.getMid() : "")
									.pdtSmallCode(pEntity != null ? pEntity.getSmall() : "")
									.pdtArea(area)
									.pdtArea2(area2)
									.pdtWriter(userEntity.getUserNick())
									.pdtKg(kgValue2)
									.pdtGPrice(roundedPriceStr)
									.build();
			
			entity.updateUserEntity(userEntity);
			entity.updateCodeName(pEntity.getLargeName(), pEntity.getMidName(), pEntity.getSmallName());
			
			if(productRepository.save(entity) != null) {
				result++;
			}
		}
		
		return result;
	}
	
}
