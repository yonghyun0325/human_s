package com.human.hms.service;

import java.util.Date;
import java.util.List;

import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.UnUserEntity;

public interface OrderListService {

	OrderListEntity orderUnuser(UnUserEntity un_entity, OrderListEntity entity);

	OrderListEntity orderuser(OrderListEntity entity);

	String authEmail(String email, String authNumber);

	List<OrderListEntity> getOrdersByDateRange(Date startDate, Date endDate);

}
