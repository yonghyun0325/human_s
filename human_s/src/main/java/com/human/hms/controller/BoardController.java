package com.human.hms.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@GetMapping("/notice.no")
    public String list(){
    	
		return "board/notice";
    }
	@GetMapping("/review")
	public String review() {
		
		return "board/review";
	}
	
	@GetMapping("/product")
	public String product() {
		
		return "board/productquestion";
	}
	@GetMapping("/faq")
	public String faq() {
		
		return "board/faq";
	}
	
	
}
