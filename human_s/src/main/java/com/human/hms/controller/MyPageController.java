package com.human.hms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.human.hms.entity.UserEntity;
import com.human.hms.service.MyPageService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	@Autowired
	private MyPageService myPageService;
    
    // 공통적으로 세션 데이터를 전달하는 메서드
    private void addSessionDataToModel(HttpSession session, Model model) {
        UserEntity user = (UserEntity) session.getAttribute("user"); // 세션에서 사용자 이름 가져오기
        if (user != null) {
        	model.addAttribute("userName", user.getUserName()); // 사용자 이름
            model.addAttribute("userPhone", user.getUserPhone()); // 사용자 전화번호
            model.addAttribute("userEmail", user.getUserEmail()); // 사용자 이메일
            model.addAttribute("userNick", user.getUserNick()); // 사용자 닉네임
        } else {
        	model.addAttribute("userName", "Guest"); // 기본값
            model.addAttribute("userPhone", "N/A");
            model.addAttribute("userEmail", "N/A");
        }
    }
    // mypage.jsp로 이동
    @GetMapping("/mypage.do")
    public String showMyPage(HttpSession session, Model model){
        addSessionDataToModel(session, model);
        return "mypage/mypage";
    }
    // userUpdate.jsp로 이동
    @GetMapping("/update.do")
    public String showUserUpdate(){
        return "mypage/userUpdate";
    }
    // orderShippingStatus.jsp로 이동
    @GetMapping("/order.do")
    public String showOrderShippingStatus(){
        return "mypage/orderShippingStatus";
    }
    // addressmanagement.jsp로 이동
    @GetMapping("/address.do")
    public String showAddressManagement(){
        return "mypage/addressmanagement";
    }
    // discountcoupon.jsp로 이동
    @GetMapping("/coupon.do")
    public String showDiscountCoupon(){
        return "mypage/discountcoupon";
    }
    // pointsdetails.jsp로 이동
    @GetMapping("/points.do")
    public String showPointsDetails(){
        return "mypage/pointsdetails";
    }
    // pointsdetails.jsp로 이동
    @GetMapping("/favorite.do")
    public String showFavoriteProducts(){
        return "mypage/favoriteproducts";
    }
    // todaygoods.jsp로 이동
    @GetMapping("/todaygoods.do")
    public String showTodayGoods(){
        return "mypage/todaygoods";
    }
    // basket.jsp로 이동
    @GetMapping("/basket.do")
    public String showBasket(){
        return "mypage/basket";
    }
    // inquiry.jsp로 이동
    @GetMapping("/inquiry.do")
    public String showInquiry(){
        return "mypage/inquiry";
    }
    // inquirywrite.jsp로 이동
    @GetMapping("/write.do")
    public String showInquiryWrite(){
        return "mypage/inquirywrite";
    }
    // myarticle.jsp로 이동
    @GetMapping("/myarticle.do")
    public String showMyArticle(){
        return "mypage/myarticle";
    }
}
