<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/d7e414b2e7.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro@4cac1a6/css/all.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>
<body>
    <header>
        <div class="headerTop">
            <a href="/hms/index.no">
            	<img src="${pageContext.request.contextPath}/resources/img/Logo.webp" alt="GreenCart">
            </a>
            <div class="search">
                <input type="text" class="searchProduct">
                <i class="fa-solid fa-magnifying-glass"></i>
            </div>
            <div class="userIcon">
                <a href="/hms/mypage/favorite.do">
                	<i class="fa-regular fa-heart"></i><br>
                	<span>찜목록</span>
                </a>
	<c:if test="${ empty user }">
                <a href="/hms/user/login.no">
                	<i class="fa-regular fa-user"></i><br>
                	<span>로그인</span>
                </a>
	</c:if>
	<c:if test="${ not empty user }">
                <a href="/hms/mypage/mypage.do">
                	<i class="fa-regular fa-user"></i><br>
                	<span>마이페이지</span>
                </a>
	</c:if>
                <a href="/hms/mypage/basket.do">
                	<i class="far fa-shopping-cart"></i><br>
                	<span>장바구니</span>
                </a>
	<c:if test="${ not empty user }">
                <a href="/hms/user/logout.do">
                	<i class="fa-solid fa-right-from-bracket"></i><br>
                	<span>로그아웃</span>
                </a>
	</c:if>  
            </div>
        </div>
        <div class="navigation">
            <i class="fa-solid fa-bars"></i>
            <div class="hiddenBack">
	            <div class="hiddenNav">
	            	<a href="/hms/product/checkBoxList.no?select=category">전체상품</a>
	            	<a href="/hms/product/popNewList.no?select=pop">인기순</a>
	            	<a href="/hms/product/popNewList.no?select=new">신상품</a>
	            	<a href="/hms/product/checkBoxList.no?select=areaSelect">지역특산물</a>
	            	<a href="">곡류</a>
	            	<a href="">과일</a>
	            	<a href="">채소</a>
	            	<a href="">견과류</a>
	            </div>
            </div>
            <div class="otherPage">
	            <a href="/hms/product/popNewList.no?select=pop">인기순</a>
	            <a href="/hms/product/popNewList.no?select=new">신상품</a>
	            <a href="/hms/product/checkBoxList.no?select=areaSelect">지역 특산물</a>
	            <a href="/hms/product/checkBoxList.no?select=category">전체상품</a>
	            <a href="/hms/story/farmstory.no">팜스토리</a>
	            <a href="/hms/board/notice.no">고객센터</a>
	<c:if test="${ user.grade eq 3 }">
	            <a href="/hms/manager/manager.do">관리자</a>
	</c:if>
            </div>
        </div>
    </header>
</body>
</html>