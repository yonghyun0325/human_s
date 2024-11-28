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
    
    <div class="story_comment">
<c:forEach var="item" items="${ storyComList }">
		<div class="comment">
			<div>
				<div>${ item.userEntity.userNick }</div>
				<div>${ item.comment }</div>
				<div><fmt:formatDate value="${item.commDate}" pattern="yyyy-MM-dd" /></div>
	<c:if test="${ item.userEntity.userIdx == user.userIdx }">
			<button value="${ item.scIdx }" data-id="${ story.id }">삭제</button>
	</c:if>
			</div>
		</div>
</c:forEach>
    </div>
	<div class="insertComment">
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
    
    function comment(item){
    	let commentDate = new Date(item.commDate).toLocaleString();
    	
    	return `
	    	<div class="comment">
				<div>
					<div>${ item.userEntity.userNick }</div>
					<div>${ item.comment }</div>
					<div>${ commentDate }</div>
					<button value="${ item.scIdx }" data-id="${ item.storyEntity.id }">삭제</button>
				</div>
			</div>   	
	    `;
    };
    
    $(function(){
    	//스토리 댓글 등록
    	$(".insertComment button").click(function(){
    		let comment = $(".userComment").val();
    		let id = $(this).val();
    		
    		$.ajax({
    			type: "get",
    			url: "/hms/story/saveComment.do",
    			data: {comment: comment, id: id},
    			headers: {"Accept": "application/json"},
    			success: function(item){
    				let htmlContent = "";
    				let commentDate = new Date(item.commDate).toLocaleString();
    				
    				htmlContent += `
    					<div class="comment">
	    					<div>
	    						<div>${ item.userEntity.userNick }</div>
	    						<div>${ item.comment }</div>
	    						<div>${ commentDate }</div>
	    						<button value="${ item.scIdx }" data-id="${ item.storyEntity.id }">삭제</button>
	    					</div>
	    				</div>  
    				`;
    	    		
    	    		$(".story_comment").append(htmlContent);
    	    		
    	    		$(".userComment").val("");
            	},
            	error: function(){
            		console.log("댓글 리스트를 불러오는 데 실패했습니다.");
            	}
    		});
    		
    	});
    	
    	
    	//스토리 댓글 삭제
    	$(document).on("click", ".story_comment > .comment button", function () {
    		let scIdx = $(this).val();
    		let id = $(this).data("id");
    		
    		$.ajax({
    			type: "get",
    			url: "/hms/story/deleteComment.do",
    			data: {scIdx: scIdx, id:id},
    			headers: {"Accept": "application/json"},
    			success: function(storyComList){
    				let htmlContent = "";
    				
    				storyComList.forEach(function(item){
	    				
	    				htmlContent += comment(item);
    					
    				});
    	    		
    	    		$(".story_comment").html(htmlContent);
            	},
            	error: function(){
            		console.log("댓글 리스트를 불러오는 데 실패했습니다.");
            	}
    		});
    		
    	});
    	
    });
</script>

</html>
