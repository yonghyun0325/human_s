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
