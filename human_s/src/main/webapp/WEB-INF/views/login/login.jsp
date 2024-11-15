<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/login.js"></script>
</head>
<body>
	<%@ include file="../main/header.jsp"%>
	<img class="logo"src="${pageContext.request.contextPath}/resources/img/Logo.webp"
	onclick="location.href='/hms/'">
	<div class="login_type_btn">
			<button>회원</button>
			<button>비회원</button>
		</div>
	<div class="login_container"> 
		
		<form action="loginProcess.no" method="post">
			<div class="id_and_pw">
				<input type="text" name="userEmail" placeholder="아이디를 입력하세요">
				<input type="password" name="userPw" placeholder="비밀번호를 입력하세요">
			</div>		
			<button class="login_btn">로그인</button>
		</form>
		<div class="find_and_join">
			<a href="#">아이디찾기</a>
			<a href="#">비밀번호찾기</a>
			<a href="/hms/user/agreement.no">회원가입</a>
		</div>
		<div class="easy_login">
			<button><img src="${pageContext.request.contextPath}/resources/img/카카오.PNG" alt="카카오"></button>
			<button type="button" id="naverApiBtn"><img src="${pageContext.request.contextPath}/resources/img/네이버.PNG" alt="네이버"></button>
			<button type="button"><img src="${pageContext.request.contextPath}/resources/img/구글.PNG" alt="구글"></button>
		</div>
		<div class="login-bottom">
			<p>간편로그인/회원가입</p>
		</div>
		
	</div>
<%@ include file="../main/footer.jsp"%>

</body>
</html>