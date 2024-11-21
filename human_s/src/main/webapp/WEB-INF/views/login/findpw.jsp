<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/findidandpw.css">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/resources/js/find_pw.js"></script>
</head>
<body>
<%@ include file="../main/header.jsp"%>
	<div class="find_container">
		<h2>비밀번호 찾기</h2> 
		<p style="color:rgba(0,0,0,0.5)">회원로그인을 통해 더욱 다양한 서비스를 이용하세요.</p>
		<form action="findPwProcess.no" method="post">
			<div class="email">
				<p>이메일</p>
				<i>*</i>
				<input placeholder="이메일을 입력하세요" id="emailInput" name="userEmail">
				<button type="button" id="sendEmailNumBtn">인증번호 받기</button>
			</div>
			<div class="authnum">
				<p>인증번호</p>
				<i>*</i>
				<input placeholder="인증번호를 입력하세요" id="authNum">
				<button type="button" id="confirmAuthNumBtn">인증번호 확인</button> 
			</div>
			<div class="button">
				<button id="changePwPageBtn" type="submit">다음</button>
			</div>
		</form>
	</div>

<%@ include file="../main/footer.jsp"%>
</body>
<script>
</script>
</html>