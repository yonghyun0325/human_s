<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/basket.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp" %>

    <h1>장바구니</h1>

    <div class="container">

        <!-- 탭 메뉴 -->
        <div class="tab-menu">
            <button class="tab active">일반구매 (0)</button>
            <button class="tab">정기구독 (0)</button>
        </div>

        <!-- 장바구니 테이블 -->
        <table class="basket-table">
            <thead>
                <tr>
                    <th><input type="checkbox"></th>
                    <th>사진</th>
                    <th>상품명</th>
                    <th>옵션</th>
                    <th>수량</th>
                    <th>적립</th>
                    <th>가격</th>
                    <th>삭제</th>
                </tr>
            </thead>
            <tbody>
	<c:choose>
		<c:when test="${ not empty basketList }">
			<c:forEach var="item" items="${ basketList }">
				<tr>
					<td><input type="checkbox"></td>
				<c:if test="${ not empty item.productEntity.img }">
					<td><img src="${ item.productEntity.img }" alt="${ item.productEntity.pdtTitle }"></td>				
				</c:if>
				<c:if test="${ empty item.productEntity.img }">
					<td><img src="${pageContext.request.contextPath}/resources/uploads/${item.productEntity.pdtSave}" alt="${ item.productEntity.pdtOrigin }"></td>
				</c:if>
					<td>${ item.productEntity.pdtTitle }</td>
					<td>${ item.productEntity.pdtKg }Kg</td>
					<td>${ item.qty }</td>
					<td>${ item.productEntity.pdtPrice / 100 }원</td>
					<td>${ item.productEntity.pdtPrice }원</td>
					<td><button>삭제</button></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
                <tr>
                    <td colspan="8">장바구니가 비어 있습니다.</td>
                </tr>
		</c:otherwise>
	</c:choose>
            </tbody>
        </table>

        <!-- 장바구니 가격 -->
         <div class="price-info-container">
            <div class="price-info">
                <div class="price-label">총 주문 금액:</div>
                <div class="total-price">0원</div>
            </div>
            <p class="info-message">※ 최소 주문 금액은 10,000원입니다. 정기구독 상품은 개별 배송됩니다.</p>
         </div>

         <!-- 안내 메세지 -->
         <h3>안내 메세지</h3>
         <div class="price-info-notice">
            <ul style="margin-left: 10px;">
                <li class="info-use">장바구니의 상품 할인가는 "회원 전용 할인가" 입니다.</li><br>
                <li class="info-use">주문서 작성 시 쿠폰 적용 후 실제 가격을 확인할 수 있습니다.</li><br>
                <li class="info-use">상품 한정 최대 구매 가능 수량은 99개입니다.</li>
            </ul>
         </div>

        <!-- 기능 버튼 -->
        <div class="basket-buttons">
            <button class="btn delete-selected">선택삭제</button>
            <button class="btn select-all">전체선택</button>
            <button class="btn continue-shopping">계속쇼핑하기</button>
            <button class="btn add-to-basket">장바구니 담기</button>
            <button class="btn order-selected">선택상품주문하기</button>
        </div>

        <h3>찜한 상품</h3>
        <!-- 찜한 상품 테이블 -->
        <div id="wishlist" class="tab-content">
            <table class="basket-table">
                <thead>
                    <tr>
                        <th><input type="checkbox"></th>
                        <th>사진</th>
                        <th>상품명</th>
                        <th>옵션</th>
                        <th>가격</th>
                        <th>장바구니</th>
                    </tr>
                </thead>
                <tbody>
	<c:choose>
		<c:when test="${ not empty favoriteList }">
			<c:forEach var="item" items="${ favoriteList }">
				<tr>
					<td><input type="checkbox"></td>
				<c:if test="${ not empty item.productEntity.img }">
					<td><img src="${ item.productEntity.img }" alt="${ item.productEntity.pdtTitle }"></td>				
				</c:if>
				<c:if test="${ empty item.productEntity.img }">
					<td><img src="${pageContext.request.contextPath}/resources/uploads/${item.productEntity.pdtSave}" alt="${ item.productEntity.pdtOrigin }"></td>
				</c:if>
					<td>${ item.productEntity.pdtTitle }</td>
					<td>${ item.productEntity.pdtKg }Kg</td>
					<td>${ item.productEntity.pdtPrice }원</td>
					<td><button>담기</button></td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
                <tr>
                    <td colspan="6">찜한 상품이 없습니다.</td>
                </tr>
		</c:otherwise>
	</c:choose>
                </tbody>
            </table>
        </div>
    </div>

    <%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>
