<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTR-8">
<title>찜한상품</title>
<!-- 찜한상품 전용 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/favoriteproducts.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp" %>

    <h1>찜한 상품</h1>

    <div class="container">
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp" %>

        <!-- 메인 콘텐츠 -->
        <main class="main-content">
            <!-- 찜한 상품 안내 메시지 -->
            <div class="info-box">
                <p><span class="user-name">[이용현]</span>님이 쇼핑몰에서 찜하신 상품 내역입니다.</p>
            </div>

            <!-- 찜한 상품 테이블 -->
            <table class="wishlist-table">
                <thead>
                    <tr>
                        <th><input type="checkbox"></th>
                        <th>사진</th>
                        <th>상품명</th>
                        <th>옵션</th>
                        <th>재고</th>
                        <th>적립</th>
                        <th>가격</th>
                        <th>장바구니</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="8">보관하신 상품 내역이 없습니다.</td>
                    </tr>
                </tbody>
            </table>

            <!-- 선택 삭제 버튼 -->
            <div class="wishlist-actions">
                <button class="delete-selected-btn">선택 삭제</button>
            </div>

            <!-- 페이지네이션 -->
            <div class="pagination">
                <button class="page-btn" disabled>&lt;</button>
                <span class="current-page">1</span>
                <button class="page-btn">&gt;</button>
            </div>
        </main>
    </div>

    <%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>