package com.human.hms.service;

import java.util.List;
import java.util.Map;

public interface ProductService {

	List<Object[]> getLargeList();

	List<Object[]> getMidList();

	List<Object[]> getSmallList();

	List<Map<String, String>> getGraphData(String largeCode, String midCode, String smallCode);

}
