package com.human.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/story")
public class StoryController {

	@GetMapping("/farmstory.no")
	public String story() {
		return "story/story";
	}
}
