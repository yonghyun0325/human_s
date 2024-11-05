<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<footer>
        <div class="footerTop">
            <button>회사소개</button>
            <button>이용약관</button>
            <button>개인정보처리방침</button>
            <button>전자금융거래약관</button>
        </div>
        <div class="footerContent">
        <div class="footerLeft">
            <div>상호: GreenCart</div>
            <div>대표자: 박지훈</div>
            <div>개인정보취급담당자: 임도현</div>
            <div>사업자등록번호: 123-45-67890</div>
            <div>주소: 충남 천안시 동남구 대흥로 215, 8층 6강의실</div>
            <div>이메일: human_s@human.com</div>
        </div>
        <div class="footerMid">
            <div>소비자 고객센터</div>
            <div>
                <i class="fa-solid fa-phone"></i>
                <span>041-561-1122</span>
            </div>
            <div>판매처 고객센터</div>
            <div>
                <i class="fa-solid fa-phone"></i>
                <span>041-561-1123</span>
            </div>
            <div class="footerContactHours">09:30 ~ 17:30 / 주말, 공휴일 휴무</div>
        </div>
        <div class="footerRight">
            <img src="${pageContext.request.contextPath}/resources/img/Logo.webp" alt="GreenCart" class="logo">
            <div class="api">aT 도매시장 통합홈페이지</div>
            <div class="api">KAMIS</div>
        </div>
    </div>
    </footer>
</body>
</html>