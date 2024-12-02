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
					                <p>수량: ${qty}개 (${product.pdtPrice*qty}원)</p>
					                <input type="hidden" value="${qty}" name="orCount">
					            </div>
					        </div>
					        <div class="price-info">
					            <p>상품금액: <span>${product.pdtPrice*qty}원</span></p>
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
		                    <input type="text" value="${user.userName}" name="orHuman">
		                </div>
		                <div class="form-group">
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
		                    <input type="text" name="orPost" size="5" id="sample6_postcode" readonly>
		                    <button type="button" class="search-button" onclick="sample6_execDaumPostcode()">검색</button>
		                </div>
		                <div class="form-group">
		                    <label>주소 <span class="required">*</span></label>
		                    <input type="text" name="orAdd1" id="sample6_address" readonly>
		                </div>
		                <div class="form-group">
		                    <label>나머지 주소</label>
		                    <input type="text" name="orAdd2" id="sample6_detailAddress">
		                    <input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
		                </div>
		                <div class="form-group">
		                    <label>휴대폰 <span class="required">*</span></label>
		                    <input type="text" name="orRecPhone">
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
		                <p>상품금액: ${product.pdtPrice*qty}</p>
		                <p>배송비: <span>3,000원</span></p>
		                <p>할인금액: <span>0원</span></p>
		                <hr>
		                <p class="total">최종 결제금액: ${product.pdtPrice*qty+3000}<input value="${product.pdtPrice}" name="orPayAmount" readOnly type="hidden">원</p>
		            </div>
		
		            <!-- 결제 수단 -->
		            <div class="payment-method">
		                <h2>결제 수단</h2>
		                <input	type="hidden" name="orPayType" id="orPayType">
		                <button type="button" class="method-button" id="cardBtn">신용카드</button>
		                <div class="cardInfo">
		                	<input placeholder="이름">
		                	<input placeholder="-를 제외한 카드번호 입력" name="orCardNum">
		                	<input placeholder="cvc번호" name="orCvc" maxlength="3">
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
		                <div class="term">
			                <label><input type="checkbox" id="checkbox1"> 전체동의</label>
		                </div>
		                <div class="term">
			                <label><input type="checkbox" id="checkbox2"> 쇼핑몰 이용 약관 (필수)</label><i class="fa-solid fa-chevron-down" id="down1"></i><i class="fa-solid fa-chevron-up" id="up1"></i>
		                </div>
		                <div class="interm" id="interm1">
		                	<p>온라인으로 서비스를 제공하는 경우 플랫폼을 방문하는 고객을 위한 이용약관 및 고객의 개인정보 수집을 위한 내용(개인정보취급방침) 등을 정해야 합니다. 약관의 경우 약관의 규제에 관한 법률 (약관규제법)의 적용을 받아 불공정한 내용이 포함될 경우 고객이 동의하였다고 해도 효력이 없습니다. 그러므로 신중하게 검토하여 작성할 필요가 있습니다. 
		                		일반적으로 이용약관에 들어가는 기본 내용은 회사의 의무, 서비스 제공에 관한 사항, 서비스 목적과 용어 등의 정의 규정부터, 손해배상, 분쟁 시 해결방안 등이 포함되어 있습니다.
								이용약관 검토시 각 항목을 면밀히 점검하여 추가 혹은 불공정한 것으로 판단될 가능성이 높아 제거해야 하는 부분이 없는지 확인이 필요합니다. 표준 약관 혹은 동종 업종의 이용약관 등을 활용해서 작성해도 되지만 전문가의 조력을 받아 사업에 맞게 제대로 설정하는 것이 필요합니다.
								저희 법무법인 테헤란 기업법무팀에서는 프랜차이즈, 엔터테인먼트, 스타트업 등 다양한 분야에 관하여 법률자문 서비스를 제공하고 있습니다. 아래 연락처를 통하여 문의주시면 보다 자세한 상담을 도와드리겠습니다.</p>
		                </div>
            	       	<div class="term">
			                <label><input type="checkbox" id="checkbox3"> 개인정보 수집 및 이용 (필수)</label><i class="fa-solid fa-chevron-down" id="down2"></i><i class="fa-solid fa-chevron-up" id="up2"></i>
            	       	</div>
            	       	<div class="interm" id="interm2">
            	       			<p>온라인으로 서비스를 제공하는 경우 플랫폼을 방문하는 고객을 위한 이용약관 및 고객의 개인정보 수집을 위한 내용(개인정보취급방침) 등을 정해야 합니다. 약관의 경우 약관의 규제에 관한 법률 (약관규제법)의 적용을 받아 불공정한 내용이 포함될 경우 고객이 동의하였다고 해도 효력이 없습니다. 그러므로 신중하게 검토하여 작성할 필요가 있습니다. 
		                		일반적으로 이용약관에 들어가는 기본 내용은 회사의 의무, 서비스 제공에 관한 사항, 서비스 목적과 용어 등의 정의 규정부터, 손해배상, 분쟁 시 해결방안 등이 포함되어 있습니다.
								이용약관 검토시 각 항목을 면밀히 점검하여 추가 혹은 불공정한 것으로 판단될 가능성이 높아 제거해야 하는 부분이 없는지 확인이 필요합니다. 표준 약관 혹은 동종 업종의 이용약관 등을 활용해서 작성해도 되지만 전문가의 조력을 받아 사업에 맞게 제대로 설정하는 것이 필요합니다.
								저희 법무법인 테헤란 기업법무팀에서는 프랜차이즈, 엔터테인먼트, 스타트업 등 다양한 분야에 관하여 법률자문 서비스를 제공하고 있습니다. 아래 연락처를 통하여 문의주시면 보다 자세한 상담을 도와드리겠습니다.</p>
								</div>
						<div class="term">
			                <label><input type="checkbox" id="checkbox4"> 개인정보 제3자 제공 동의 (필수)</label><i class="fa-solid fa-chevron-down" id="down3"></i><i class="fa-solid fa-chevron-up" id="up3"></i>
						</div>
						<div class="interm" id="interm3">
							<p>온라인으로 서비스를 제공하는 경우 플랫폼을 방문하는 고객을 위한 이용약관 및 고객의 개인정보 수집을 위한 내용(개인정보취급방침) 등을 정해야 합니다. 약관의 경우 약관의 규제에 관한 법률 (약관규제법)의 적용을 받아 불공정한 내용이 포함될 경우 고객이 동의하였다고 해도 효력이 없습니다. 그러므로 신중하게 검토하여 작성할 필요가 있습니다. 
		                		일반적으로 이용약관에 들어가는 기본 내용은 회사의 의무, 서비스 제공에 관한 사항, 서비스 목적과 용어 등의 정의 규정부터, 손해배상, 분쟁 시 해결방안 등이 포함되어 있습니다.
								이용약관 검토시 각 항목을 면밀히 점검하여 추가 혹은 불공정한 것으로 판단될 가능성이 높아 제거해야 하는 부분이 없는지 확인이 필요합니다. 표준 약관 혹은 동종 업종의 이용약관 등을 활용해서 작성해도 되지만 전문가의 조력을 받아 사업에 맞게 제대로 설정하는 것이 필요합니다.
								저희 법무법인 테헤란 기업법무팀에서는 프랜차이즈, 엔터테인먼트, 스타트업 등 다양한 분야에 관하여 법률자문 서비스를 제공하고 있습니다. 아래 연락처를 통하여 문의주시면 보다 자세한 상담을 도와드리겠습니다.</p>
						</div>
		            </div>
	
	            	<!-- 결제 버튼 -->
	            	<div class="submit-button">
	           	     <button type="submit">결제하기</button>
	            	</div>
	            	<img src="${pageContext.request.contextPath}/resources/img/결제창배너.png">
	        	</div>
	    	</div>
		</form>
	</div>
</div>
   <%@ include file="../main/footer.jsp"%>
   <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   <script>
   $('input[name="orRecPhone"]').on('input', function () {
	    let phone = $(this).val().replace(/[^0-9]/g, ''); // 숫자만 남기기
	    if (phone.length <= 3) {
	        // 3자리 이하일 경우 그대로
	        $(this).val(phone);
	    } else if (phone.length <= 7) {
	        // 3-4 형식
	        $(this).val(phone.slice(0, 3) + '-' + phone.slice(3));
	    } else {
	        // 3-4-4 형식
	        $(this).val(phone.slice(0, 3) + '-' + phone.slice(3, 7) + '-' + phone.slice(7, 11));
	    }
	});
   
   $('input[name="orphone"]').on('input', function () {
	    let phone = $(this).val().replace(/[^0-9]/g, ''); // 숫자만 남기기
	    if (phone.length <= 3) {
	        // 3자리 이하일 경우 그대로
	        $(this).val(phone);
	    } else if (phone.length <= 7) {
	        // 3-4 형식
	        $(this).val(phone.slice(0, 3) + '-' + phone.slice(3));
	    } else {
	        // 3-4-4 형식
	        $(this).val(phone.slice(0, 3) + '-' + phone.slice(3, 7) + '-' + phone.slice(7, 11));
	    }
	});
   $('#down1').on('click', function() {
	    $('#interm1').css('display', 'flex');
	    $('#down1').css('display', 'none');
	    $('#up1').css('display', 'flex');
	});

	$('#up1').on('click', function() {
	    $('#interm1').css('display', 'none');
	    $('#down1').css('display', 'flex');
	    $('#up1').css('display', 'none');
	});

	$('#down2').on('click', function() {
	    $('#interm2').css('display', 'flex');
	    $('#down2').css('display', 'none');
	    $('#up2').css('display', 'flex');
	});

	$('#up2').on('click', function() {
	    $('#interm2').css('display', 'none');
	    $('#down2').css('display', 'flex');
	    $('#up2').css('display', 'none');
	});

	$('#down3').on('click', function() {
	    $('#interm3').css('display', 'flex');
	    $('#down3').css('display', 'none');
	    $('#up3').css('display', 'flex');
	});

	$('#up3').on('click', function() {
	    $('#interm3').css('display', 'none');
	    $('#down3').css('display', 'flex');
	    $('#up3').css('display', 'none');
	});
   
   </script>
</body>
</html>
