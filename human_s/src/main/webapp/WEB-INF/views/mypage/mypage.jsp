<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<!-- 공통 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css">
    
<!-- 마이페이지 전용 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mypage.css">

<!-- JavaScript 라이브러리 -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp"%>
    
    <h1>마이페이지</h1>
    <!-- 마이페이지 컨테이너 -->
    <div class="mypage-container">
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp" %>

        <!-- 메인 콘텐츠 -->
        <div class="main-content">
            <!-- 사용자 정보 카드 -->
            <div class="user-card">
                <div class="user-info-left">
                    <p>ka@89a3e3c6b</p>
                    <p class="user">이용현님 환영합니다.</p>
                    <p>마지막 로그인: 2024-11-05 11:36:21</p>
                    <a href="#"><p class="modify">---------------------------------------------- 수정</p></a>
                    <p>전화: 010-1234-5678</p>
                    <p>이메일: example@kakao.com</p>
                </div>

                <!-- 포인트 정보 -->
                <div class="points-info">
                    <div>
                        <h3>총 구매금액</h3>
                        <p>0원</p>
                    </div>
                    <div>
                        <h3>적립금</h3>
                        <p>0원</p>
                        <a href="#"><span class="arrow">></span></a>
                    </div>
                    <div>
                        <h3>할인쿠폰</h3>
                        <p style="margin-right: 10px;">0개</p>
                        <a href="#"><span class="arrow">></span></a>
                    </div>
                </div>
            </div>

            <!-- 최근 주문 정보 -->
            <div class="section">
                <h2>최근 주문 정보</h2>
                <table>
                    <tr>
                        <th>주문일자</th>
                        <th>상품명</th>
                        <th>결제금액</th>
                    </tr>
                    <tr>
                        <td colspan="3">주문 내역이 없습니다.</td>
                    </tr>
                </table>
            </div>
            <hr>

            <!-- 최근 문의 내역 -->
            <div class="section">
                <h2>최근 문의 내역</h2>
                <table>
                    <tr>
                        <th>게시판</th>
                        <th>글제목</th>
                        <th>등록일</th>
                    </tr>
                    <tr>
                        <td>상품후기</td>
                        <td>좋아요</td>
                        <td>2024-11-04</td>
                    </tr>
                </table>
            </div>
            <hr>

            <!-- 찜한 상품 -->
            <div class="section">
                <h2>찜한 상품</h2>
                <div class="nothing">
                    <p>찜한 상품이 없습니다.</p>
                </div>
            </div>
        </div>
    </div>

    <%@ include file ="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>