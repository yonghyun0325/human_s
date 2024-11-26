<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GreenCart</title>
<!-- 주문배송현황 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ordershippingstatus.css">

<!-- JavaScript 라이브러리 -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp"%>

    <h1>환불 / 취소 내역</h1>
    <div class="order-container">
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp" %>
        
        <!-- 메인 콘텐츠 영역 -->
        <div class="main-content">
            <div class="info-box">
                <p><span class="highlight">${user.userName}${ unuser.unName }</span>님이 쇼핑몰에서 환불 및 취소한 내역입니다.</p>
            </div>
            
            <h3>환불 / 취소 내역</h3>
            <table class="order-table">
                <thead>
                    <tr>
                        <th>취소일자</th>
                        <th>상품명</th>
                        <th>수량</th>
                        <th>결제금액</th>
                        <th>상태</th>
                    </tr>
                </thead>
                <tbody>
			        <c:if test="${not empty orderList}">
			            <c:forEach var="order" items="${orderList}">
			                <tr>
			                    <td><fmt:formatDate value="${order.orPayDate}" pattern="yyyy-MM-dd" /></td>
			                    <td>${order.orName}</td>
			                    <td>${order.orCount}</td>
			                    <td>${order.orPayAmount}원</td>
			                    <td>${order.orStatus}</td>
			                </tr>
			            </c:forEach>
			        </c:if>
			        <c:if test="${empty orderList}">
			            <tr>
			                <td colspan="5" class="no-data">환불 / 취소 내역이 없습니다.</td>
			            </tr>
			        </c:if>
			    </tbody>
            </table>
            
            <h3>반품 내역</h3>
            <table class="order-table">
                <thead>
                    <tr>
                        <th>반품일자</th>
                        <th>상품명</th>
                        <th>수량</th>
                        <th>결제금액</th>
                        <th>상태</th>
                    </tr>
                </thead>
                <tbody>
			        <c:if test="${not empty orderList}">
			            <c:forEach var="order" items="${orderList}">
			                <tr>
			                    <td><fmt:formatDate value="${order.orPayDate}" pattern="yyyy-MM-dd" /></td>
			                    <td>${order.orName}</td>
			                    <td>${order.orCount}</td>
			                    <td>${order.orPayAmount}원</td>
			                    <td>${order.orStatus}</td>
			                </tr>
			            </c:forEach>
			        </c:if>
			        <c:if test="${empty orderList}">
			            <tr>
			                <td colspan="5" class="no-data">반품 내역이 없습니다.</td>
			            </tr>
			        </c:if>
			    </tbody>
            </table>
            
        </div>
    </div>

    <%@ include file ="/WEB-INF/views/main/footer.jsp" %>

</body>
</html>