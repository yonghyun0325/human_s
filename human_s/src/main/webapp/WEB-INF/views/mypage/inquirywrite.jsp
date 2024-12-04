<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의하기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/inquirywrite.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp" %>

    <h1>1:1 문의하기</h1>

    <div class="container">
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp"%>

        <!-- 메인 콘텐츠 -->
            <form class="main-content" name="frmBoardWrite" action="/hms/board/cinquiry/writeProcess.do" method="post">
                <div class="info-message">[<span class="highlight">${user.userName}</span>]님의 문의에 답변이 등록되면 알려드립니다.</div>
                <div class="form-section">
                    <table class="form-table">
                        <tr>
                            <th>이름</th>
                            <td><input type="text" name="author" value="${ user.userName }" readonly></td>
                        </tr>
                        <tr>
                            <th>이메일</th>
                            <td><input type="email" value="${ user.userEmail }" disabled></td>
                        </tr>
                        <tr>
                            <th>핸드폰</th>
                            <td><input type="tel" placeholder="핸드폰 번호" value="${ user.userPhone }"></td>
                        </tr>
                        <tr>
                            <th>제목</th>
                            <td><input type="text" name="cinquiryTitle" placeholder="제목" required></td>
                        </tr>
                        <tr>
                            <th>내용</th>
                            <td><textarea name="cinquiryContent" placeholder="문의 내용을 입력하세요" required></textarea></td>
                        </tr>
                    </table>
                    <div class="button-group">
                        <button type="submit" class="submit-btn">문의하기</button>
                        <button class="cancel-btn" onclick="history.back()">취소하기</button>
                    </div>
                </div>
            </form>
        </div>

    <%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>