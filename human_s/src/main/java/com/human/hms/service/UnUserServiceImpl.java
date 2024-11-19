package com.human.hms.service;

import org.springframework.stereotype.Service;

import com.human.hms.entity.UnUserEntity;
import com.human.hms.repository.UnUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UnUserServiceImpl implements UnUserService {
	
	UnUserRepository u_repository;
	
	@Override
	public UnUserEntity checkOrderNum(String orderNum, String unPhone) {
		return u_repository.checkOrderNum(orderNum, unPhone);
	}

}
