<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <title>상품문의</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/pinquiry_list.css">  
</head>
<body>

<%@ include file="../main/header.jsp"%>
<div class="layout">
<h3>상품문의</h3>
<%@ include file="../board/topmenu.jsp" %>
  <div class="board-container">

    <div class="bbs-sch">
    <div class="header">
        <p class="total-posts">총 게시물 수: ${paging.totalCount}</p>
        <div class="search-bar">
            <form action="${pageContext.request.contextPath}/board/pinquiry.no" method="get">
                <div class="check-box">
                    <label><input type="radio" name="searchType" value="name" ${param.searchType == null || param.searchType == 'name' ? 'checked' : ''}> 이름</label>
                    <label><input type="radio" name="searchType" value="title" ${param.searchType == 'title' ? 'checked' : ''}> 제목</label>
                    <label><input type="radio" name="searchType" value="content" ${param.searchType == 'content' ? 'checked' : ''}> 내용</label>
                </div>
                <div class="search-box">
                    <input type="text" name="search" placeholder="검색어를 입력하세요" value="${fn:escapeXml(param.search)}">
                    <button type="submit">검색</button>
                </div>
            </form>
        </div>
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
        
        <c:choose>
        <c:when test="${empty pinquiryList}">
            <tr>
                <td colspan="5">등록된 게시물이 없습니다</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="pinquiry" items="${pinquiryList}" varStatus="vs">
                <tr>
                    <td>${pinquiry.id}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/board/pinquiry/view.do?pinquiryId=${pinquiry.id}">
                            ${pinquiry.pinquiryTitle}
                        </a>
                    </td>
                    <td>${pinquiry.author}</td>
                    <td>
                        <fmt:formatDate value="${pinquiry.createdDate}" type="date" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>${pinquiry.views}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </table>
    
    <div class="bottom-box">
        <div class="pagination">
            <c:if test="${paging.currentPage > 1}">
                <a href="?page=${paging.currentPage - 1}&searchType=${param.searchType}&search=${param.search}">&laquo;</a>
            </c:if>
            <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="pageNum">
                <a href="?page=${pageNum}&searchType=${param.searchType}&search=${param.search}" class="${pageNum == paging.currentPage ? 'active' : ''}">
                    ${pageNum}
                </a>
            </c:forEach>
            <c:if test="${paging.currentPage < paging.totalPages}">
                <a href="?page=${paging.currentPage + 1}&searchType=${param.searchType}&search=${param.search}">&raquo;</a>
            </c:if>
        </div>

        <div class="write-box">
        <c:if test="${not empty user}"> 
            <a href="${pageContext.request.contextPath}/board/pinquiry/write.do">
                <button class="pinquiry_write_btn" type="button" id="write_btn">글등록</button> 
            </a>
        </c:if>

        <!-- 로그인되지 않은 경우 로그인 페이지로 이동 -->
        <c:if test="${empty user}">
            <button class="pinquiry_write_btn" type="button" id="write_btn" onclick="redirectToLogin()">로그인 후 글등록</button> 
        </c:if>
        </div>
    </div>
</div>
</div>

<%@ include file="../main/footer.jsp"%>

</body>
<script type="text/javascript">
        function redirectToLogin() {
            alert("로그인 페이지로 이동합니다.");
            setTimeout(function() {
                window.location.href = "${pageContext.request.contextPath}/user/login.no";  // 로그인 페이지로 리다이렉트
            }, 2000);  // 2초 후에 리다이렉트
        }
    </script>
</html>
