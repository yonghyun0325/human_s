<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품문의 게시판</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/productquestion.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<%@ include file="../board/topmenu.jsp" %>
<div class="board-container">
    <div class="board-header">상품문의 게시판</div>

    <!-- 검색 및 필터 섹션 -->
 <div class="bbs-sch">
    	<div class="header">
        	<p class="total-posts">* 총 260개의 게시물이 있습니다.</p>
        	<div class="search-bar">
            	<div class="check-box">
	                <label><input type="radio" name="searchType" value="name"> 이름</label>
	                <label><input type="radio" name="searchType" value="title" checked> 제목</label>
	                <label><input type="radio" name="searchType" value="content"> 내용</label>
            	</div>
            <div class="search-box">
                <input type="text" name="search" placeholder="검색어를 입력하세요">
                <button type="search-button">검색</button>
            </div>
        </div>
      </div>
    </div>
    <!-- 문의 목록 테이블 -->
    <table class="board-table">
        <thead>
        <tr>
            <th>번호</th>
            <th>상품</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회</th>
        </tr>
        </thead>
        <tbody>
        <!-- 반복문을 통해 게시글 목록 출력 -->
        <% 
            // 문의 목록 데이터를 이미지 파일명과 함께 설정
            String[][] inquiries = {
                {"5071", "한우 곰탕용 사골", "[문의] 한우는 뭐야?", "microhp", "2024/11/06", "2", "han.jpg"},
                {"5070", "전통 김치", "[문의] 김장철 준비중 문의", "이태성", "2024/11/05", "4", "kimchi.jpg"},
                {"5069", "고등어", "[문의] 신선도는?", "loveme41", "2024/11/05", "11", "go.jpg"}
            };

            for (String[] inquiry : inquiries) {
                String imgSrc = inquiry[6]; // 이미지 파일명
        %>
        <tr>
            <td><%= inquiry[0] %></td>
            <td class="product-image">
                <!-- 각 상품에 맞는 이미지를 동적으로 설정 -->
                <img src="${pageContext.request.contextPath}/resources/img/<%= imgSrc %>" alt="상품 이미지">
            </td>
            <td><%= inquiry[2] %></td>
            <td><%= inquiry[3] %></td>
            <td><%= inquiry[4] %></td>
            <td><%= inquiry[5] %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <!-- 페이지네이션 -->
    <div class="pagination">
        <a href="#">&laquo;</a>
        <a href="#" class="active">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">&raquo;</a>
    </div>
</div>
</div>
<%@ include file="../main/footer.jsp"%>
</body>
</html>
