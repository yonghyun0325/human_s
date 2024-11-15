package com.human.hms.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.human.hms.entity.UserEntity;
import com.human.hms.service.UserService;
import com.human.hms.vo.NaverUserVO;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ApiLoginController {
	
	private UserService userServiceImpl;
	
	//로그인페이지 호출
	@GetMapping("/naver_login")
	public String naver_login(HttpServletRequest request) {
	    String client_id = "PEiEn7jvNKz7sOSYejxo";
	    String redirect_uri = "http://localhost:9090/hms/naver_redirect";
	    String state = "justNaver";
	    String login_url = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
	            + "&client_id=" + client_id
	            + "&redirect_uri=" + redirect_uri
	            + "&state=" + state;
	    request.getSession().setAttribute("state", state);

	    return "redirect:" + login_url;
	}
	
	//액세스 토큰가져오기
	@GetMapping("/naver_redirect")
	public String naver_redirect(HttpServletRequest request) {
		
		String viewName = "login/login";
		// 네이버에서 전달해준 code, state 값 가져오기
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    
		// 세션에 저장해둔 state값 가져오기
	    String session_state = String.valueOf(request.getSession().getAttribute("state"));

		// CSRF 공격 방지를 위해 state 값 비교
	    if (!state.equals(session_state)) {
	        System.out.println("세션 불일치");
	        request.getSession().removeAttribute("state");
	        return "/error";
	    }

	    String tokenURL = "https://nid.naver.com/oauth2.0/token";
	    String client_id = "PEiEn7jvNKz7sOSYejxo";
	    String client_secret = "4UADym5eyI";

	    // body data 생성
	    MultiValueMap<String, String> parameter = new LinkedMultiValueMap<>();
	    parameter.add("grant_type", "authorization_code");
	    parameter.add("client_id", client_id);
	    parameter.add("client_secret", client_secret);
	    parameter.add("code", code);
	    parameter.add("state", state);
	    
	    // request header 설정
	    HttpHeaders headers = new HttpHeaders();
	    // Content-type을 application/x-www-form-urlencoded 로 설정
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    // header 와 body로 Request 생성
	    HttpEntity<?> entity = new HttpEntity<>(parameter, headers);
	    
	    try {
	        RestTemplate restTemplate = new RestTemplate();
	        // 응답 데이터(json)를 Map 으로 받을 수 있도록 관련 메시지 컨버터 추가
	        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

	        // Post 방식으로 Http 요청
	        // 응답 데이터 형식은 Hashmap 으로 지정
	        ResponseEntity<HashMap> result = restTemplate.postForEntity(tokenURL, entity, HashMap.class);
	        Map<String, String> resMap = result.getBody();
	        
			// 응답 데이터 확인
	        System.out.println("응답내용: "+resMap);
	        HttpSession session = request.getSession();
	        
	        NaverUserVO naver = getNaverUserInfo(resMap.get("access_token"));
	        if(userServiceImpl.sameIdcheck(naver.getEmail()) == 1) {
	        	UserEntity user = userServiceImpl.equalsUser(naver.getEmail());
	        	session.setAttribute("user", user);
	        	viewName = "redirect:/index.no";
	        }else {
	        	
	        	session.setAttribute("naver", naver);
	        	
	        	viewName = "join/jointype";
	        }
	        
	        
			
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return viewName;
	}
	
	public NaverUserVO getNaverUserInfo(String accessToken){
        String reqUrl = "https://openapi.naver.com/v1/nid/me";
        
        RestTemplate restTemplate = new RestTemplate();
        
        // HttpHeader 오브젝트
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        
        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers);
        
        ResponseEntity<String> response = restTemplate.exchange(reqUrl,
                                                  HttpMethod.POST,
                                                  naverProfileRequest,
                                                  String.class);
        
        System.out.println("response = " + response);
        NaverUserVO naverProfile = new NaverUserVO(response.getBody());
        
        return naverProfile;
    }
}
