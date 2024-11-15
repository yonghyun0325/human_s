<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품문의</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/pinquiry_update.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<h3>상품문의</h3>
<%@ include file="../board/topmenu.jsp" %>
    <div id="board-container">
        <form action="${pageContext.request.contextPath}/board/pinquiry/updateProcess.do" method="post">
            <input type="hidden" name="pinquiryIdx" value="${pinquiry.id}"/>
            <div>
                <label for="pinquiryTitle">제목:</label>
                <input type="text" id="pinquiryTitle" name="pinquiryTitle" value="${pinquiry.pinquiryTitle}" required>
            </div>
            <div>
                <label for="pinquiryContent">내용:</label>
                <textarea id="pinquiryContent" name="pinquiryContent" required>${pinquiry.pinquiryContent}</textarea>
            </div>
            <div id="detail-btn-group">
                <button type="submit" class="btn btn-edit">수정하기</button>
                <button type="button" class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/board/pinquiry.no'">목록으로</button>
            </div>
        </form>
    </div>
</div>

<%@ include file="../main/footer.jsp"%>
</body>
</html>
