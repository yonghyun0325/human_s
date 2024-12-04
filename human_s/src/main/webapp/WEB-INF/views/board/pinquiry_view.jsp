<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품문의</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/pinquiry_view.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<h3>상품문의</h3>
<%@ include file="../board/topmenu.jsp" %>
    <div class="board-container">
        <div class="detail-header">
            <h2 class="detail-title">${pinquiry.pinquiryTitle}</h2>
            <div class="detail-meta">
                <span class="meta-item">작성자: ${pinquiry.author}</span>
                <span class="meta-item">조회수: ${pinquiry.views}</span> 
                <span class="meta-item">작성일: <fmt:formatDate value="${pinquiry.createdDate}" type="date" pattern="yyyy-MM-dd" /></span>
            </div>
        </div>
<c:if test="${ not empty pinquiry.productEntity }">
        <!-- 태그된 상품 -->    
		<div class="tagged-products">
			<a href="/hms/product/viewDetail.no?idx=${ pinquiry.productEntity.pdtIdx }">
	            <div class="tagged-items">
    <c:choose>
    	<c:when test="${ not empty pinquiry.productEntity.img }">
		        	<img src="${ pinquiry.productEntity.img }" alt="태그된 상품 이미지">
    	</c:when>
    	<c:otherwise>
	    			<img src="${pageContext.request.contextPath}/resources/uploads/${ pinquiry.productEntity.pdtSave }" 
	    				alt="${ pinquiry.productEntity.pdtOrigin }">
    	</c:otherwise>
    </c:choose>
		    
			        <div class="tagged-item-info">
			            <div class="tagged-item-title">${ pinquiry.productEntity.pdtTitle }</div>
			            <div class="tagged-item-price">${ pinquiry.productEntity.pdtPrice }원</div>
			        </div>
	    		</div>
	    	</a>
	    <div>
</c:if>
        
        <div class="detail-content">
            <p>${pinquiry.pinquiryContent}</p>
        </div>
        
        <h4>판매자 답변</h4>
       	 <div id="pinquiry_comment">
<c:choose>
	<c:when test="${ not empty pinquiry.pinquiryComment }">
	        <div class="comment">
				<div>
					<div>${ pinquiry.pinquiryComment }</div>
					<div><fmt:formatDate value="${pinquiry.updateComment}" pattern="yyyy-MM-dd" /></div>
				</div>
			</div>
	</c:when>
	<c:otherwise>
			<div class="comment">
				<div>등록된 답변이 없습니다.</div>
			</div>
	</c:otherwise>
</c:choose>
		</div>

<c:if test="${ user.userIdx == pinquiry.productEntity.userEntity.userIdx }">
		<div id="insertComment">
			<input class="sellerComment" placeholder="등록된 답변이 있다면 내용이 수정됩니다."></input>
			<button value="${ pinquiry.id }" data-userIdx="${ user.userIdx }">등록하기</button>
		</div>
</c:if>
       
        <div id="detail-btn-group">
            <!-- 조건: 로그인 상태 + 자신이 작성한 게시물일 경우 -->
            <c:if test="${(not empty user) and (user.userIdx eq pinquiry.userEntity.userIdx)}"> 
                <button class="btn btn-edit" onclick="location.href='${pageContext.request.contextPath}/board/pinquiry/update.do?pinquiryIdx=${pinquiry.id}'">수정하기</button>
                <button class="btn btn-delete" onclick="deletePost()">삭제하기</button>
             </c:if> 
            <button class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/board/pinquiry.no'">목록으로</button>
        </div>
    </div>
</div>

<script>
    function deletePost() {
        const ans = confirm("정말 삭제하겠습니까?");
        
        if (ans) {
            location.href = "${pageContext.request.contextPath}/board/pinquiry/deleteProcess.do?pinquiryIdx=${pinquiry.id}";
        }
    }
    
    $(function(){
    	//판매자 답변 등록
    	$("#insertComment button").click(function(){
    		let comment = $(".sellerComment").val();
    		let id = $(this).val();
    		let userIdx = $(this).attr("data-userIdx");
    		
    		$.ajax({
    			type: "get",
    			url: "/hms/board/pinquiry/saveComment.do",
    			data: {comment: comment, id: id},
    			headers: {"Accept": "application/json"},
    			success: function(item){
    				let date = new Date(item.updateComment); // item.commDate: 밀리초 기반 시간
    				let commentDate = 
    				    date.getFullYear() + "-" + 
    				    String(date.getMonth() + 1).padStart(2, "0") + "-" + 
    				    String(date.getDate()).padStart(2, "0");	
    				
    				$("#pinquiry_comment").empty();

    			    // DOM 요소 직접 생성
    			    const commentDiv = document.createElement("div");
    			    commentDiv.classList.add("comment");

    			    const innerDiv = document.createElement("div");

    			    const commDiv = document.createElement("div");
    			    commDiv.textContent = item.pinquiryComment;

    			    const dateDiv = document.createElement("div");
    			    dateDiv.textContent = commentDate;

    			    innerDiv.appendChild(commDiv);
    			    innerDiv.appendChild(dateDiv);
    			    commentDiv.appendChild(innerDiv);

    			    document.getElementById("pinquiry_comment").appendChild(commentDiv);
    	    		
    	    		$(".sellerComment").val("");
            	},
            	error: function(){
            		console.log("댓글 리스트를 불러오는 데 실패했습니다.");
            	}
    		});
    		
    	});
    });
</script>

<%@ include file="../main/footer.jsp"%>

</body>
</html>  
