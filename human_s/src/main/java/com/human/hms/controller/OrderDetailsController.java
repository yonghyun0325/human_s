package com.human.hms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orderdetails")
public class OrderDetailsController {

    @GetMapping("/orderdetails.no")
    public String payment(){
        return "orderdetails/orderdetails";
    }
    @GetMapping("/order.no")
    public String order() {
    	return "orderdetails/order";
    }
}
