<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품문의</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/pinquiry_view.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<h3>상품문의</h3>
<%@ include file="../board/topmenu.jsp" %>
    <div class="board-container">
        <div class="detail-header">
            <h2 class="detail-title">${pinquiry.pinquiryTitle}</h2>
            <div class="detail-meta">
                <span class="meta-item">작성자: ${pinquiry.author}</span>
                <span class="meta-item">조회수: ${pinquiry.views}</span> 
                <span class="meta-item">작성일: <fmt:formatDate value="${pinquiry.createdDate}" type="date" pattern="yyyy-MM-dd" /></span>
            </div>
        </div>
        
        <div class="detail-content">
            <p>${pinquiry.pinquiryContent}</p>
        </div>
       
        <div id="detail-btn-group">
            <!-- 조건: 로그인 상태 + 자신이 작성한 게시물일 경우 -->
            <c:if test="${(not empty user) and (user.userIdx eq pinquiry.userEntity.userIdx)}"> 
                <button class="btn btn-edit" onclick="location.href='${pageContext.request.contextPath}/board/pinquiry/update.do?pinquiryIdx=${pinquiry.id}'">수정하기</button>
                <button class="btn btn-delete" onclick="deletePost()">삭제하기</button>
             </c:if> 
            <button class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/board/pinquiry.no'">목록으로</button>
        </div>
    </div>
</div>

<script>
    function deletePost() {
        const ans = confirm("정말 삭제하겠습니까?");
        
        if (ans) {
            location.href = "${pageContext.request.contextPath}/board/pinquiry/deleteProcess.do?pinquiryIdx=${pinquiry.id}";
        }
    }
</script>

<%@ include file="../main/footer.jsp"%>

</body>
</html>  
