<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글등록</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/notice_write.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
    <h3>글 작성</h3>
<%@ include file="../board/topmenu.jsp" %>
    <div class="board-container">
        <form name="frmBoardWrite" action="write.do" method="post">
            <!-- 작성자 -->
            <div class="form-group">
                <label for="gbWriter">이름</label>
                <input type="text" id="gbWriter" name="gbWriter" required>
            </div>

            <!-- 제목 및 선택 -->
            <div class="form-group">
                <label for="gbTitle">제목</label>
                <input type="text" id="gbTitle" name="gbTitle" required>
                
                <label for="category">카테고리</label>
                <select id="category" name="category">
                    <option value="general">일반</option>
                    <option value="notice">공지</option>
                    <option value="event">이벤트</option>
                </select>
            </div>

            <!-- 내용 -->
            <div class="form-group">
                <label for="gbContent">내용</label>
                <textarea id="gbContent" name="gbContent" rows="10" required></textarea>
            </div>

            <!-- HTML 태그 허용 -->
            <div class="form-group">
                <label for="allowHtml">HTML 태그 허용</label>
                <input type="checkbox" id="allowHtml" name="allowHtml">
            </div>

            <!-- 제출 버튼 -->
            <div class="form-group button-group">
                <input type="submit" value="등록하기">
                <input type="reset" value="다시입력">
                <input type="button" value="목록보기" onclick="location.href='../notice.no'">
            </div>
        </form>
    </div>
</div>

<%@ include file="../main/footer.jsp"%>
</body>
</html>
