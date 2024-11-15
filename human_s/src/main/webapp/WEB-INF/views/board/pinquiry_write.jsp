<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품문의</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/pinquiry_write.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<h3>상품문의</h3>
<%@ include file="../board/topmenu.jsp" %>
    <div class="board-container">
        <form name="frmBoardWrite" action="writeProcess.do" method="post">

            <!-- 작성자 (사용자가 직접 입력) -->
            <div class="form-group">
                <label for="author">이름</label>
                <input type="text" id="author" name="author" placeholder="이름을 입력하세요" required>
            </div>

            <!-- 제목 -->
            <div class="form-group">
                <label for="pinquiry_title">제목</label>
                <input type="text" id="pinquiryTitle" name="pinquiryTitle" placeholder="제목을 입력하세요" required>
            </div>

            <!-- 내용 -->
            <div class="form-group">
                <label for="pinquiry_content">내용</label>
                <textarea id="pinquiryContent" name="pinquiryContent" rows="10" placeholder="내용을 입력하세요" required></textarea>
            </div>

            <!-- 제출 버튼 -->
            <div class="form-group button-group">
                <input type="submit" value="등록하기" class="btn">
                <input type="reset" value="다시입력" class="btn">
                <input type="button" value="목록보기" onclick="location.href='${pageContext.request.contextPath}/board/pinquiry.no'" class="btn">
            </div>
        </form>
    </div>
</div>

<%@ include file="../main/footer.jsp"%>
</body>
</html>
