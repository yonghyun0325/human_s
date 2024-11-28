<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>스토리 상세 보기</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/story_view.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
	<div class="top"> 
    <h1>${story.storyTitle}</h1>
    </div>
    <div>
         <img src="${pageContext.request.contextPath}/${story.contentImage}" alt="스토리 이미지">
    </div>
   	
    <p>${story.storyContent}</p>
        <!-- 태그된 상품 표시 -->
    <div class="tagged-products">
        <h3>태그된 상품</h3>
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
    <div>
        <small>작성자: ${story.userEntity.userNick}</small>
        <small>작성일: <fmt:formatDate value="${story.createdDate}" pattern="yyyy-MM-dd" /></small>
    </div>
    
    <div id="story_comment">
<c:forEach var="item" items="${ storyComList }">
		<div class="comment">
			<div>
				<div>${ item.userEntity.userNick }</div>
				<div>${ item.comment }</div>
				<div><fmt:formatDate value="${item.commDate}" pattern="yyyy-MM-dd" /></div>
			</div>
	<c:if test="${ item.userEntity.userIdx == user.userIdx }">
			<button value="${ item.scIdx }" data-id="${ story.id }" data-userIdx="${ item.userEntity.userIdx }">삭제</button>
	</c:if>
		</div>
</c:forEach>
    </div>
	<div id="insertComment">
<c:if test="${ not empty user }">
		<input class="userComment"></input>
</c:if>
<c:if test="${ empty user }">
		<input class="unuserComment" placeholder="로그인 후 이용해주세요" disabled></input>
</c:if>
		<button value="${ story.id }">등록하기</button>
	</div>
    
    
            <div id="detail-btn-group">
            <!-- 조건: 로그인 상태 + 자신이 작성한 게시물일 경우 -->
		  <c:if test="${not empty user and user.userIdx == story.userEntity.userIdx}">
		    <button class="btn btn-edit" 
		            onclick="location.href='${pageContext.request.contextPath}/story/update.do?storyIdx=${story.id}'">수정하기</button>
		    <button class="btn btn-delete" onclick="deletePost()">삭제하기</button>
		</c:if>

            <button class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/story/farmstory.no'">목록으로</button>
        </div>
</div>
</div>
<%@ include file="../main/footer.jsp"%>
</body>
<script>
    function deletePost() {
        const ans = confirm("정말 삭제하겠습니까?");
        
        if (ans) {
            location.href = "${pageContext.request.contextPath}/story/delete.do?storyIdx=${story.id}";
        }
    }
    
    $(function(){
    	//스토리 댓글 등록
    	$("#insertComment button").click(function(){
    		let comment = $(".userComment").val();
    		let id = $(this).val();
    		
    		$.ajax({
    			type: "get",
    			url: "/hms/story/saveComment.do",
    			data: {comment: comment, id: id},
    			headers: {"Accept": "application/json"},
    			success: function(item){
    				let date = new Date(item.commDate); // item.commDate: 밀리초 기반 시간
    				let commentDate = 
    				    date.getFullYear() + "-" + 
    				    String(date.getMonth() + 1).padStart(2, "0") + "-" + 
    				    String(date.getDate()).padStart(2, "0");			    

    			    // DOM 요소 직접 생성
    			    const commentDiv = document.createElement("div");
    			    commentDiv.classList.add("comment");

    			    const innerDiv = document.createElement("div");

    			    const nickDiv = document.createElement("div");
    			    nickDiv.textContent = item.userEntity.userNick;

    			    const contentDiv = document.createElement("div");
    			    contentDiv.textContent = item.comment;

    			    const dateDiv = document.createElement("div");
    			    dateDiv.textContent = commentDate;

    			    const button = document.createElement("button");
    			    button.value = item.scIdx;
    			    button.setAttribute("data-id", item.storyEntity.id);
    			    button.textContent = "삭제";

    			    innerDiv.appendChild(nickDiv);
    			    innerDiv.appendChild(contentDiv);
    			    innerDiv.appendChild(dateDiv);
    			    commentDiv.appendChild(innerDiv);
    			    commentDiv.appendChild(button);

    			    document.getElementById("story_comment").appendChild(commentDiv);
    	    		
    	    		$(".userComment").val("");
            	},
            	error: function(){
            		console.log("댓글 리스트를 불러오는 데 실패했습니다.");
            	}
    		});
    		
    	});
    	
    	
    	//스토리 댓글 삭제
    	$(document).on("click", "#story_comment > .comment button", function () {
    		let scIdx = $(this).val();
    		let id = $(this).data("id");
    		let userIdx = $(this).data("userIdx");
    		
    		$.ajax({
    			type: "get",
    			url: "/hms/story/deleteComment.do",
    			data: {scIdx: scIdx, id:id},
    			headers: {"Accept": "application/json"},
    			success: function(storyComList){
    				// 기존 댓글 DOM 비우기
    	            $("#story_comment").empty();

    	            // 삭제 후 반환된 댓글 리스트를 DOM으로 추가
    	            storyComList.forEach(function(item) {
    	                // 날짜 포맷팅
    	                let date = new Date(item.commDate);
    	                let commentDate =
    	                    date.getFullYear() + "-" +
    	                    String(date.getMonth() + 1).padStart(2, "0") + "-" +
    	                    String(date.getDate()).padStart(2, "0");

    	                // 댓글 요소 생성
    	                const commentDiv = document.createElement("div");
    	                commentDiv.classList.add("comment");

    	                const innerDiv = document.createElement("div");

    	                const nickDiv = document.createElement("div");
    	                nickDiv.textContent = item.userEntity.userNick;

    	                const contentDiv = document.createElement("div");
    	                contentDiv.textContent = item.comment;

    	                const dateDiv = document.createElement("div");
    	                dateDiv.textContent = commentDate;

    	                if(userIdx == item.userEntity.userIdx){
	    	                const button = document.createElement("button");
	    	                button.value = item.scIdx;
	    	                button.setAttribute("data-id", item.storyEntity.id);
	    	                button.textContent = "삭제";
    	                }

    	                // DOM 구조 구성
    	                innerDiv.appendChild(nickDiv);
    	                innerDiv.appendChild(contentDiv);
    	                innerDiv.appendChild(dateDiv);
    	                commentDiv.appendChild(innerDiv);
    	                if(userIdx == item.userEntity.userIdx){
    	                	commentDiv.appendChild(button);
    	                }

    	                // 새 댓글을 DOM에 추가
    	                document.getElementById("story_comment").appendChild(commentDiv);
    	            });
            	},
            	error: function(){
            		console.log("댓글 리스트를 불러오는 데 실패했습니다.");
            	}
    		});
    		
    	});
    	
    });
</script>

</html>
