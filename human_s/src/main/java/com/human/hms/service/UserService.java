package com.human.hms.service;

import com.human.hms.vo.UserVO;

public interface UserService {

	UserVO login(String userEmail, String userPw);

}
