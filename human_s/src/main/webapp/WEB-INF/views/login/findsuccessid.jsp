<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기완료</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/findsuccess.css">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<%@ include file="../main/header.jsp"%>
	<div class="success_container">
		<h1>아이디 찾기 결과</h1>
        <p>고객님의 정보와 일치하는 아이디입니다.</p>
	        <div class="findid"> 
	           <P>${user.userEmail}</P>
	        </div>
	        <div class="button">
	        	<button id="findPwBtn">비밀번호 찾기</button>
	        	<button id="loginBtn">로그인</button>
	        </div>
	</div>
<%@ include file="../main/footer.jsp"%>
</body>
<script>
	$('#findPwBtn').on('click', function(){
		window.location.href='findsuccesspw.no';
	})
	$('#loginBtn').on('click', function(){
		window.location.href='login.no';
	})
</script>
</html>