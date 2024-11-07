<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문배송현황</title>
<!-- 주문배송현황 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ordershippingstatus.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp"%>

    <h1>주문내역</h1>
    <div class="order-container">
        <!-- 좌측 사이드바 -->
        <div class="sidebar">
            <h3>쇼핑정보</h3>
            <ul>
                <li><a href="#" class="no-underline active">주문배송현황</a></li>
                <li><a href="#" class="no-underline">나의 배송지 관리</a></li>
                <li><a href="#" class="no-underline">선물함</a></li>
                <li><a href="#" class="no-underline">정기구독 관리</a></li>
            </ul>
            <h3>혜택정보</h3>
            <ul>
                <li><a href="#" class="no-underline">할인쿠폰내역</a></li>
                <li><a href="#" class="no-underline">적립금내역</a></li>
            </ul>
            <h3>활동정보</h3>
            <ul>
                <li><a href="#" class="no-underline">찜한상품</a></li>
                <li><a href="#" class="no-underline">최근 본 상품</a></li>
                <li><a href="#" class="no-underline">장바구니</a></li>
                <li><a href="#" class="no-underline">1:1 문의내역</a></li>
                <li><a href="#" class="no-underline">내 게시글 보기</a></li>
            </ul>
            <h3>개인정보</h3>
            <ul>
                <li><a href="#" class="no-underline">회원정보수정</a></li>
                <li><a href="#" class="no-underline">회원탈퇴</a></li>
            </ul>
        </div>
        
        <!-- 메인 콘텐츠 영역 -->
        <div class="main-content">
            <div class="info-box">
                <p><strong>이용현</strong>님이 쇼핑몰에서 주문한 내역입니다.</p>
            </div>
            
            <div class="order-search-box">
                <dl>
                    <dd class="btnset">
                        <a href="#">오늘</a>
                        <a href="#">어제</a>
                        <a href="#">일주일</a>
                        <a href="#">1개월</a>
                        <a href="#">3개월</a>
                        <a href="#">1년</a>

                        <input type="text" id="search_date_start" name="search_date_start" size="11" value="" class="MS_input_txt MS_calendar" readonly="" onclick="Calendar(this, event);">
                        <input type="date" id="search_date_end" size="11" name="search_date_end" value="" readonly="">
                        <a href="#" class="srch">조회하기</a>
                    </dd>
                </dl>
            </div>
            
            <table class="order-table">
                <thead>
                    <tr>
                        <th>주문일자</th>
                        <th>상품명</th>
                        <th>결제금액</th>
                        <th>상태</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="4" class="no-data">주문내역이 없습니다.</td>
                    </tr>
                </tbody>
            </table>
            
            <div class="notice">
                <p>※ 상품명 또는 주문상세의 조회 버튼을 클릭하시면, 주문상세 내역을 확인하실 수 있습니다.</p>
                <p>※ 배송현황의 조회 버튼을 클릭하시면, 해당 주문의 배송 현황을 한눈에 확인하실 수 있습니다.</p>
            </div>
        </div>
    </div>

    <%@ include file ="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>