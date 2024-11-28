<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품후기</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/review_view.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>

<div class="layout">
    <h2>상품후기</h2>
    <%@ include file="../board/topmenu.jsp" %>
    <div class="board-container">
        <div class="detail-header">
            <h2 class="detail-title">${review.reviewTitle}</h2>
            <div class="detail-meta">
                <span class="meta-item">작성자: ${review.author}</span>
                <span class="meta-item">조회수: ${review.views}</span> 
                <span class="meta-item">작성일: <fmt:formatDate value="${review.createdDate}" type="date" pattern="yyyy-MM-dd" /></span>
                
                <!-- 평점을 별 모양으로 표시 -->
                <span class="meta-item">평 점:
                    <c:forEach begin="1" end="${review.rating}" var="star">
                        <span class="star">&#9733;</span> <!-- ★ 아이콘으로 별을 나타냄 -->
                    </c:forEach>
                </span>
            </div>
        </div>
        
        <div class="detail-content">
            <p>${review.reviewContent}</p>
        </div>

        <div id="detail-btn-group">
            <!-- 조건: 로그인 상태 + 자신이 작성한 게시물일 경우 -->
            <c:if test="${(not empty user) and (user.userIdx eq review.userEntity.userIdx)}"> 
                <button class="btn btn-edit" onclick="location.href='${pageContext.request.contextPath}/board/review/update.do?reviewIdx=${review.id}'">수정하기</button>
                <button class="btn btn-delete" onclick="deletePost()">삭제하기</button>
             </c:if> 
            <button class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/board/review.no'">목록으로</button>
        </div>
    </div>
</div>

<script>
    function deletePost() {
        const ans = confirm("정말 삭제하겠습니까?");
        
        if (ans) {
            location.href = "${pageContext.request.contextPath}/board/review/deleteProcess.do?reviewIdx=${review.id}";
        }
    }
</script>

<%@ include file="../main/footer.jsp"%>

</body>
</html>
