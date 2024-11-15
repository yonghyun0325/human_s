package com.human.hms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.hms.api.MllBsComNmInfoApiExplorer;
import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.service.UserService;
import com.human.hms.vo.MllBsComNmInfoVO;
import com.human.hms.vo.MllBsComNmInfoVO.MllBsComNmInfo;
import com.human.hms.vo.UserVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	
	//상호명을 통한 통신판매업등록현황 조회(사업자 등록번호 조회 가능 이 안에서 처리함)
	private final UserService userServiceImpl;
	private String ServiceKey = "cHMBzY2Ljo7k/uc9cuO7pzwJoPCyA3zZM5rAV0c6bXxkV6dB66ov2nfRGgk/9P/A55kmN25hvQEB5rK116XY5w==";
	private String srcUrl = "https://apis.data.go.kr/1130000/MllBs_2Service/getMllBsCoNmInfo_2";
	private String numOfRows = "100";
	private String resultType = "json";
	
	//회원가입 약관동의 페이지
	@GetMapping("/agreement.no")
	public String joinAgreement() {
		
		return "join/agreement";
	}
	
	//회원가입 유형선택 페이지
	@GetMapping("/jointype.no")
	public String jointype(HttpServletRequest request) {
		
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
	
	
	@GetMapping("/sellerInfoCheck.no")
	@ResponseBody
	public String sellerInfoCheck(String bzmnNm, String seIdNum) {
		int pageNo = 1;
		String result = "fail";
		
		try {
			Class<MllBsComNmInfoVO> mllVO = MllBsComNmInfoVO.class;
			
			while(true) {
				MllBsComNmInfoVO dataVO = (MllBsComNmInfoVO)MllBsComNmInfoApiExplorer
						.getApiJsonData(ServiceKey,bzmnNm,srcUrl, resultType,String.valueOf(pageNo), numOfRows, mllVO);
				System.out.println("data:"+dataVO);
				if(dataVO.getItems().size()==0) break;
				
				List<MllBsComNmInfo> vo = dataVO.getItems();
				
				for(MllBsComNmInfo vos :vo) {
					System.out.println(vos.getBrno());
					System.out.println(seIdNum);
					if(seIdNum.equals(vos.getBrno())) {
						int result2= userServiceImpl.sellerInfoCheck(seIdNum);
						System.out.println(result2);
						if(result2 == 0) {
							result = "ok";
						}
					}
					
				}
				pageNo++;
			}
		
		} catch (Exception e) {
			System.out.println("사업자 조회 확인 중 오류 발생");
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	@PostMapping("/joinProcess.no")
	public ModelAndView joinProcess(ModelAndView mav, UserVO vo, HttpServletRequest request) {
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
	

	
	if(request.getSession().getAttribute("naver") != null) {
		request.getSession().removeAttribute("naver");
	}
	
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
