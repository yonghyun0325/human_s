<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 작성 내역 보기</title>
<!-- 내 댓글 보기 전용 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/myarticle.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/main/header.jsp" %>

    <h1>내 댓글 보기</h1>

    <div class="container">
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp" %>

        <!-- 메인 콘텐츠 -->
        <main>
            <div class="info-message">
                <span class="highlight">[이용자님]</span>이 쇼핑몰에 작성한 댓글 내역입니다.
            </div>

            <!-- 게시글 내역 테이블 -->
            <table class="article-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>게시판</th>
                        <th>제목</th>
                        <th>날짜</th>
                        <th>조회</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="5">댓글 내역이 없습니다.</td>
                    </tr>
                </tbody>
            </table>

            <!-- 페이지네이션 -->
            <div class="pagination-container">
                <div class="pagination">
                    <span>&lt;</span>
                    <span>1</span>
                    <span>&gt;</span>
                </div>
            </div>
        </main>
    </div>

    <%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>