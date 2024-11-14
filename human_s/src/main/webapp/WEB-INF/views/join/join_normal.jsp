<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/join.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro@4cac1a6/css/all.css" />
	<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
	<script src="https://kit.fontawesome.com/d7e414b2e7.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/popup_2.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/join_normal.js"></script>
	<title>일반 회원가입</title>
	<title>일반 회원가입</title>
</head>

<body>
	<%@ include file="../main/header.jsp"%>
	<h2>회원가입</h2>
	<div class="join_container">
		<form action="joinProcess.no" class="form" method="post">
			<!-- 이메일 -->
			<div class="nor_email">
				<p>이메일<i>*</i></p>
				<div>
					<input type="text" placeholder="이메일을 입력하세요" name="userEmail" class="email">
					<button type="button" id="sameIdBtn">중복확인</button>
				</div>
				<div>
					<input type="text" placeholder="인증번호를 입력하세요" id="emailNumInput">
					<button type="button" id="emailNumberBtn">인증번호확인</button>
				</div>
			</div>
			<!-- 비밀번호 -->
			<div class="nor_pw">
				<p>비밀번호<i>*</i></p>
				<input type="password" placeholder="비밀번호(영문+숫자+특수문자 포함 8자이상)" name="userPw">
				<input type="password" placeholder="비밀번호 재입력">
			</div>
			<hr>
			<!-- 닉네임 -->
			<div class="nor_nickname">
				<p>닉네임<i>*</i></p>
				<input type="text" placeholder="닉네임을 입력하세요" name="userNick">
			</div>
			<!-- 이름 -->
			<div class="nor_name">
				<p>이름<i>*</i></p>
				<input type="text" placeholder="이름을 입력하세요" name="userName">
			</div>
			<hr>
			<!-- 전화번호 -->
			<div class="nor_tel-top">
				<p>전화번호<i>*</i></p>
				<div>
				<input type="text" placeholder="전화번호를 입력하세요" name="userPhone" maxlength="13">
				<button type="button">인증번호 받기</button>
				</div>
			</div>
			<div class="nor_tel-bottom">
				<input type="text" placeholder="인증번호를 입력하세요" maxlength="6">
				<button type="button">인증번호 확인</button>
			</div>
			<hr>
			<!-- 생년월일 -->
			<div class="nor_birth">
				<p>생년월일<i>*</i></p>
				<input type="text" placeholder="생년월일을 입력하세요" name="birth" maxlength="8">
			</div>
			<hr>
			<!-- 주소 -->
			<div class="nor_addr-top">
				<p>주소<i>*</i></p>
				<div>
				<input type="text" id="sample6_postcode" placeholder="우편번호" name="addPost">
				<button type="button" onclick="sample6_execDaumPostcode()" >우편번호 찾기</button>
				</div>
			</div>
			<div class="nor_addr-bottom">
				<input type="hidden" value="0" name="">
				<input type="text" id="sample6_address" placeholder="주소를 입력하세요" name="add1" >
				<input type="text" id="sample6_detailAddress" placeholder="상세주소를 입력하세요" name="add2">
				<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
			</div>
			
			<button type="submit" class="joinbutton" >회원가입 하기</button>	
		</form>
	</div>
	<%@ include file="../main/footer.jsp"%>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
</body>
  
</html>