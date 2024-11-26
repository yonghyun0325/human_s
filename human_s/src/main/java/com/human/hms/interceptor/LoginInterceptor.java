package com.human.hms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.human.hms.entity.UserEntity;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean result = true; //Controller로 사용자의 요청이 전달되게 하는 결과값
		
		UserEntity entity = (UserEntity) request.getSession().getAttribute("user");
		if(entity == null) { //로그인이 안 된 경우
			response.sendRedirect(request.getContextPath()+"/user/login.no");
			result = false; //Controller로 사용자의 요청이 전달되지 못하게 하는 결과값
		}

		return result;
	}

	
	
}
