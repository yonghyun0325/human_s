<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/cinquiry_update.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<h3>고객문의</h3>
<%@ include file="../board/topmenu.jsp" %>
    <div id="board-container">
        <form action="${pageContext.request.contextPath}/board/cinquiry/updateProcess.do" method="post">
            <input type="hidden" name="cinquiryIdx" value="${cinquiry.id}"/>
            <div>
                <label for="cinquiryTitle">제목:</label>
                <input type="text" id="cinquiryTitle" name="cinquiryTitle" value="${cinquiry.cinquiryTitle}" required>
            </div>
            <div>
                <label for="cinquiryContent">내용:</label>
                <textarea id="cinquiryContent" name="cinquiryContent" required>${cinquiry.cinquiryContent}</textarea>
            </div>
            <div id="detail-btn-group">
                <button type="submit" class="btn btn-edit">수정하기</button>
                <button type="button" class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/board/cinquiry.no'">목록으로</button>
            </div>
        </form>
    </div>
</div>

<%@ include file="../main/footer.jsp"%>
</body>
</html>
