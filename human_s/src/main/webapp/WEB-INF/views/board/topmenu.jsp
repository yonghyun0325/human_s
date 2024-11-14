<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>top menu</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/topmenu.css">
</head>
<body>

<div class="sidebar">
    <ul>
        <li><a href="${pageContext.request.contextPath}/board/notice.no">공지사항</a></li>
        <li><a href="${pageContext.request.contextPath}/board/faq.no">자주 묻는 질문</a></li>
        <li><a href="${pageContext.request.contextPath}/board/productquestion.no">상품 문의</a></li>
        <li><a href="${pageContext.request.contextPath}/board/review.no">상품 후기</a></li>
        <li><a href="${pageContext.request.contextPath}/board/customerinquiry.no">고객 문의</a></li>
    </ul>
</div>
</body>
</html>
