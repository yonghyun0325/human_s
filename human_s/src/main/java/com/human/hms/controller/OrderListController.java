package com.human.hms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.ProductEntity;
import com.human.hms.entity.UnUserEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.service.OrderListService;
import com.human.hms.service.ProductService;
import com.human.hms.service.UserService;
import com.human.hms.vo.OrderListVO;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/orderdetails")
public class OrderListController {
	
	private ProductService productServiceImpl;
	private OrderListService OrderListServiceImpl;
	private UserService userServiceImpl;
	
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
    
    @PostMapping("/orderProcess.no")
    public String orderProcess(OrderListVO vo, Model model, HttpServletRequest request) {
    	String viewName = "orderdetail/order.no?p_idx="+vo.getPdtIdx();//실패시 뷰네임
    	OrderListEntity entity = OrderListEntity.builder()
    									.orPayAmount(vo.getOrPayAmount()+3000)
    									.orPayType(vo.getOrPayType())
    									.orName(vo.getOrName())
    									.orCount(vo.getOrCount())
    									.orPost(vo.getOrPost())
    									.orAdd1(vo.getOrAdd1())
    									.orAdd2(vo.getOrAdd2())
    									.orRecHuman(vo.getOrRecHuman())
    									.orRecPhone(vo.getOrRecPhone())
    									.orMessage(vo.getOrMessage())
    									.orCardNum(vo.getOrCardNum())
    									.orCvc(vo.getOrCvc())
    									.orBankName(vo.getOrBankName())
    									.orBankNum(vo.getOrBankNum())
    									.build();
    	entity.updateProductEntity(productServiceImpl.findbyId(vo.getPdtIdx()));
    	
    	//회원인지 비회원인지 조회
    	if(userServiceImpl.sameIdcheck(vo.getOrEmail()) == 0) {
    		UnUserEntity un_entity = UnUserEntity.builder() 
    									.unName(vo.getOrHuman())
    									.unPhone(vo.getOrphone())
    									.unEmail(vo.getOrEmail())
    									.build();
    		model.addAttribute("unuser", un_entity);
    		if(OrderListServiceImpl.orderUnuser(un_entity, entity) != null) {
        		viewName = "orderdetails/orderdetails";
        		model.addAttribute("order", entity);
        	}
    	}else {
    		UserEntity u_entity =  (UserEntity)request.getSession().getAttribute("user");
    		entity.updateUserEntity(u_entity);//orderListEntity에 대한 user_idx 값 메소드
    		if(OrderListServiceImpl.orderuser(entity) != null) {
        		viewName = "orderdetails/orderdetails";
        		model.addAttribute("order", entity);
        	}
    	}
    	return viewName;
    }
    
}
