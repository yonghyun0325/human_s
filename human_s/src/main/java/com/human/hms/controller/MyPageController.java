package com.human.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyPageController {
    
    // mypage.jsp로 이동
    @GetMapping("/mypage")
    public String showMyPage(){
        return "mypage/mypage";
    }
}
