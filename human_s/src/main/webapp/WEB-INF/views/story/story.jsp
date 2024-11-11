<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>포스팅 카드</title>
 <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/story.css">

</head>
<body>
 <%@ include file="../main/header.jsp"%>
    <div class="layout">
   
         
        <div class="story-write">
        <button type="button" id="write_btn">스토리 올리기</button>
        </div>
        <div class="post-cards-container">
            <!-- 카드 반복 예제 (4개의 카드) -->
            <c:forEach var="i" begin="1" end="4">
                <div class="post-card">
                    <!-- 상단 헤더 -->
                    <div class="post-header">
                        <img src="${pageContext.request.contextPath}/resources/img/tree.jpg" alt="프로필 사진" class="profile-pic">
                        <div class="seller-info">
                            <div class="seller-name">농부</div>
                            <div class="post-time">4시간 전</div>
                        </div>
                        <button class="follow-btn">+ 팔로우</button>
                    </div>

                    <!-- 메인 이미지 -->
                    <img src="${pageContext.request.contextPath}/resources/img/han.jpg" alt="게시 이미지" class="post-image">

                    <!-- 태그된 작품 -->
                    <div class="tagged-items">
                        <img src="${pageContext.request.contextPath}/resources/img/kimchi.jpg" alt="태그된 상품 이미지">
                        <div class="tagged-item-info">
                            <div class="tagged-item-title">[SET] 한우세트</div>
                            <div class="tagged-item-price">20% 31,120원</div>
                        </div>
                    </div>

                    <!-- 게시물 설명 -->
                    <div class="post-description">
                        집들이 선물로 좋은 홀트 커플 머그 세트 :)
                    </div>

                    <!-- 하단 버튼들 -->
                    <div class="post-footer">
                        <button>
                            <img src="https://img.icons8.com/ios/50/happy--v1.png" alt="공감하기"> 공감하기
                        </button>
                        <button>
                            <img src="https://img.icons8.com/ios/50/comments--v1.png" alt="댓글쓰기"> 댓글쓰기
                        </button>
                    </div>
                </div>
            </c:forEach>
        </div>

       
    </div>
    <%@ include file="../main/footer.jsp"%> 
</body>
</html>
