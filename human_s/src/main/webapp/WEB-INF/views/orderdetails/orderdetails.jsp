<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문완료</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/orderdetails.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="orderdetails_container">
	<div class="orderdetails_top">
		<h2>주문완료</h2>
		<p>주문자님의 주문이 정상적으로 처리되었습니다</p>
	</div>
	<!-- 바디부분 -->
	<div class="orderdetails_mid">
		<!-- 왼쪽 -->
		<div>
			<h2>주문상품</h2>
			<div>
				<div>
					<p>배송:<span>[#농장]</span>택배 ####(주문 시 결제)</p>
				</div>
				<div>
					<div>
						<img src="${pageContext.request.contextPath}/resources/img/kimchi.jpg" alt="상품사진">
					</div>
				
				</div>
			</div>
			<div>
				<h4>주문자</h4>
				<c:choose>
				    <c:when test="${not empty sessionScope.user}">
				        <p>${sessionScope.user.userName}</p>
				        <p>${sessionScope.user.userPhone}</p>
				        <p>${sessionScope.user.userEmail}</p>
				    </c:when>
				
				    <c:otherwise>
				        <p>${unuser.unName}</p>
				        <p>${unuser.unPhone}</p>
				        <p>${unuser.unEmail}</p>
				    </c:otherwise>
				</c:choose>

				<p>주문자 정보로 주문 관련 정보가 문자와 이메일로 발송됩니다</p>
				<p>비회원은 이메일과 주문번호로 주문조회가 가능합니다</p>
			</div>
			<div>
				<h4>배송지</h4>
				<p>${order.orRecHuman}</p>
				<p>[${order.orPost}]${order.orAdd1} ${order.orAdd2}</p>
				<p>${order.orRecPhone}</p>
				<p>배송메시지:${order.orMessage}</p>
				
			</div>
		</div>
		<!-- 오른쪽 -->
		<div>
			<p>코딱지</p>
		</div>
	</div>
	<div class="orderdetails_btm">
		<button id="mypageBtn">MY페이지 주문내역</button>
		<button id="shoppingBtn">쇼핑 계속하기</button>
	</div>
</div>
<%@ include file="../main/footer.jsp"%>
<script>
	$('#mypageBtn').on('click', function(){
		window.location.href="../mypage/order.do";
		
		
	});
	
	$('#shoppingBtn').on('click', function(){
		window.location.href="../index.no";
		
		
	});
	
	
</script>
</body>
</html>