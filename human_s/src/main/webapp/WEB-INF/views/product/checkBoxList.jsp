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
							<input type="checkbox" value="all" checked>
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
							<input type="checkbox" value="all" checked>
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
		<h3></h3>
		<div>
			<div class="newContent">
	<c:if test="${ user.grade == 2 or user.grade == 3 }">
				<a href="/hms/product/writeDetail.do">+ 글 등록</a>
	</c:if>
			</div>
			<div class="selectType"></div>
		</div>
		<div class="productList">
	<c:if test="${ not empty productList }">
		<c:forEach var="item" items="${ productList }" varStatus="i">
			<div class="productItem" data-idx="${ item.pdtIdx }">
				<div class="productRow"></div>
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
					<div class="price">(100g당 ${ item.pdtGPrice }원)<span>${ item.pdtPrice } 원</span></div>
					<div class="company">${ item.userEntity.userNick }</div>
				</div>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${ empty productList }">
			<div class="noneList">검색 결과가 없습니다.</div>
	</c:if>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp" />
	
	<script>
		const contextPath = '${pageContext.request.contextPath}';
	</script>
</body>
</html>