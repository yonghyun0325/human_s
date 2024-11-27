package com.human.hms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.FavoriteEntity;
import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.service.MyPageService;
import com.human.hms.vo.UserVO;

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
    //userUpdate.jsp로 이동
    @GetMapping("/update.do")
    public String showUserUpdate(Model model, HttpServletRequest request){
    	UserEntity entity = (UserEntity) request.getSession().getAttribute("user");
    	//userIdx값으로 주소 불러오기
    	AddressEntity address = myPageService.getAddress(entity.getUserIdx());
    	model.addAttribute("add", address);
        return "mypage/userUpdate";
    }    
    
    //회원정보 업데이트
    @PostMapping("/updateProcess.do")
    public String updateProcess(int userAdd,int userIdx,UserVO vo, HttpServletRequest request) {
        String viewName = "mypage/userUpdate"; // 기본적으로 실패 시 이동할 뷰
        
       
        UserEntity userEntity = UserEntity.builder()
        		.userIdx(userIdx)
                .userName(vo.getUserName())
                .userPhone(vo.getUserPhone())
                .userPw(vo.getUserPw())
                .userNick(vo.getUserNick())
                .build();

        AddressEntity addressEntity = AddressEntity.builder()
                .addPost(vo.getAddPost())
                .addIdx(userAdd)
                .add1(vo.getAdd1())
                .add2(vo.getAdd2())
                .build();

        // 업데이트 서비스 호출
        int result = myPageService.updateInfo(userEntity, addressEntity);

        // 세션 갱신 (성공 시)
        if (result == 1) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userEntity); // 세션에 업데이트된 사용자 정보 저장
            session.setAttribute("add", addressEntity); // 세션에 업데이트된 주소 정보 저장
            viewName = "redirect:/index.no"; // 성공 시 마이페이지로 이동
        }

        return viewName;
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
    @GetMapping("/addaddress.do")
    public String addAddressPage() {
        return "mypage/addaddress";
    }
    //취소환불내역 이동
    @GetMapping("orderRefundCancel.no")
    public String orderRefundCancel() {
    	return "mypage/orderRefundCancel";
    }
}
