<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의내역</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/inquiry.css">
</head>
<body>
	 <%@ include file="/WEB-INF/views/main/header.jsp" %>

    <h1>1:1 문의내역</h1>

    <!-- 메인 컨테이너 -->
    <div class="container">
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp" %>

        <!-- 메인 콘텐츠 -->
        <main class="main-content">
            <div class="info-message">
                <span class="highlight">[이용자님]</span>이 쇼핑몰에서 사용 가능한 1:1 고객상담 내역입니다.
            </div>

            <!-- 문의 내역 테이블 -->
            <table class="inquiry-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>날짜</th>
                        <th>상태</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="4">문의 내역이 없습니다.</td>
                    </tr>
                </tbody>
            </table>

            <!-- 페이지네이션 및 버튼 -->
            <div class="pagination-container">
                <div class="pagination">
                    <span>&lt;</span>
                    <span>1</span>
                    <span>&gt;</span>
                </div>
                <a href="write.do"><button class="register-inquiry-btn">1:1 문의하기</button></a>
            </div>
        </main>
    </div>

    <%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>