<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>고객문의</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/cinquiry_view.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<h3>고객문의</h3>
<%@ include file="../board/topmenu.jsp" %>
    <div class="board-container">
        <div class="detail-header">
            <h2 class="detail-title">${cinquiry.cinquiryTitle}</h2>
            <div class="detail-meta">
                <span class="meta-item">작성자: ${cinquiry.author}</span>
                <span class="meta-item">조회수: ${cinquiry.views}</span> 
                <span class="meta-item">작성일: <fmt:formatDate value="${cinquiry.createdDate}" type="date" pattern="yyyy-MM-dd" /></span>
            </div>
        </div>
        
        <div class="detail-content">
            <p>${cinquiry.cinquiryContent}</p>
        </div>
       
        <div id="detail-btn-group">
            <!-- 조건: 로그인 상태 + 자신이 작성한 게시물일 경우 -->
            <%-- <c:if test="${(not empty user) and (user.user_idx eq cinquiry.user_idx)}"> --%>
                <button class="btn btn-edit" onclick="location.href='${pageContext.request.contextPath}/board/cinquiry/update.do?cinquiryIdx=${cinquiry.id}'">수정하기</button>
                <button class="btn btn-delete" onclick="deletePost()">삭제하기</button>
            <%-- </c:if> --%>
            <button class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/board/cinquiry.no'">목록으로</button>
        </div>
    </div>
</div>

<script>
    function deletePost() {
        const ans = confirm("정말 삭제하겠습니까?");
        
        if (ans) {
            location.href = "${pageContext.request.contextPath}/board/cinquiry/deleteProcess.do?cinquiryIdx=${cinquiry.id}";
        }
    }
</script>

<%@ include file="../main/footer.jsp"%>

</body>
</html>  
