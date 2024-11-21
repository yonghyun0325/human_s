<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GreenCart</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/productList.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/productList.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<section>
		<img src="${pageContext.request.contextPath}/resources/img/인기순.png" alt="인기순배너">
		<img src="${pageContext.request.contextPath}/resources/img/최신순.png" alt="최신순배너">
		<div>
			<div class="newContent">
	<c:if test="${ user.grade == 2 or user.grade == 3 }">
				<a href="/hms/product/writeDetail.do">+ 글 등록</a>
	</c:if>
			</div>
			<div class="selectType">
				<a href="/hms/product/popNewList.no?select=pop"><i class="fa-solid fa-h"></i><i class="fa-solid fa-o"></i><i class="fa-solid fa-t"></i></a>
				<a href="/hms/product/popNewList.no?select=new"><i class="fa-solid fa-n"></i><i class="fa-solid fa-e"></i><i class="fa-solid fa-w"></i></a>
			</div>
		</div>
		<div class="productList">
	<c:forEach var="item" items="${ popNewList }" varStatus="i">
			<div class="productItem" data-idx="${ item.pdtIdx }">
				<div class="productRow">${ i.index+1 }위</div>
				<div class="popNum">
		<c:if test="${ not empty item.img }">
					<img src="${ item.img }" alt="${ item.pdtTitle }">
		</c:if>
		<c:if test="${ empty item.img }">
					<img src="${pageContext.request.contextPath}/resources/uploads/${item.pdtSave}" alt="${ item.pdtOrigin }">
		</c:if>
				</div>
				<div class="productContent">
					<div class="title">${ item.pdtTitle }</div>
					<div class="price">(100g당 ${ item.pdtGPrice }원)<span>${ item.pdtPrice }</span></div>
					<div class="company">${ item.pdtWriter }</div>
				</div>
			</div>
	</c:forEach>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>