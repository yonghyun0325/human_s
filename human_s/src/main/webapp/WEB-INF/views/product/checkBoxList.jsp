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
		<div class="checkContainer">
			<div class="category">카테고리</div>
			<div class="areaSelect">지역선택</div>
			<div class="checkBox">
				<div class="category">
					<div class="checkList checkAll">
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							전체
						</label>
					</div>
					<hr>
					<div class="checkList checkItem">
	<c:forEach var="item" items="${ largeList }">
						<label>
							<input type="checkbox" value="${ item[0] }">
							<span class="custom-checkbox"></span>
							${ item[1] }
						</label>
	</c:forEach>
					</div>
				</div>
				<div class="areaSelect">
					<div class="checkList checkAll">
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							전체
						</label>
					</div>
					<hr>
					<div class="checkList checkItem">
	<c:forEach var="item" items="${ areaList }">
						<label>
							<input type="checkbox" value="${ item[0] }">
							<span class="custom-checkbox"></span>
							${ item[0] }
						</label>
	</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<div class="productList">
	<c:forEach var="i" begin="0" end="15">
			<div class="productItem">
				<div class="productRow">${ i+1 }위</div>
				<div class="popNum">
					<img src="${pageContext.request.contextPath}/resources/img/최신순.png" alt="상품타이틀">
				</div>
				<div class="productContent">
					<div class="title">title</div>
					<div class="price">(100g당 가격)<span>price원</span></div>
					<div class="company">(주)food</div>
				</div>
			</div>
	</c:forEach>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>