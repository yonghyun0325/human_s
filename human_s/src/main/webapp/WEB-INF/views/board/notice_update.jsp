<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/notice_update.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<h3>공지사항</h3>
<%@ include file="../board/topmenu.jsp" %>
    <div id="board-container">
        <form action="${pageContext.request.contextPath}/board/notice/updateProcess.do" method="post">
            <input type="hidden" name="noticeIdx" value="${notice.id}"/>
            <div>
                <label for="noticeTitle">제목:</label>
                <input type="text" id="noticeTitle" name="noticeTitle" value="${notice.noticeTitle}" required>
            </div>
            <div>
                <label for="noticeContent">내용:</label>
                <textarea id="noticeContent" name="noticeContent" required>${notice.noticeContent}</textarea>
            </div>
            <div id="detail-btn-group">
                <button type="submit" class="btn btn-edit">수정하기</button>
                <button type="button" class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/board/notice.no'">목록으로</button>
            </div>
        </form>
    </div>
</div>

<%@ include file="../main/footer.jsp"%>
</body>
</html>
