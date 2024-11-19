package com.human.hms.service;

import com.human.hms.entity.UnUserEntity;

public interface UnUserService {
	
	UnUserEntity checkOrderNum(String orderNum, String unPhone);

}
