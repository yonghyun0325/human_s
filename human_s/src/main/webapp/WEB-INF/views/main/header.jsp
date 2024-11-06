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
                <a href=""><i class="fa-regular fa-heart"></i></a>
                <a href=""><i class="fa-regular fa-user"></i></a>
                <a href=""><i class="far fa-shopping-cart"></i></a>
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
	            <a href="">팜스토리</a>
	            <a href="">고객센터</a>
            </div>
        </div>
    </header>
</body>
</html>