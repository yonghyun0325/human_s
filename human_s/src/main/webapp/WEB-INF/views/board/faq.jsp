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
            <button class="active" onclick="filterCategory('전체보기')" data-category="전체보기">전체보기</button>
            <button onclick="filterCategory('회원서비스')" data-category="회원서비스">회원서비스</button>
            <button onclick="filterCategory('주문/결제')" data-category="주문/결제">주문/결제</button>
            <button onclick="filterCategory('배송문의')" data-category="배송문의">배송문의</button>
            <button onclick="filterCategory('반품/교환')" data-category="반품/교환">반품/교환</button>
        </div>

        <!-- FAQ 리스트 -->
        <div class="faq-list">
            <% 
                // FAQ 데이터 배열에 카테고리 정보를 추가
                String[][] faqs = {
                    {"1", "주문 후 배송지를 변경하려면 어떻게 해야 하나요?", "주문 완료 후 배송지 변경은 고객센터에 문의하시기 바랍니다.", "주문/결제"},
                    {"2", "아이디와 비밀번호를 분실했습니다. 어떻게 찾을 수 있나요?", "로그인 페이지에서 '아이디 찾기' 또는 '비밀번호 찾기'를 클릭하여 복구 가능합니다.", "회원서비스"},
                    {"3", "배송기간은 얼마나 걸리나요?", "일반적으로 주문 후 2~3일 이내 배송됩니다.", "배송문의"},
                    {"4", "고객센터 운영 시간은 언제인가요?", "고객센터 운영 시간은 평일 오전 9시부터 오후 6시까지입니다. 주말 및 공휴일은 운영하지 않습니다.", "회원서비스"},
                    {"5", "반품 후 환불은 얼마나 걸리나요?", "반품이 완료된 후 3~5일 이내에 환불 처리가 완료됩니다.", "반품/교환"},
                    {"6", "주문 취소는 어떻게 하나요?", "주문 취소는 '내 주문' 페이지에서 직접 취소하거나, 고객센터로 문의하시면 됩니다.", "주문/결제"},
                    {"7", "결제 수단은 어떤 것이 있나요?", "저희는 신용카드, 무통장 입금, 휴대폰 결제 등 다양한 결제 수단을 제공합니다.", "주문/결제"},
                    {"8", "상품이 불량인 경우 어떻게 교환하나요?", "상품이 불량인 경우, 수령 후 7일 이내에 고객센터로 연락 주시면 교환 절차를 안내해 드립니다.", "반품/교환"},
                    {"9", "배송비는 얼마인가요?", "일반 배송비는 3,000원이며, 주문 금액이 50,000원 이상일 경우 무료 배송이 적용됩니다.", "배송문의"},
                    {"10", "정말 믿을 수 있는 제품인가요??", "충남경제진흥원의 지원으로 충청남도 농사랑 이름을 걸고 우수 농산물만을 엄선하여 판매하고 있으니 안심하고 이용하실 수 있습니다.", "주문/결제"},
                    {"11", "회원탈퇴는 어떻게 하나요??", "1) PC로 로그인시 농사랑 홈페이지 우측 상단 [마이페이지] ▶ [마이홈] ▶ 메인 메뉴 가장 오른쪽 [회원탈퇴] ▶ 탈퇴사유 입력 ▶ 확인 2) 모바일로 로그인시 농사랑 모바일 상단 두 번 째 메뉴 [마이쇼핑] ▶ 맨 아래 오른쪽 [회원탈퇴] 클릭 ▶ 메인 메뉴 가장 오른쪽 [회원탈퇴] ▶ 탈퇴사유 입력 ▶ 확인 회원탈퇴시에는 적립금은 모두 삭제 처리되며, 아이디는 즉시 탈퇴 처리됩니다.", "회원서비스"},
                    {"12", "꼭 회원가입을 해야만 이용할 수 있습니까?", "비회원 고객님도 언제든지 농사랑을 이용할 수 있습니다. 단, 농사랑 회원님들께 드리는 혜택(이벤트참여, 사은행사, 할인쿠폰)을 받으실 수는 없습니다.", "회원서비스"},
                    {"13", "회원가입방법", "화면 상단메뉴의 회원가입/변경에서 회원가입을 누르시고, 정보 입력 후 등록하시면 됩니다.", "회원서비스"}      
                };
            

                for (String[] faq : faqs) {
            %>
            <div class="faq-item" onclick="toggleAnswer('<%= faq[0] %>')" data-category="<%= faq[3] %>">
                <div class="question">
                    <span class="question-icon">Q:</span>
                    <span class="category">[<%= faq[3] %>]</span> <!-- 카테고리 표시 -->
                    <span class="question-text"><%= faq[1] %></span>
                </div>
                <div class="answer" id="answer-<%= faq[0] %>" style="display: none;">A: <%= faq[2] %></div>
            </div>
            <% } %>
        </div>

        <!-- 페이지네이션 -->
    </div>
</div>
<%@ include file="../main/footer.jsp"%>
</body>
<script>
//FAQ 아이템 클릭 시 답변 표시/숨김
function toggleAnswer(id) {
    const answer = document.getElementById("answer-" + id);
    answer.style.display = answer.style.display === "none" ? "block" : "none";
}

//카테고리 필터링 함수
function filterCategory(category) {
    const faqItems = document.querySelectorAll(".faq-item");
    faqItems.forEach(item => {
        const itemCategory = item.getAttribute("data-category");
        if (category === "전체보기" || itemCategory === category) {
            item.style.display = "block";
        } else {
            item.style.display = "none";
        }
    });

    // 버튼 스타일 업데이트
    const buttons = document.querySelectorAll(".category-buttons button");
    buttons.forEach(button => {
        // 현재 클릭된 카테고리와 일치하는 버튼에만 active 클래스 추가
        if (button.getAttribute("data-category") === category) {
            button.classList.add("active");
        } else {
            button.classList.remove("active");
        }
    });
}

//검색 함수
function searchFAQs() {
    const searchTerm = document.querySelector(".search-bar input").value.toLowerCase();
    const faqItems = document.querySelectorAll(".faq-item");

    faqItems.forEach(item => {
        const questionText = item.querySelector(".question-text").textContent.toLowerCase();
        const answerText = item.querySelector(".answer").textContent.toLowerCase();

        // 검색어가 질문 또는 답변에 포함되어 있는 경우 표시, 아니면 숨기기
        if (questionText.includes(searchTerm) || answerText.includes(searchTerm)) {
            item.style.display = "block";
        } else {
            item.style.display = "none";
        }
    });
}

document.addEventListener("DOMContentLoaded", function() {
    // 검색 버튼 클릭 이벤트 리스너 추가
    document.querySelector(".search-bar button").addEventListener("click", searchFAQs);

    // Enter 키로도 검색이 되도록 설정
    document.querySelector(".search-bar input").addEventListener("keyup", function(event) {
        if (event.key === "Enter") {
            searchFAQs();
        }
    });
});

</script>
</html>
