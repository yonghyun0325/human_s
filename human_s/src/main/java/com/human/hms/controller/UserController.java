package com.human.hms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.service.UserService;
import com.human.hms.vo.UserVO;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
	
	
	private UserService userServiceImpl;
	
	//회원가입 약관동의 페이지
	@GetMapping("/agreement.no")
	public String joinAgreement() {
		
		return "join/agreement";
	}
	
	//회원가입 유형선택 페이지
	@GetMapping("/jointype.no")
	public String jointype() {
		
		return "join/jointype";
	}
	
	//판매자 등록가입
	@GetMapping("/join_register.no")
	public String join_register() {
		
		return "join/join_register";
	}
	
	//일반회원가입
	@GetMapping("/join_normal.no")
	public String join_normal() {
		
		return "join/join_normal";
	}
	
	@ResponseBody
	@GetMapping("/sameidcheck.no")
	public String sameIdCheck(String userEmail) {
		String viewName = "Fail";
		
		if(userServiceImpl.sameIdcheck(userEmail) == 0) {
			viewName= "SUCCESS";
		}else {
			System.out.println(userEmail);
		}
		
		return viewName;
	}
	
	@PostMapping("/joinProcess.no")
	public ModelAndView joinProcess(ModelAndView mav, UserVO vo) {
	//VO객체에 저장된 값을 Entity에 저장되도록 Builder를 이용해서 Entity객체를 생성함
	UserEntity entity = UserEntity.builder()
						  .userEmail(vo.getUserEmail())
						  .userPw(vo.getUserPw())
						  .userNick(vo.getUserNick())
						  .userName(vo.getUserName())
						  .userPhone(vo.getUserPhone())
						  .birth(vo.getBirth())
						  .build();
	
	AddressEntity a_entity = AddressEntity.builder()
								.addPost(vo.getAddPost())
								.add1(vo.getAdd1())
								.add2(vo.getAdd2())
								.build();
	
	String viewName = "user/join"; //회원가입 실패 시 뷰이름
	
	//MemberServiceImpl클래스를 통해서 요청 처리: JPA에서 지원하는 save()메소드 이용
	UserEntity savedVo = userServiceImpl.save(entity);
	AddressEntity a_savedVo = userServiceImpl.a_save(a_entity, entity);
	
	if(savedVo != null && a_savedVo != null) {//회원가입 성공
		viewName = "redirect:/index.no"; //index.do 재요청
		
	}else {//회원가입 실패
		//안내메시지를 Model객체에 저장함
		mav.addObject("msg", "회원가입이 정상적으로 이루어지지 않았습니다");
	}
	mav.setViewName(viewName);
	
	return mav;
	}
	
	//이메일 인증
	@PostMapping("/checkEmail.no")
	@ResponseBody
	public String checkEmail(String email) {
		System.out.println("이메일 인증 이메일: "+email);
		return userServiceImpl.authEmail(email);
	}
	
	
	
	
	
	@GetMapping("/login.no")
	public String login() {
		return "login/login";
	}
	
	@PostMapping("/loginProcess.no")
	public String loginProcess(String userEmail, String userPw, HttpServletRequest request, Model model) {
		String viewName = "login/login";
		
		UserEntity vo = userServiceImpl.login(userEmail, userPw);
		System.out.println(vo);
		
		if(vo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", vo);
			viewName = "redirect:/index.no";
		}else {
			model.addAttribute("msg", "아이디나 비밀번호가 일치하지 않습니다.");
		}
		
		return viewName;
	}
	
	@GetMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/index.no";
	}
	
}
