<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GreenCart</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/product.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/product.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<section>
		<div class="selectType">
			<a href="/hms/product/popNewList.no?select=pop"><i class="fa-regular fa-crown"></i>인기순</a>
			<a href="/hms/product/popNewList.no?select=new"><i class="fa-regular fa-sparkles"></i>최신순</a>
		</div>
		<img src="${pageContext.request.contextPath}/resources/img/인기순.png" alt="인기순배너">
		<img src="${pageContext.request.contextPath}/resources/img/최신순.png" alt="최신순배너">
		<div class="productList">
	<c:forEach var="i" begin="0" end="15">
			<div class="productItem">
				<div class="productRow">${ i+1 }위</div>
				<div class="popNum">
					<img src="${pageContext.request.contextPath}/resources/img/최신순.png" alt="상품타이틀">
				</div>
				<div class="productContent">
					<div class="title">title</div>
					<div class="price">price</div>
					<div class="company">(주)food</div>
				</div>
			</div>
	</c:forEach>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>