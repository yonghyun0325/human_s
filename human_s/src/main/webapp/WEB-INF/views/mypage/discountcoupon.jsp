<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>할인쿠폰 내역</title>
<!-- 할인쿠폰내역 페이지 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/discountcoupon.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp" %>

    <h1>할인쿠폰내역</h1>
    <!-- 메인 컨테이너 -->
    <div class="container">
        
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp"%>

        <!-- 메인 콘텐츠 -->
        <main class="main-content">
            
            <div class="coupon-header">
                <p><span class="user-name">[이용현]</span>님이 쇼핑몰에서 사용 가능한 쿠폰 내역입니다.</p>
                <button class="register-coupon-btn">쿠폰 번호 등록</button>
            </div>

            <!-- 쿠폰 테이블 -->
            <table class="coupon-table">
                <thead>
                    <tr>
                        <th>쿠폰명/번호</th>
                        <th>할인/적립금액</th>
                        <th>사용가능기간</th>
                        <th>사용처</th>
                        <th>사용유무</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="5">쿠폰 내역이 없습니다.</td>
                    </tr>
                </tbody>
            </table>

            <!-- 안내 메시지 -->
            <div class="notice-box">
                <p>주문 할인쿠폰 제공방법</p><br>
                <p>주문서 쿠폰 선택에서 고객님이 보유하신 [쿠폰번호]를 선택하시면 할인금액(또는 적립금액)이 나타납니다.
                    (정률할인(적립)의 경우, 할인율(적립률)이 나타납니다.)</p>
            </div>

            <div class="notice-box">
                <p>쿠폰 사용시 유의사항</p><br>
                <p>① 쿠폰마다 사용 가능 금액과 사용기한이 정해져 있으며, 1개 주문서에 1개의 쿠폰만을 사용하실 수 있습니다.</p><br>
                <p>② 쿠폰을 사용한 주문 후 반품/환불/취소의 경우 한번 사용하신 쿠폰은 다시 사용하실 수 없습니다.</p><br>
                <p>③ 쿠폰 적용 품목 및 카테고리가 제한된 경우 해당 품목 또는 카테고리에서만 쿠폰을 사용하실 수 있습니다.</p><br>
                <p>④ 할인/적립 쿠폰은 적립금할인 등을 제외한 실제 결제금액에 적용됩니다.</p><br>
                <p>⑤  모바일 아이콘 표시 쿠폰은 모바일에서만 사용이 가능합니다.</p><br>
            </div>
        </main>
    </div>

    <%@ include file="/WEB-INF/views/main/footer.jsp" %>
</body>
</html>