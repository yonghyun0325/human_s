<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" 
integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" 
crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>
<body>
    <header>
        <div class="headerTop">
            <a href="/hms/index.no">
            	<img src="${pageContext.request.contextPath}/resources/img/Logo.png" alt="GreenCart">
            </a>
            <div class="search">
                <input type="text" class="searchProduct" value="${ select }">
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
	<c:if test="${ empty unuser }">
                <a href="/hms/mypage/basket.do">
                	<i class="fa-solid fa-basket-shopping"></i><br>
                	<span>장바구니</span>
                </a>
    </c:if>
	<c:if test="${ not empty user && empty unuser }">
                <a href="/hms/user/logout.do">
                	<i class="fa-solid fa-right-from-bracket"></i><br>
                	<span>로그아웃</span>
                </a>
	</c:if>  
	<c:if test="${ not empty unuser }">
                <a href="/hms/mypage/order.do">
                	<i class="fa-solid fa-list"></i><br>
                	<span>주문내역</span>
                </a>
	</c:if>
	<c:if test="${ not empty unuser }">
                <a href="/hms/user/unUserlogout.no">
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