<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>스토리 상세 보기</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/story_view.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
	<div class="top"> 
    <h1>${story.storyTitle}</h1>
    </div>
    <div>
         <img src="${pageContext.request.contextPath}/${story.contentImage}" alt="스토리 이미지">
    </div>
   	
    <p>${story.storyContent}</p>
        <!-- 태그된 상품 표시 -->
    <div class="tagged-products">
        <h3>태그된 상품</h3>

            <div class="tagged-items">
    <c:choose>
    	<c:when test="${ not empty story.product.img }">
	        <img src="${story.product.img}" alt="태그된 상품 이미지">
    	</c:when>
    	<c:otherwise>
    		<img src="${pageContext.request.contextPath}/resources/uploads/${story.product.pdtSave}" alt="${ story.product.pdtOrigin }">
    	</c:otherwise>
    </c:choose>
    
        <div class="tagged-item-info">
            <div class="tagged-item-title">${story.product.pdtTitle}</div>
            <div class="tagged-item-price">${story.product.pdtPrice}원</div>
        </div>
    </div>
    
    <div>
        <small>작성자: ${story.author}</small>
        <small>작성일: <fmt:formatDate value="${story.createdDate}" pattern="yyyy-MM-dd" /></small>
    </div>
    
    
            <div id="detail-btn-group">
            <!-- 조건: 로그인 상태 + 자신이 작성한 게시물일 경우 -->
		  <c:if test="${not empty user and user.userIdx == story.userEntity.userIdx}">
		    <button class="btn btn-edit" 
		            onclick="location.href='${pageContext.request.contextPath}/story/update.do?storyIdx=${story.id}'">수정하기</button>
		    <button class="btn btn-delete" onclick="deletePost()">삭제하기</button>
		</c:if>

            <button class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/story/farmstory.no'">목록으로</button>
        </div>
</div>
</div>
<%@ include file="../main/footer.jsp"%>
</body>
<script>
    function deletePost() {
        const ans = confirm("정말 삭제하겠습니까?");
        
        if (ans) {
            location.href = "${pageContext.request.contextPath}/story/delete.do?storyIdx=${story.id}";
        }
    }
</script>

</html>
