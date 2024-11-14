package com.human.hms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.human.hms.repository.DmsjPriceRealRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private DmsjPriceRealRepository priceRealRepository;

	//대분류코드 조회
	@Override
	public List<Object[]> getLargeList() {
		return priceRealRepository.getLargeList();
	}
	
	//중분류코드 조회
	@Override
	public List<Object[]> getMidList() {
		return priceRealRepository.getMidList();
	}

	//소분류코드조회
	@Override
	public List<Object[]> getSmallList() {
		return priceRealRepository.getSmallList();
	}

	//경매가 최대,최소,평균값 조회
	@Override
	public List<Map<String, String>> getGraphData(String largeCode, String midCode, String smallCode) {
		
		List<Object[]> result = priceRealRepository.getGraphData(largeCode, midCode, smallCode);
		List<Map<String, String>> responseList = new ArrayList<>();
	    
	    for (Object[] item : result) {
	        Map<String, String> response = new HashMap<>();
	        response.put("max", item[0].toString());
	        response.put("min", item[1].toString());
	        response.put("avg", item[2].toString());
	        response.put("std", item[3].toString());
	        responseList.add(response);
	    }

	    return responseList;
	}
	
}
