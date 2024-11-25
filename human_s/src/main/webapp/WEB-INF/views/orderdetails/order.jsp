<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>주문/결제</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/order.css">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
	<script src="https://kit.fontawesome.com/d7e414b2e7.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/popup_2.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/order.js"></script>
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
	<div class="container">
	    <h1>주문/결제</h1>
		<form action="orderProcess.no" method="post">
		    <div class="content">
		        <!-- 왼쪽 섹션 -->
		        <input type="hidden" value="${product.pdtIdx}" name="pdtIdx">
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
					            <img src="${pageContext.request.contextPath}/resources/uploads/${product.pdtSave}">
					            <div class="product-details">
					                <p>${product.pdtTitle}</p>
					                <input type="hidden" name="orName" value="${product.pdtTitle}">
					                <p>수량: 1개 (${product.pdtPrice}원)</p>
					                <input type="hidden" value="1" name="orCount">
					            </div>
					        </div>
					        <div class="price-info">
					            <p>상품금액: <span>${product.pdtPrice}원</span></p>
					            <p>할인금액: <span>-</span></p>
					            <p>할인적용금액: <span>-</span></p>
					        </div>
					    </div>
					</div>
			
		            <!-- 주문자 정보 -->
		            <div class="order-info">
		                <h2>주문자</h2>
		                <div class="form-group order-name">
		                    <label>주문자 이름 <span class="required">*</span></label>
		                    <input type="text" value="${user.userName}" name="orHuman" required>
		                </div>
		                <div class="form-group phone-group">
		                    <label>휴대폰 <span class="required">*</span></label>
		                    <input type="text" name="orphone"  value="${user.userPhone}" required>
		                </div>
		                <div class="form-group">
		                    <label>이메일 <span class="required">*</span></label>
		                    <input type="email" name="orEmail" value="${user.userEmail}" required>
		                </div>
		            </div>
		
		            <!-- 배송지 정보 -->
		            <div class="delivery-info">
		                <h2>배송지</h2>
		                <div class="form-group receiver-name">
		                    <label>받는 분 <span class="required">*</span></label>
		                    <input type="text" name="orRecHuman" required>
		                </div>
		                <div class="form-group post-number">
		                    <label>우편번호 <span class="required">*</span></label>
		                    <input type="text" name="orPost" size="5" id="sample6_postcode" required>
		                    <button type="button" class="search-button" onclick="sample6_execDaumPostcode()">검색</button>
		                </div>
		                <div class="form-group">
		                    <label>주소 <span class="required">*</span></label>
		                    <input type="text" name="orAdd1" id="sample6_address" required>
		                </div>
		                <div class="form-group">
		                    <label>나머지 주소</label>
		                    <input type="text" name="orAdd2" id="sample6_detailAddress">
		                    <input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
		                </div>
		                <div class="form-group phone-group">
		                    <label>휴대폰 <span class="required">*</span></label>
		                    <input type="text" name="orRecPhone" size="3" required>
		                </div>
		                <div class="form-group">
		                    <label>배송 메시지 입력</label>
		                    <input type="text" name="orMessage">
		                </div>
		            </div>
		        </div>
		
		        <!-- 오른쪽 섹션 -->
		        <div class="right-section">
		            <!-- 결제 금액 -->
		            <div class="payment-info">
		                <h2>결제 금액</h2>
		                <p>상품금액: ${product.pdtPrice}</p>
		                <p>배송비: <span>3,000원</span></p>
		                <p>할인금액: <span>0원</span></p>
		                <hr>
		                <p class="total">최종 결제금액: ${product.pdtPrice+3000}<input value="${product.pdtPrice}" name="orPayAmount" readOnly type="hidden">원</p>
		            </div>
		
		            <!-- 결제 수단 -->
		            <div class="payment-method">
		                <h2>결제 수단</h2>
		                <input	type="hidden" name="orPayType" id="orPayType">
		                <button type="button" class="method-button" id="cardBtn">신용카드</button>
		                <div class="cardInfo">
		                	<input placeholder="이름">
		                	<input placeholder="-를 제외한 카드번호 입력" name="orCardNum">
		                	<input placeholder="cvc번호" name="orCvc">
		                </div>
		                <button type="button" class="method-button" id="bankBtn">계좌이체</button>
		            	<div class="bankInfo">
		            		<input placeholder="이름">
		            		<input placeholder="은행명">
		            		<input placeholder="-를 제외한 계좌번호 입력" name="orBankNum">
		            	</div>
		            </div>
		
		            <!-- 약관 동의 -->
		            <div class="terms">
		                <h2>약관 동의</h2>
		                <label><input type="checkbox" id="checkbox1"> 전체동의</label>
		                <label><input type="checkbox" id="checkbox2"> 쇼핑몰 이용 약관 (필수)</label>
		                <label><input type="checkbox" id="checkbox3"> 개인정보 수집 및 이용 (필수)</label>
		                <label><input type="checkbox" id="checkbox4"> 개인정보 제3자 제공 동의 (필수)</label>
		            </div>
	
	            	<!-- 결제 버튼 -->
	            	<div class="submit-button">
	           	     <button type="submit">결제하기</button>
	            	</div>
	        	</div>
	    	</div>
		</form>
	</div>
</div>
   <%@ include file="../main/footer.jsp"%>
   <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>
