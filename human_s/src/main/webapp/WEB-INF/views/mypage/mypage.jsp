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

        <!-- 좌측 메뉴 -->
        <div class="sidebar">
            <h3>쇼핑정보</h3>
            <ul>
                <li>주문배송현황</li>
                <li>나의 배송지 관리</li>
                <li>선물함</li>
                <li>정기구독 관리</li>
            </ul>
            <h3>혜택정보</h3>
            <ul>
                <li>할인쿠폰내역</li>
                <li>적립금내역</li>
            </ul>
            <h3>활동정보</h3>
            <ul>
                <li>찜한상품</li>
                <li>최근 본 상품</li>
                <li>장바구니</li>
                <li>1:1 문의내역</li>
                <li>내 게시글 보기</li>
            </ul>
            <h3>개인정보</h3>
            <ul>
                <li><a href="/hms/mypage/update.do" class="no-underline">회원정보수정</a></li>
                <li>회원탈퇴</li>
            </ul>
        </div>

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