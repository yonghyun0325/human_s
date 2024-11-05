package com.human.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
    
    // mypage.jsp로 이동
    @GetMapping("/mypage.do")
    public String showMyPage(){
        return "mypage/mypage";
    }
    // UserUpdate.jsp로 이동
    @GetMapping("/update.do")
    public String showUserUpdate(){
        return "mypage/userUpdate";
    }
}
