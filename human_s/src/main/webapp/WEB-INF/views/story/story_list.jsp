<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>스토리</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/story_list.css">
</head>
<body>
    <%@ include file="../main/header.jsp" %>
    <div class="layout">
      <div class="story-write">
    <!-- grade가 2 또는 3인 경우에만 "스토리 올리기" 버튼 표시 -->
		    <%-- <c:if test="${not empty grade and (grade == 2 or grade == 3)}">  --%>
		    <c:if test="${user.grade == 2 or user.grade == 3}">
		        <a href="${pageContext.request.contextPath}/story/story/write.do">
		            <button class="review_write_btn" type="button" id="write_btn">스토리 올리기</button> 
		        </a>
		    </c:if>
		</div>


        <div id="story-cards-container" class="story-cards-container" data-user="${ user ? user.userIdx : 0 }">
            <!-- 초기 6개의 스토리만 로드 -->
            <c:forEach var="story" items="${stories}">
                <div class="story-card">
                     <div class="story-card">
    <!-- 상단 헤더 -->
    <div class="story-header">
        <!-- 프로필 사진 (프로필 페이지로 이동) -->
        <a href="#">
            <img src="${pageContext.request.contextPath}/resources/img/han.jpg" alt="프로필 사진" class="profile-pic">
        </a>

        <!-- 이름 (다른 페이지로 이동) -->
        <div class="seller-info">
            <a href="#" class="seller-name">
                ${story.userEntity.userNick}
            </a>
            <div class="story-time">
                <fmt:formatDate value="${story.createdDate}" pattern="yyyy-MM-dd" />
            </div>
        </div>

        <!-- 팔로우 버튼 (동작만 수행) -->
        <button class="follow-btn" onclick="followUser('${story.author}')">+ 팔로우</button>
    </div>

    <!-- 게시물 메인 이미지 (스토리 상세 페이지로 이동) -->
    <a href="${pageContext.request.contextPath}/story/view?storyId=${story.id}">
        <img src="${pageContext.request.contextPath}${story.mainImage}" alt="게시 이미지" class="story-image">

    <!-- 게시물 설명 (스토리 상세 페이지로 이동) -->
        <div class="story-description">
            ${story.storyContent}
        </div>
    </a>

    <!-- 태그된 아이템  -->
    <a href="${pageContext.request.contextPath}/product/viewDetail.no?idx=${story.product.pdtIdx}">
    <div class="tagged-items">
    <c:choose>
    	<c:when test="${ not empty story.product.img }">
	        <img src="${story.product.img}" alt="태그된 상품 이미지">
    	</c:when>
    	<c:otherwise>
    		<img src="${pageContext.request.contextPath}/resources/uploads/${story.product.pdtSave}" alt="${ story.product.pdtOrigin }">
    	</c:otherwise>
    </c:choose>
    
        <div class="tagged-item-info">
            <div class="tagged-item-title">${story.product.pdtTitle}</div>
            <div class="tagged-item-price">${story.product.pdtPrice}원</div>
        </div>
    </div>
    </a>
    
</div>

                    <div class="story-footer">
                        <button class="thumbsUpBtn">
                            <i class="fa-solid fa-thumbs-up"></i>
                            <i class="fa-regular fa-thumbs-up"></i> 공감하기
                        </button>
                        <button class="insertcomment" data-id="${ story.id }">
                            <img src="https://img.icons8.com/ios/50/comments--v1.png" alt="댓글쓰기"> 댓글쓰기
                        </button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <%@ include file="../main/footer.jsp" %> 

  <script>
  
  $(function(){
	  
	  $(".insertcomment").click(function(){
		  let id = $(this).data("id");
		  console.log(id);
		  
		  //페이지 이동 후 댓글창으로 스크롤 내리기 위한 #insertComment
		  location.href = "/hms/story/view?storyId="+id+"#insertComment";
	  });
	  
	  $(".thumbsUpBtn").click(function(){
		  let userIdx = $("#story-cards-container").data("user");
		  if(userIdx == 0){
			  alert("로그인 후 진행해주세요.");
			  return;
		  }
		  
		 $(this).children("i").toggle(); 
		 
		 let currentColor = $(this).css("color");
		 
	     if (currentColor === "rgb(51, 51, 51)") {
			 $(this).css("color", "#ff3333");
		 }else{
			 $(this).css("color", "#666");
		 }
	  });
	  
  });
  
  
	    /* let page = 1; // 초기 페이지
        const size = 6; // 한 번에 가져올 항목 수

        // 무한 스크롤 이벤트
        window.addEventListener('scroll', () => {
            if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
                loadMoreStories();
            }
        });

        // 서버로부터 추가 데이터를 가져와서 story-cards-container에 추가
        function loadMoreStories() {
            fetch(`/story/loadStories?page=${page}&size=${size}`)
                .then(response => response.json())
                .then(stories => {
                    const container = document.getElementById('story-cards-container');
                    stories.forEach(story => {
                        const storyCard = document.createElement('div');
                        storyCard.className = 'story-card';
                        storyCard.innerHTML = `
                            <div class="story-header">
                                <img src="${pageContext.request.contextPath}/resources/img/${story.profileImage}" alt="프로필 사진" class="profile-pic">
                                <div class="seller-info">
                                    <div class="seller-name">${story.author}</div>
                                    <div class="story-time">${story.createdDate}</div>
                                </div>
                                <button class="follow-btn">+ 팔로우</button>
                            </div>
                            <img src="${pageContext.request.contextPath}/resources/img/${story.image}" alt="게시 이미지" class="story-image">
                            <div class="tagged-items">
                                <img src="${pageContext.request.contextPath}/resources/img/kimchi.jpg" alt="태그된 상품 이미지">
                                <div class="tagged-item-info">
                                    <div class="tagged-item-title">${story.taggedItemTitle}</div>
                                    <div class="tagged-item-price">${story.taggedItemPrice}</div>
                                </div>
                            </div>
                            <div class="story-description">${story.storyContent}</div>
                            <div class="story-footer">
                                <button><img src="https://img.icons8.com/ios/50/happy--v1.png" alt="공감하기"> 공감하기</button>
                                <button><img src="https://img.icons8.com/ios/50/comments--v1.png" alt="댓글쓰기"> 댓글쓰기</button>
                            </div>
                        `;
                        container.appendChild(storyCard);
                    });
                    page++; // 다음 페이지로 증가
                })
                .catch(error => console.error('Error loading more stories:', error));
        } */
    </script>
</body>
</html>
