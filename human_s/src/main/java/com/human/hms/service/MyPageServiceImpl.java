package com.human.hms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.human.hms.repository.MyPageRepository;
import com.human.hms.repository.UserRepository;

@Service
public class MyPageServiceImpl implements MyPageService {

	@Autowired
	private UserRepository userRepository;
}
