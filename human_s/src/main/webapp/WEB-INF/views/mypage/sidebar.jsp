<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 사이드바 전용 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/sidebar.css">
</head>
<body>  
        <!-- 좌측 메뉴 -->
        <div class="sidebar">
	<c:if test="${ user.grade == 1 || not empty unuser }">
            <h3>쇼핑정보</h3>
            <ul>
                <li><a href="order.do">주문배송현황</a></li>
                <li><a href="orderRefundCancel.no">환불/취소 내역</a></li>
		<c:if test="${ not empty user }">
                <li><a href="address.do">나의 배송지 관리</a></li>
		</c:if>
            </ul>
	</c:if>
	<c:if test="${ user.grade == 1 }">
            <h3>혜택정보</h3>
            <ul>
                <li><a href="coupon.do">할인쿠폰내역</a></li>
                <li><a href="points.do">적립금내역</a></li>
            </ul>
            <h3>활동정보</h3>
            <ul>
                <li><a href="favorite.do">찜한상품</a></li>
                <li><a href="todaygoods.do">최근 본 상품</a></li>
                <li><a href="basket.do">장바구니</a></li>
                <li><a href="inquiry.do">1:1 문의내역</a></li>
                <li><a href="myarticle.do">내 게시글 보기</a></li>
            </ul>
	</c:if>
	<c:if test="${ user.grade == 2 }">
            <h3>판매정보</h3>
            <ul>
                <li><a href="#">판매 현황</a></li>
            </ul>
            <h3>활동정보</h3>
            <ul>
            	<li><a href="#">상품 문의 내역</a></li>
                <li><a href="#">1:1 문의내역</a></li>
                <li><a href="#">내 게시글 보기</a></li>
            </ul>
	</c:if>
	<c:if test="${ not empty user }">
            <h3>개인정보</h3>
            <ul>
                <li><a href="update.do" class="no-underline">회원정보수정</a></li>
                <li><a href="javascript:void(0);" onclick="confirmWithdrawal()">회원탈퇴</a></li>
            </ul>
	</c:if>
        </div>
        
        <script>
            function confirmWithdrawal() {
                if (confirm("정말로 탈퇴하시겠습니까? 모든 정보가 삭제됩니다.")) {
                    // 탈퇴 요청 경로로 이동
                    window.location.href = "${pageContext.request.contextPath}/user/withdrawal.do";
                }
            }
        </script>
</body>
</html>