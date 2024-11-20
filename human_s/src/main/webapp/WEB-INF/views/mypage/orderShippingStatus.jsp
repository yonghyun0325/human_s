<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문배송현황</title>
<!-- 주문배송현황 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ordershippingstatus.css">

<!-- JavaScript 라이브러리 -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp"%>

    <h1>주문내역</h1>
    <div class="order-container">
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp" %>
        
        <!-- 메인 콘텐츠 영역 -->
        <div class="main-content">
            <div class="info-box">
                <p><span class="highlight">${user.userName}</span>님이 쇼핑몰에서 주문한 내역입니다.</p>
            </div>
            
            <div class="filter-container">
			    <!-- 필터 버튼 -->
			    <button class="filter-btn" data-period="today">오늘</button>
			    <button class="filter-btn" data-period="yesterday">어제</button>
			    <button class="filter-btn" data-period="week">일주일</button>
			    <button class="filter-btn" data-period="month">1개월</button>
			    <button class="filter-btn" data-period="threeMonths">3개월</button>
			    <button class="filter-btn" data-period="year">1년</button>
			    
			    <!-- 날짜 입력 필드 -->
			    <div class="input-with-icon">
			        <input type="date" id="baseDate" class="date-input" placeholder="기준 날짜 선택">
			    </div>
			    <div class="input-with-icon">
			        <input type="date" id="endDate" class="date-input" placeholder="종료 날짜 선택">
			    </div>
			    
			    <!-- 조회 버튼 -->
			    <button class="filter-btn search-btn selects">조회하기</button>
			</div>
            
            <table class="order-table">
                <thead>
                    <tr>
                        <th>주문일자</th>
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
			                <td colspan="5" class="no-data">주문내역이 없습니다.</td>
			            </tr>
			        </c:if>
			    </tbody>
            </table>
            
            <div class="notice">
                <p>※ 상품명 또는 주문상세의 조회 버튼을 클릭하시면, 주문상세 내역을 확인하실 수 있습니다.</p>
                <p>※ 배송현황의 조회 버튼을 클릭하시면, 해당 주문의 배송 현황을 한눈에 확인하실 수 있습니다.</p>
            </div>
        </div>
    </div>

    <%@ include file ="/WEB-INF/views/main/footer.jsp" %>
    
    <!-- 필터 스크립트 -->
    <script>
        $(document).ready(function() {
            // 필터 버튼 클릭 시 이벤트 처리
            $('.filter-btn').on('click', function() {
                // 모든 버튼에서 활성화 클래스 제거
                $('.filter-btn').removeClass('active');
                
                // 클릭한 버튼에 활성화 클래스 추가
                $(this).addClass('active');
                
                // 버튼 데이터 속성으로 기간 설정
                const period = $(this).data('period');
                
                // 기간에 따른 기본 날짜 설정
                const today = new Date();
                let baseDate = new Date(today);
                
                if (period === 'yesterday') {
                    baseDate.setDate(today.getDate() - 1);
                } else if (period === 'week') {
                    baseDate.setDate(today.getDate() - 7);
                } else if (period === 'month') {
                    baseDate.setMonth(today.getMonth() - 1);
                } else if (period === 'threeMonths') {
                    baseDate.setMonth(today.getMonth() - 3);
                } else if (period === 'year') {
                    baseDate.setFullYear(today.getFullYear() - 1);
                }
                
             	// '오늘' 버튼 클릭 시 오늘 날짜 유지
                if (period === 'today') {
                    baseDate = today; // 오늘 날짜 그대로
                }
                
                // 날짜 입력 필드에 값 설정
                $('#baseDate').val(baseDate.toISOString().split('T')[0]);
                $('#endDate').val(today.toISOString().split('T')[0]);
            });
        });
    </script>
</body>
</html>