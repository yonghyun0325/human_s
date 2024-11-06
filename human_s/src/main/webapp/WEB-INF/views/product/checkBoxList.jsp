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
		<div class="checkContainer">
			<div class="category">카테고리</div>
			<div class="areaSelect">지역선택</div>
			<div class="checkBox">
				<div class="category">
					<label>
						<input type="checkbox">
						<span class="custom-checkbox"></span>
						전체
					</label>
					<div class="checkList">
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							곡식
						</label>
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							과일
						</label>
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							채소
						</label>
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							견과류
						</label>
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							버섯
						</label>
					</div>
				</div>
				<div class="areaSelect">
					<div>
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							전체
						</label>
					</div>
					<div class="checkList">
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							서울
						</label>
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							인천
						</label>
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							대전
						</label>
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							대구
						</label>
						<label>
							<input type="checkbox">
							<span class="custom-checkbox"></span>
							울산
						</label>
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