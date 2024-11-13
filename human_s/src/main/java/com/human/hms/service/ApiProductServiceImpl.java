package com.human.hms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.human.hms.entity.CodeWhsalEntity;
import com.human.hms.entity.DailyPriceByCategoryEntity;
import com.human.hms.entity.PriceRealEntity;
import com.human.hms.repository.DmsjCodeWhsalRepository;
import com.human.hms.repository.DmsjPriceRealRepository;
import com.human.hms.repository.KamisDailyPriceRepository;
import com.human.hms.vo.CodeWhsalVO;
import com.human.hms.vo.CodeWhsalVO.CodeWhsal;
import com.human.hms.vo.DailyPriceByCategoryVO;
import com.human.hms.vo.DailyPriceByCategoryVO.DailyPriceByCategory;
import com.human.hms.vo.PriceRealVO;
import com.human.hms.vo.PriceRealVO.PriceReal;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ApiProductServiceImpl implements ApiProductService {

	private DmsjCodeWhsalRepository codeWhsalRepository;
	private DmsjPriceRealRepository priceRealRepository;
	private KamisDailyPriceRepository dailyPriceRepository;

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
	
}
