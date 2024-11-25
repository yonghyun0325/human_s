package com.human.hms.service;

import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.UnUserEntity;

public interface OrderListService {

	OrderListEntity orderUnuser(UnUserEntity un_entity, OrderListEntity entity);

	OrderListEntity orderuser(OrderListEntity entity);

	String authEmail(String email, String authNumber);

}
