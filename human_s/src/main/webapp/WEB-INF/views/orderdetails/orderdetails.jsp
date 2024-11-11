<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/orderdetails.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="orderdetails_container">
	<div class="orderdetails_top">
		<h2>주문완료</h2>
		<p>#님의 주문이 정상적으로 처리되었습니다</p>
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
				<h4>주문상품</h4>
				<span>이용현</span>
				<p>010-####-####</p>
				<p>주문자 정보로 주문 관련 정보가 문자와 이메일로 발송됩니다</p>
				<p>비회원은 이메일과 주문번호로 주문조회가 가능합니다</p>
			</div>
			<div>
				<h4>배송지</h4>
				<p>이용현</p>
				<p>[31115]충청남도 천안시 동남구 북일로 70(힐스테이트 천안 신부) 109동 1803호</p>
				<p>010-####-####</p>
				<p>yonghyunbabo@naver.com</p>
			</div>
		</div>
		<!-- 오른쪽 -->
		<div>
			<p>코딱지</p>
		</div>
	</div>
	<div class="orderdetails_btm">
		<button>MY페이지 주문내역</button>
		<button>쇼핑 계속하기</button>
	</div>
</div>
<%@ include file="../main/footer.jsp"%>
</body>
</html>