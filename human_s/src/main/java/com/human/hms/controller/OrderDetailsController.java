package com.human.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.human.hms.entity.ProductEntity;
import com.human.hms.service.ProductService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/orderdetails")
public class OrderDetailsController {
	
	private ProductService productServiceImpl;
	
    @GetMapping("/orderdetails.no")
    public String payment(){
        return "orderdetails/orderdetails";
    }
    @GetMapping("/order.no")
public String order(@RequestParam("p_idx")int idx, Model model) {
		System.out.println(idx);
		  ProductEntity product = productServiceImpl.findbyId(idx);
		  model.addAttribute("product", product);
		 
    	return "orderdetails/order";
    }
    
}
