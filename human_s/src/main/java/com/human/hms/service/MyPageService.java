package com.human.hms.service;

import java.util.List;

import com.human.hms.entity.FavoriteEntity;
import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.ReviewEntity;

public interface MyPageService {

	List<ReviewEntity> getReviewList(int idx);

	List<FavoriteEntity> getFavoriteByUser(int userIdx);

	List<OrderListEntity> getOrderList(int userIdx);

	List<OrderListEntity> getOrdersByDateRange(int userIdx, String startDate, String endDate);


}
