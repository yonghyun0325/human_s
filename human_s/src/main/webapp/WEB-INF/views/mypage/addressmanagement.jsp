<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 배송지 관리</title>
<!-- 마이페이지 전용 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addressmanagement.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp"%>

    <h1>나의 배송지 관리</h1>
    <div class="container">
        
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

            <div class="info-box">
                <p>자주 쓰는 이용자님의 배송지를 등록 및 관리 하실 수 있습니다.</p>
            </div>

            <!-- 이용자님의 배송지 섹션 -->
            <div class="address-section">
                <h2>이용자님의 배송지</h2>
                <table>
                    <thead>
                        <tr>
                            <th><input type="checkbox"></th>
                            <th>[배송지명] 받는 사람 / 주소</th>
                            <th>연락처</th>
                            <th>주문 메시지</th>
                            <th>관리</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="5">자주 사용하는 배송지를 주소록에 추가해주세요.</td>
                        </tr>
                    </tbody>
                </table>
                <div class="button-container">
                    <button class="add-btn">배송지 추가</button>
                </div>
            </div>

            <!-- 최근 배송지 섹션 -->
            <div class="recent-address">
                <h2>최근 배송지</h2>
                <table>
                    <thead>
                        <tr>
                            <th>받는 사람</th>
                            <th>주소</th>
                            <th>연락처</th>
                            <th>관리</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="4">최근 배송지 내역이 없습니다.</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <%@ include file ="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>