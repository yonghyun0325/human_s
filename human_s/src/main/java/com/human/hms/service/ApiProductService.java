package com.human.hms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.human.hms.entity.CodeWhsalEntity;
import com.human.hms.entity.DailyPriceByCategoryEntity;
import com.human.hms.vo.CodeWhsalVO;
import com.human.hms.vo.DailyPriceByCategoryVO;
import com.human.hms.vo.PriceRealVO;
import com.human.hms.vo.ProductLocalVO;

public interface ApiProductService {

	int insertCodeWhsal(CodeWhsalVO data);

	List<CodeWhsalEntity> getCodeWhsalList();

	int insertPriceReal(PriceRealVO data);

	int insertDailyPrice(DailyPriceByCategoryVO data);

	List<DailyPriceByCategoryEntity> getDailyPriceList();

	void priceRealDeleteAll();

	void dailyPriceDeleteAll();

	DailyPriceByCategoryEntity getGraphData(String selectedItemCode, String selectedKindCode, String selectedRankCode);

	int insertAreaProduct(ProductLocalVO data, HttpServletRequest request);

	void deleteLocalSpcprdAll();

}
