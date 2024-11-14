<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지사항</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/notice_view.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>


<div class="layout">
    <h2>공지사항</h2>
    <%@ include file="../board/topmenu.jsp" %>
    <div class="board-container">
    <div class="detail-header">
    <h2 class="detail-title">${notice.noticeTitle}</h2>
        <div class="detail-meta">
        <span class="meta-item">작성자: ${notice.author}</span>
        <span class="meta-item">조회수: ${notice.views}</span> 
        <span class="meta-item">작성일: <fmt:formatDate value="${notice.createdDate}" type="date" pattern="yyyy-MM-dd" /></span>
       </div>
       </div>
        <div class="detail-content">
        <p>${notice.noticeContent}</p>
        </div>
       
        <div id="detail-btn-group">
            <!-- 조건: 로그인 상태 + 자신이 작성한 게시물일 경우 -->
            <%-- <c:if test="${(not empty user) and (user.user_idx eq notice.user_idx)}"> --%>
                <button class="btn btn-edit" onclick="location.href='${pageContext.request.contextPath}/board/update.do?noticeIdx=${notice.id}'">수정하기</button>
                <button class="btn btn-delete" onclick="deletePost()">삭제하기</button>
            <%-- </c:if> --%>
            <button class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/board/notice.no'">목록으로</button>
        </div>
    </div>
</div>


<script>
    function deletePost() {
        const ans = confirm("정말 삭제하겠습니까?");
        
        if (ans) {
            location.href = "${pageContext.request.contextPath}/board/deleteProcess.do?noticeIdx=${notice.id}";
        }
    }
</script>

<%@ include file="../main/footer.jsp"%>

</body>
</html>
