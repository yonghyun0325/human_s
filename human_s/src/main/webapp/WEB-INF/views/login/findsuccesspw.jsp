<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/findsuccess.css">
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/findsuccess.js"></script>
<body>
<%@ include file="../main/header.jsp"%>
	<div class="success_container">
			<h1>비밀번호 변경</h1>
			<p>아이디:${userEmail}</p>
			<form action="changePwProcess.no" method="post">
			<input type="hidden" name="userEmail" value="${userEmail}">
	        <div class="changepw"> 
	           <div class="newpw">
	           	   <p>새비밀번호</p>
		           <input placeholder= "비밀번호는 영문, 특수문자 포함 8자리 이상으로 입력해주세요" name="userPw">
	           </div>
	           <div class="renewpw">
	           		<p>새비밀번호 재입력</p>
	           		<input placeholder="비밀번호 재입력">
	           </div>
	        </div>
	        <div class="changepwBtn">
	        	<button id="findPwBtn" type="submit">비밀번호 변경하기</button>
	        </div>
	        </form>
	</div>
	
<%@ include file="../main/footer.jsp"%>
</body>
</html>