<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/jointype.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
<script src="https://kit.fontawesome.com/d7e414b2e7.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro@4cac1a6/css/all.css" />
<title>회원가입 유형선택</title>
</head>
<body>
	<%@ include file="../main/header.jsp"%>
    <div class="jointype_container">
	        <div>
	            <p>구매자 가입하기</p>
	             <a href="/hms/user/join_normal.no">
	             <i class="fal fa-arrow-circle-right"></i>
	             </a>
	        </div>
	        <div>
	            <p>판매자 가입하기</p>
	             <a href="/hms/user/join_register.no">
	             <i class="fal fa-arrow-circle-right"></i>
	             </a>
	        </div>
    </div>
	<%@ include file="../main/footer.jsp"%>
</body>
</html>