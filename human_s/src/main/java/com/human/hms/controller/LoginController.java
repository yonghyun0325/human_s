package com.human.hms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.human.hms.service.UserService;
import com.human.hms.vo.UserVO;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
	
	private UserService userServiceImpl;

	@GetMapping("/login.no")
	public String login() {
		return "login/login";
	}
	
	@PostMapping("/loginProcess.no")
	public String loginProcess(String userEmail, String userPw, HttpServletRequest request, Model model) {
		String viewName = "login/login";
		
		UserVO vo = userServiceImpl.login(userEmail, userPw);
		
		if(vo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("member", vo);
		}else {
			model.addAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
		}
		
		return viewName;
	}
}
