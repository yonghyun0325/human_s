package com.human.hms.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.human.hms.entity.FavoriteEntity;
import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.service.MyPageService;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	
	@Autowired
	private MyPageService myPageService;
    
    // 공통적으로 세션 데이터를 전달하는 메서드
    private UserEntity addSessionDataToModel(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user"); // 세션에서 사용자 이름 가져오기
        return user;
    }
    // mypage.jsp로 이동
    @GetMapping("/mypage.do")
    public String showMyPage(HttpSession session, Model model){
    	UserEntity user = addSessionDataToModel(session);
    	List<ReviewEntity> listReview = myPageService.getReviewList(user.getUserIdx());
    	model.addAttribute("review_list", listReview);
        return "mypage/mypage";
    }
    // userUpdate.jsp로 이동
    @GetMapping("/update.do")
    public String showUserUpdate(){
        return "mypage/userUpdate";
    }
    // orderShippingStatus.jsp로 이동
    @GetMapping("/order.do")
    public String showOrderShippingStatus(HttpSession session, Model model){
    	UserEntity user = addSessionDataToModel(session);
    	if (user != null) {
			List<OrderListEntity> orderList = myPageService.getOrderList(user.getUserIdx());
			model.addAttribute("orderList",orderList);
		} else {
			model.addAttribute("error","로그인이 필요합니다.");
		}
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
    public String showFavoriteProducts(HttpSession session, Model model){
    	UserEntity user = addSessionDataToModel(session);
    	List<FavoriteEntity> favoriteEntities = myPageService.getFavoriteByUser(user.getUserIdx());
    	model.addAttribute("favorites", favoriteEntities);
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
