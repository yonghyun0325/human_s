package com.human.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping("/login.no")
	public String login() {
		return "login/login";
	}
}
