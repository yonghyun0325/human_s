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
        <img src="${pageContext.request.contextPath}/resources/img/han.jpg" alt="스토리 이미지">
    </div>
    <p>${story.storyContent}</p>
    <div>
        <small>작성자: ${story.author}</small>
        <small>작성일: <fmt:formatDate value="${story.createdDate}" pattern="yyyy-MM-dd" /></small>
    </div>
    
</div>
<%@ include file="../main/footer.jsp"%>
</body>
</html>
