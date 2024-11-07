<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>포스팅 카드</title>
    <style>
        /* 페이지 전체 스타일 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
        }

        /* 레이아웃 컨테이너 스타일 */
        .layout {
            max-width: 1200px;
            width: 100%;
            display: flex;
            flex-direction: column; /* 세로 배치 */
            align-items: center;
        }

        /* 카드 컨테이너 스타일 */
        .post-cards-container {
            display: grid;
            grid-template-columns: repeat(2, 1fr); /* 한 줄에 2개의 카드 */
            gap: 20px;
            width: 100%;
            max-width: 800px;
            margin-top: 20px;
        }

        /* 카드 전체 레이아웃 */
        .post-card {
            width: 100%;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            display: flex;
            flex-direction: column;
        }

        /* 상단 헤더 스타일 */
        .post-header {
            display: flex;
            align-items: center;
            padding: 15px;
            border-bottom: 1px solid #eee;
        }

        .profile-pic {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
        }

        .seller-info {
            flex-grow: 1;
        }

        .seller-name {
            font-weight: bold;
            font-size: 16px;
            margin: 0;
        }

        .post-time {
            color: #888;
            font-size: 12px;
        }

        .follow-btn {
            padding: 5px 10px;
            font-size: 12px;
            color: #ff6600;
            border: 1px solid #ff6600;
            border-radius: 5px;
            background-color: transparent;
            cursor: pointer;
        }

        .follow-btn:hover {
            background-color: #ff6600;
            color: #fff;
        }

        /* 이미지 스타일 */
        .post-image {
            width: 100%;
            height: auto;
        }

        /* 태그된 작품 섹션 스타일 */
        .tagged-items {
            padding: 15px;
            font-size: 14px;
            border-bottom: 1px solid #eee;
            display: flex;
            align-items: center;
        }

        .tagged-items img {
            width: 50px;
            height: 50px;
            margin-right: 10px;
            border-radius: 5px;
            object-fit: cover;
        }

        .tagged-item-info {
            display: flex;
            flex-direction: column;
        }

        .tagged-item-title {
            font-weight: bold;
            font-size: 14px;
            color: #ff6600;
        }

        .tagged-item-price {
            font-size: 13px;
            color: #333;
        }

        /* 설명 스타일 */
        .post-description {
            padding: 15px;
            font-size: 14px;
            color: #333;
            line-height: 1.5;
        }

        /* 하단 버튼 섹션 */
        .post-footer {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 15px;
            border-top: 1px solid #ddd;
            font-size: 14px;
            color: #666;
        }

        .post-footer button {
            border: none;
            background: none;
            font-size: 14px;
            cursor: pointer;
            color: #666;
            display: flex;
            align-items: center;
        }

        .post-footer button:hover {
            color: #333;
        }

        .post-footer button img {
            width: 16px;
            margin-right: 5px;
        }
                /* 버튼 컨테이너 스타일 */

		.story-write{
		display:flex;
		justify-content: flex-end;
		width: 65%;
		}
        #write_btn {
            background-color: #ff6600; /* 버튼 배경 색상 */
            color: #fff; /* 버튼 텍스트 색상 */
            border: none;
            padding: 15px 20px;
            font-size: 16px;
            font-weight: bold;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        /* 버튼 호버 효과 */
        #write_btn:hover {
            background-color: #e65500; /* 호버 시 색상 변경 */
            transform: translateY(-3px); /* 살짝 위로 이동 */
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
        }

        /* 버튼 활성화 효과 */
        #write_btn:active {
            transform: translateY(1px); /* 클릭 시 살짝 아래로 */
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>

    <div class="layout">
    <%@ include file="../main/header.jsp"%>
         
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

        <%@ include file="../main/footer.jsp"%> <!-- 푸터를 세로 방향으로 하단에 배치 -->
    </div>
</body>
</html>
