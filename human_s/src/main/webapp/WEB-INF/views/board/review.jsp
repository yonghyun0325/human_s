<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>상품후기 게시판</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/review.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<%@ include file="../board/topmenu.jsp" %>
    <div class="board-container">
    
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
        
        <table class="board-table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>평점</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>16171</td>
                    <td>알이 너무 작고 검증이 잘돼요...</td>
                    <td>⭐⭐</td>
                    <td>nh*******</td>
                    <td>2024/11/06</td>
                    <td>8</td>
                </tr>
               
            </tbody>
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