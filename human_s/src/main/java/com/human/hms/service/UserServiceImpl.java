package com.human.hms.service;

import org.springframework.stereotype.Service;

import com.human.hms.entity.UserEntity;
import com.human.hms.repository.UserRepository;
import com.human.hms.vo.UserVO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;

	@Override
	public UserVO login(String userEmail, String userPw) {
		UserEntity entity = repository.login(userEmail, userPw);
		
		UserVO vo = UserVO.fromEntity(entity);
		
		return vo;
	}

}
