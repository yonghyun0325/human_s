<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>FAQ 게시판</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/faq.css">
    
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<%@ include file="../board/topmenu.jsp" %>
<div class="faq-container">
    <div class="faq-header">FAQ</div>

    <!-- 검색 바 섹션 -->
    <div class="search-bar">
        <input type="text" placeholder="검색어를 입력하세요">
        <button type="button">검색</button>
    </div>

    <!-- 카테고리 버튼 -->
    <div class="category-buttons">
        <button class="active">전체보기</button>
        <button>회원서비스</button>
        <button>주문/결제</button>
        <button>배송문의</button>
        <button>반품/교환</button>
    </div>

    <!-- FAQ 리스트 -->
<div class="faq-list">
    <% 
        String[][] faqs = {
            {"1", "주문 후 배송지를 변경하려면 어떻게 해야 하나요?", "주문 완료 후 배송지 변경은 고객센터에 문의하시기 바랍니다."},
            {"2", "아이디와 비밀번호를 분실했습니다. 어떻게 찾을 수 있나요?", "로그인 페이지에서 '아이디 찾기' 또는 '비밀번호 찾기'를 클릭하여 복구 가능합니다."},
            {"3", "배송기간은 얼마나 걸리나요?", "일반적으로 주문 후 2~3일 이내 배송됩니다."},
            {"4", "고객센터 운영 시간은 언제인가요?", "고객센터 운영 시간은 평일 오전 9시부터 오후 6시까지입니다. 주말 및 공휴일은 운영하지 않습니다."},
            {"5", "반품 후 환불은 얼마나 걸리나요?", "반품이 완료된 후 3~5일 이내에 환불 처리가 완료됩니다."},
            {"6", "주문 취소는 어떻게 하나요?", "주문 취소는 '내 주문' 페이지에서 직접 취소하거나, 고객센터로 문의하시면 됩니다."},
            {"7", "결제 수단은 어떤 것이 있나요?", "저희는 신용카드, 무통장 입금, 휴대폰 결제 등 다양한 결제 수단을 제공합니다."},
            {"8", "상품이 불량인 경우 어떻게 교환하나요?", "상품이 불량인 경우, 수령 후 7일 이내에 고객센터로 연락 주시면 교환 절차를 안내해 드립니다."},
            {"9", "배송비는 얼마인가요?", "일반 배송비는 3,000원이며, 주문 금액이 50,000원 이상일 경우 무료 배송이 적용됩니다."}
        };

        for (String[] faq : faqs) {
    %>
    <div class="faq-item" onclick="toggleAnswer('<%= faq[0] %>')">
        <div class="question">
            <span class="question-icon">Q:</span>
            <span class="question-text"><%= faq[1] %></span>
        </div>
        <div class="answer" id="answer-<%= faq[0] %>">A: <%= faq[2] %></div>
    </div>
    <% } %>
</div>

    <!-- 페이지네이션 -->
    <div class="pagination">
        <a href="#">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <span>...</span>
        <a href="#">10</a>
    </div>
</div>
</div>
<%@ include file="../main/footer.jsp"%>
</body>
<script>
        // FAQ 아이템 클릭 시 답변 표시/숨김
        function toggleAnswer(id) {
            const answer = document.getElementById("answer-" + id);
            answer.style.display = answer.style.display === "none" ? "block" : "none";
        }
    </script>
</html>
