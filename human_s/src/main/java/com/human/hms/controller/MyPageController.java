package com.human.hms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.hms.entity.AddressEntity;
import com.human.hms.entity.BasketEntity;
import com.human.hms.entity.FavoriteEntity;
import com.human.hms.entity.OrderListEntity;
import com.human.hms.entity.ReviewEntity;
import com.human.hms.entity.UserEntity;
import com.human.hms.service.MyPageService;
import com.human.hms.vo.AddressVO;
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
    public String showAddressManagement(HttpSession session, Model model){
    	UserEntity user = (UserEntity) session.getAttribute("user");
    	if (user != null) {
			List<AddressEntity> addressList = myPageService.getAddressesByUserId(user.getUserIdx());
			model.addAttribute("user",user);
			model.addAttribute("addressList",addressList);
		} else {
			model.addAttribute("error","로그인이 필요합니다.");
		}
        return "mypage/addressmanagement";
    }
    // 새로운 주소 추가 요청 처리
    @PostMapping("/addaddress.do")
    @ResponseBody
    public String addAddress(HttpSession session, AddressVO vo, Model model) {
    	
    	System.out.println("addAddress 처리 요청 메소드 실행");
    	
    	String result = "SUCCESS";//정상적으로 배송지 추가시 결과값
    	
    	AddressEntity addressEntity= AddressEntity.builder()
    			.addPost(vo.getAddPost())
    			.add1(vo.getAdd1())
    			.add2(vo.getAdd2())
    			.addressName(vo.getAddressName())
    			.receiverName(vo.getReceiverName())
    			.phoneNumber(vo.getPhoneNumber())
    			.orderMessage(vo.getOrderMessage())
    			.build();
    	UserEntity user = (UserEntity) session.getAttribute("user");
    	addressEntity.updateUserEntity(user);
    	if (user != null) {
            // 중복 데이터 확인
            if (myPageService.isAddressDuplicate(user.getUserIdx(), addressEntity)) {
                //model.addAttribute("error", "이미 동일한 주소가 등록되어 있습니다.");
                result="이미 동일한 주소가 등록되어 있습니다.";
                //return "mypage/addaddress"; // 오류 메시지와 함께 다시 주소 추가 페이지로 이동
            }

            // 기본 배송지 설정 여부 확인
            if (addressEntity.getAddStatus() == 0) {
                // 만약 새로운 기본 배송지를 추가하면 기존 기본 배송지를 모두 추가 배송지로 변경
                myPageService.updateAllAddressesToAdditional(user.getUserIdx());
            }

            // 유저와 연관된 주소로 설정
            addressEntity.updateUserEntity(user);
            myPageService.saveAddress(addressEntity);
            
            //return "redirect:/mypage/address.do"; // 저장 후 주소 관리 페이지로 리다이렉트
        } else {
            //model.addAttribute("error", "로그인이 필요합니다.");
            result="로그인이 필요합니다.";
            //return "mypage/addaddress"; // 로그인 필요 페이지로 이동
        }
    	return result;
    }
    //추가된 주소 삭제하기
    @DeleteMapping("/deleteaddress.do/{addressId}")
    @ResponseBody
    public String deleteAddress(@PathVariable int addressId, HttpSession session) {

        // 삭제 서비스 호출
        boolean isDeleted = myPageService.deleteAddressById(addressId);

        // 결과 반환
        return isDeleted ? "SUCCESS" : "FAIL";
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
    public String showBasket(Model model, HttpServletRequest request){
    	UserEntity user = (UserEntity) request.getSession().getAttribute("user");
    	List<BasketEntity> basketList = myPageService.getBasketByUser(user.getUserIdx());
    	model.addAttribute("basketList", basketList);
    	
    	List<FavoriteEntity> favoriteList = myPageService.getFavoriteByUser(user.getUserIdx());
    	model.addAttribute("favoriteList", favoriteList);
    	
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
