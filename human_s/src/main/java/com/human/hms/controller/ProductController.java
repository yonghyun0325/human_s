package com.human.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@GetMapping("/popNewList.no")
	public String popNewList() {
		return "product/popNewList";
	}
	
	@GetMapping("/checkBoxList.no")
	public String checkBoxList() {
		return "product/checkBoxList";
	}
	
}
