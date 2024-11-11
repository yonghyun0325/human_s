<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>고객 문의</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/customerinquiry.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
	<div class="sidebar">
        <ul>
            <li><a href="notice.no">공지사항</a></li>
            <li><a href="faq.no">자주 묻는 질문</a></li>
            <li><a href="product.no">상품 문의</a></li>
            <li><a href="review.no">사용 후기</a></li>
            <li><a href="customerinquiry.no">고객 문의</a></li>
        </ul>
    </div>
    <div class="board-container">
    
        <p>* 총 1111개의 게시물이 있습니다.</p>
        <div class="search-bar">
        <div class="check-box">
            <label><input type="radio" name="searchType" value="name"> 이름</label>
            <label><input type="radio" name="searchType" value="title" checked> 제목</label>
            <label><input type="radio" name="searchType" value="content"> 내용</label>
            <label><input type="radio" name="searchType" value="product"> 상품</label>
            </div>
            <div class="search-box">
            <input type="text" class="search" placeholder="검색어를 입력하세요">
            <button class="search-button">검색</button>
            </div>
        </div>
        
        <table class="board-table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>16171</td>
                    <td>언제 배송 </td>             
                    <td>작성자</td>
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