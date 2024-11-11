<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>주문/결제</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/order.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<div class="container">
    <h1>주문/결제</h1>
    <div class="content">
        <!-- 왼쪽 섹션 -->
        <div class="left-section">
            <!-- 주문상품 정보 -->
            <div class="order-product-info">
			    <h2>주문상품 정보</h2>
			    <div class="shipping-info">
			        <p>배송 : <span>편의노동희 택배 (선불) 3,000원</span></p>
			        <button>배송 변경</button>
			    </div>
			    <div class="product-item">
			        <div class="product-image-container">
			            <img src="${pageContext.request.contextPath}/resources/img/han.jpg" alt="상품 이미지">
			            <div class="product-details">
			                <p>수제 함박스테이크 100g 한 팩</p>
			                <p>수량: 1개 (2,200원)</p>
			            </div>
			        </div>
			        <div class="price-info">
			            <p>상품금액: <span>2,200원</span></p>
			            <p>할인금액: <span>-</span></p>
			            <p>할인적용금액: <span>2,200원</span></p>
			        </div>
			    </div>
			</div>

            <!-- 주문자 정보 -->
            <div class="order-info">
                <h2>주문자</h2>
                <div class="form-group order-name">
                    <label>주문자 이름 <span class="required">*</span></label>
                    <input type="text" name="ordererName" required>
                </div>
                <div class="form-group phone-group">
                    <label>휴대폰 <span class="required">*</span></label>
                    <input type="text" name="phone1" size="3" required> - 
                    <input type="text" name="phone2" size="4" required> - 
                    <input type="text" name="phone3" size="4" required>
                </div>
                <div class="form-group phone-group">
                    <label>연락처2 (선택)</label>
                    <input type="text" name="altPhone1" size="3"> - 
                    <input type="text" name="altPhone2" size="4"> - 
                    <input type="text" name="altPhone3" size="4">
                </div>
                <div class="form-group">
                    <label>이메일 <span class="required">*</span></label>
                    <input type="email" name="email" required>
                </div>
            </div>

            <!-- 배송지 정보 -->
            <div class="delivery-info">
                <h2>배송지</h2>
                <div class="form-group receiver-name">
                    <label>받는 분 <span class="required">*</span></label>
                    <input type="text" name="receiverName" required>
                    <button type="button" class="same-info-button">주문자 정보와 동일</button>
                </div>
                <div class="form-group post-number">
                    <label>우편번호 <span class="required">*</span></label>
                    <input type="text" name="postalCode" size="5" required>
                    <button type="button" class="search-button">검색</button>
                </div>
                <div class="form-group">
                    <label>주소 <span class="required">*</span></label>
                    <input type="text" name="address" required>
                </div>
                <div class="form-group">
                    <label>나머지 주소</label>
                    <input type="text" name="detailedAddress">
                </div>
                <div class="form-group phone-group">
                    <label>휴대폰 <span class="required">*</span></label>
                    <input type="text" name="receiverPhone1" size="3" required> - 
                    <input type="text" name="receiverPhone2" size="4" required> - 
                    <input type="text" name="receiverPhone3" size="4" required>
                </div>
                <div class="form-group">
                    <label>배송 메시지 입력</label>
                    <input type="text" name="deliveryMessage">
                </div>
            </div>
        </div>

        <!-- 오른쪽 섹션 -->
        <div class="right-section">
            <!-- 결제 금액 -->
            <div class="payment-info">
                <h2>결제 금액</h2>
                <p>상품금액: <span>2,200원</span></p>
                <p>배송비: <span>3,000원</span></p>
                <p>할인금액: <span>0원</span></p>
                <hr>
                <p class="total">최종 결제금액: <span>5,200원</span></p>
            </div>

            <!-- 결제 수단 -->
            <div class="payment-method">
                <h2>결제 수단</h2>
                <button class="method-button active">신용카드</button>
                <button class="method-button">실시간 계좌이체</button>
                <button class="method-button">가상계좌</button>
                <button class="method-button">무통장입금</button>
            </div>

            <!-- 약관 동의 -->
            <div class="terms">
                <h2>약관 동의</h2>
                <label><input type="checkbox"> 전체동의</label>
                <label><input type="checkbox"> 쇼핑몰 이용 약관 (필수)</label>
                <label><input type="checkbox"> 개인정보 수집 및 이용 (필수)</label>
                <label><input type="checkbox"> 개인정보 제3자 제공 동의 (필수)</label>
            </div>

            <!-- 결제 버튼 -->
            <div class="submit-button">
                <button type="button">결제하기</button>
            </div>
        </div>
    </div>
</div>
</div>
   <%@ include file="../main/footer.jsp"%>
</body>
</html>
