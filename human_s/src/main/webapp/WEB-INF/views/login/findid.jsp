<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/findidandpw.css">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="../main/header.jsp"%>
	<div class="find_container">
		<h2>아이디 찾기</h2> 
		<p style="color:rgba(0,0,0,0.5)">회원로그인을 통해 더욱 다양한 서비스를 이용하세요.</p>
		<form action="findIdProcess.no" method="post">
			<div class="name">
				<p>이    름</p>
				<i>*</i>
				<input placeholder="이름을 입력하세요" name="userName">
			</div>
			<div class="phone">
				<p>전화번호</p>
				<i>*</i>
				<input placeholder="전화번호를 입력하세요" name="userPhone"> 
			</div>
			<div class="button">
				<button type="submit">아이디 찾기</button>
			</div>
		</form>
	
	</div>

<%@ include file="../main/footer.jsp"%>
</body>
</html>