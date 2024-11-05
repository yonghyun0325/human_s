<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
<script src="../resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
	<section>
        <div class="priceGraph">
            <h4>농산물 가격동향</h4>
        </div>
        <div class="bestProduct">
            <h4>Best 상품</h4>
            <div class="bestList">
                <div style="background-color: #AFD485;">
                    <div>top 1</div>
                    <div>산지 직송 햇사과</div>
                </div>
                <div style="background-color: #AFD485;">
                    <div>top 1</div>
                    <div>산지 직송 햇사과</div>
                </div>
            </div>
        </div>
        <div class="newList">
            <h4>따끈따끈한 신상</h4>
            <div style="background-color: #AFD485;">
                <div>산지 직송 햇사과</div>
            </div>
        </div>
        <div class="areaList">
            <h4>지역 특산물</h4>
        </div>
        <div class="farmStoryList">
            <h4>농부들 이야기</h4>
        </div>
    </section>
</body>
</html>