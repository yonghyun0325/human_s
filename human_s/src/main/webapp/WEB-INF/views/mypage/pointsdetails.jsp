<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>적립금 내역</title>
<!-- 적립금내역 전용 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pointsdetails.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/main/header.jsp"%>
	
	<h1>적립금 내역</h1>

    <div class="container">
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp" %>

        <!-- 메인 콘텐츠 -->
        <main class="main-content">
            <!-- 적립금 안내 메시지 -->
            <div class="info-box">
                <p><span class="user-name">[이용현]</span>님이 쇼핑몰에서 사용 가능한 적립금 내역입니다.</p>
            </div>

            <!-- 총 보유 적립금 박스 -->
            <div class="total-points-box">
				<div>
					<p>총 보유 적립금액</p>
				</div>
                <div>
					<p class="points-amount">0원</p>
				</div>
            </div>

            <!-- 적립금 내역 테이블 -->
            <h2>적립금 내역</h2>
            <table class="points-table">
                <thead>
                    <tr>
                        <th>날짜</th>
                        <th>적립내역</th>
                        <th>적립금</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="3">적립금 내역이 없습니다.</td>
                    </tr>
                </tbody>
            </table>

            <!-- 하단 주의사항 박스 -->
            <div class="notice-box">
				<ul class="notice-box-opt">
					<li style="margin-bottom: 5px;">적립된 금액이 0원 이상 누적되었을 때, 사용하실 수 있습니다.</li>
					<li>현재 사용 가능한 적립금은 상단의 총 보유 적립금액에 표시됩니다.</li>
				</ul>
            </div>
        </main>
    </div>
	
	<%@ include file="/WEB-INF/views/main/footer.jsp"%>
</body>
</html>