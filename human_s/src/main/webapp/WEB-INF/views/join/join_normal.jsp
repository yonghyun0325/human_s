<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/join.css">
	<script src="../resources/js/jquery-3.7.1.min.js"></script>
	<script src="https://kit.fontawesome.com/d7e414b2e7.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro@4cac1a6/css/all.css" />
	<title>일반 회원가입</title>
</head>

<body>
	<%@ include file="../main/header.jsp"%>
	<h2>회원가입</h2>
	<div class="join_container">
		<form action="#" class="form">
			<!-- 이메일 -->
			<div class="nor_email">
				<p>이메일<i>*</i></p>
				<div>
				<input type="text" placeholder="이메일을 입력하세요" name="">
				<button>중복확인</button>
				</div>
			</div>
			<!-- 비밀번호 -->
			<div class="nor_pw">
				<p>비밀번호<i>*</i></p>
				<input type="text" placeholder="비밀번호(영문+숫자+특수문자 포함 8자이상)" name="">
				<input type="text" placeholder="비밀번호 재입력">
			</div>
			<hr>
			<!-- 닉네임 -->
			<div class="nor_nickname">
				<p>닉네임<i>*</i></p>
				<input type="text" placeholder="닉네임을 입력하세요" name="">
			</div>
			<!-- 이름 -->
			<div class="nor_name">
				<p>이름<i>*</i></p>
				<input type="text" placeholder="이름을 입력하세요" name="">
			</div>
			<hr>
			<!-- 전화번호 -->
			<div class="nor_tel-top">
				<p>전화번호<i>*</i></p>
				<div>
				<input type="text" placeholder="전화번호를 입력하세요" name="">
				<button>인증번호 받기</button>
				</div>
			</div>
			<div class="nor_tel-bottom">
				<input type="text" placeholder="인증번호를 입력하세요" name="">
				<button>인증번호 확인</button>
			</div>
			<hr>
			<!-- 생년월일 -->
			<div class="nor_birth">
				<p>생년월일<i>*</i></p>
				<input type="text" placeholder="생년월일을 입력하세요" name="">
			</div>
			<hr>
			<!-- 주소 -->
			<div class="nor_addr-top">
				<p>주소<i>*</i></p>
				<div>
				<input type="text" placeholder="우편번호" name="">
				<button>우편번호 찾기</button>
				</div>
			</div>
			<div class="nor_addr-bottom">
				<input type="text" placeholder="주소를 입력하세요" name="">
				<input type="text" placeholder="상세주소를 입력하세요" name="">
			</div>
			
			<button type="submit" class="joinbutton" >회원가입 하기</button>
			
		</form>
	</div>
	<%@ include file="../main/footer.jsp"%>
</body>

</html>