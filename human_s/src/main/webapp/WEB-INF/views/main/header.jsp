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
    <header>
        <div class="headerTop">
            <h2>GreenCart</h2>
            <div class="search">
                <input type="text" class="searchProduct">
                <i class="fa-solid fa-magnifying-glass"></i>
            </div>
            <div class="userIcon">
                <i class="fa-regular fa-heart"></i>
                <i class="fa-regular fa-user"></i>
                <i class="fa-solid fa-cart-shopping"></i>
            </div>
        </div>
        <div class="navigation">
            <button><i class="fa-solid fa-bars"></i></button>
            <div class="otherPage">
	            <button>인기순</button>
	            <button>신상품</button>
	            <button>지역 특산물</button>
	            <button>전체상품</button>
	            <button>팜스토리</button>
	            <button>고객센터</button>
            </div>
        </div>
    </header>
</body>
</html>