<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>게시판 목록</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/notice.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>

<div class="layout">
    <div class="sidebar">
        <ul>
            <li><a href="notice.no">공지사항</a></li>
            <li><a href="faq">자주 묻는 질문</a></li>
            <li><a href="product">상품 문의</a></li>
            <li><a href="review">사용 후기</a></li>
            <li><a href="#">고객 문의</a><li>
        </ul>
    </div>

    <div class="board-container">
    
        <p>* 총 260개의 게시물이 있습니다.</p>
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

        <table class="board-table">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회</th>
            </tr>
            <%
                String[][] posts = {
                    {"10", "[이벤트] 2024 희망드림판매대 후기왕을 찾아라! 안내사항", "그린카트", "2024/11/05", "52"},
                    {"9", "[이벤트] 그린카트 천안시의 날 2차 경품이벤트 안내사항", "그린카트", "2024/10/30", "78"},
                    {"8", "[10월. 푸드체험단] 당첨자 안내드립니다.", "그린카트", "2024/10/18", "136"},
                    {"7", "[당첨자발표] 9월 추기작성 이벤트 당첨자 발표", "그린카트", "2024/10/16", "99"},
                    {"6", "충청남도와 함께하는 고향사랑기부제", "그린카트", "2024/10/04", "437"},
                    {"5", "[이벤트] 그린카트 천안시의 날 경품이벤트 안내사항 (42)", "그린카트", "2024/10/04", "1973"},
                    {"4", "[당첨자발표] 그린카트 추석운동회! 푸짐한 추석 경품릴레이! 당첨자발표", "그린카트", "2024/09/23", "215"},
                    {"3", "[9월. 푸드체험단] 당첨자 안내드립니다.", "그린카트", "2024/09/20", "158"},
                    {"2", "[당첨자발표] 8월 추기작성 이벤트 당첨자 발표", "그린카트", "2024/09/19", "87"},
                    {"1", "[당첨자발표] 그린카트 정기구독 퀴즈이벤트! ㅋㅋㅋ 나는누구일까요? 당첨자 발표", "그린카트", "2024/08/26", "324"}
                };

                for (String[] post : posts) {
            %>
            <tr>
                <td><%= post[0] %></td>
                <td><%= post[1] %></td>
                <td><%= post[2] %></td>
                <td><%= post[3] %></td>
                <td><%= post[4] %></td>
            </tr>
            <% } %>
        </table>

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
