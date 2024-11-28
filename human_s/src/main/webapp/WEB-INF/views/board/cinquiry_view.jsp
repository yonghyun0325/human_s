<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>고객문의</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/cinquiry_view.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>
<div class="layout">
<h3>고객문의</h3>
<%@ include file="../board/topmenu.jsp" %>
    <div class="board-container">
        <div class="detail-header">
            <h2 class="detail-title">${cinquiry.cinquiryTitle}</h2>
            <div class="detail-meta">
                <span class="meta-item">작성자: ${cinquiry.author}</span>
                <span class="meta-item">조회수: ${cinquiry.views}</span> 
                <span class="meta-item">작성일: <fmt:formatDate value="${cinquiry.createdDate}" type="date" pattern="yyyy-MM-dd" /></span>
            </div>
        </div>
        
        <div class="detail-content">
            <p>${cinquiry.cinquiryContent}</p>
        </div>
        <div class="comments-section">
    <h4>댓글</h4>

 <div id="cinquiry_comment">
<c:forEach var="item" items="${ ComList }">
		<div class="comment">
			<div>
				<div>${ item.userEntity.userNick }</div>
				<div>${ item.comment }</div>
				<div><fmt:formatDate value="${item.commDate}" pattern="yyyy-MM-dd" /></div>
			</div>
	<c:if test="${ item.userEntity.userIdx == user.userIdx }">
			<button value="${ item.ccIdx }" data-id="${ cinquiry.id }" data-userIdx="${ user.userIdx }">삭제</button>
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
		<button value="${ cinquiry.id }" data-userIdx="${ user.userIdx }">등록하기</button>
	</div>

       
        <div id="detail-btn-group">
            <!-- 조건: 로그인 상태 + 자신이 작성한 게시물일 경우 -->
            <c:if test="${(not empty user) and (user.userIdx eq notice.userEntity.userIdx)}"> 
                <button class="btn btn-edit" onclick="location.href='${pageContext.request.contextPath}/board/cinquiry/update.do?cinquiryIdx=${cinquiry.id}'">수정하기</button>
                <button class="btn btn-delete" onclick="deletePost()">삭제하기</button>
             </c:if> 
            <button class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/board/cinquiry.no'">목록으로</button>
        </div>
    </div>
</div>
</div>

<script>
    function deletePost() {
        const ans = confirm("정말 삭제하겠습니까?");
        
        if (ans) {
            location.href = "${pageContext.request.contextPath}/board/cinquiry/deleteProcess.do?cinquiryIdx=${cinquiry.id}";
        }
    }
    
    $(function(){
    	//스토리 댓글 등록
    	$("#insertComment button").click(function(){
    		let comment = $(".userComment").val();
    		let id = $(this).val();
    		let userIdx = $(this).attr("data-userIdx");
    		
    		$.ajax({
    			type: "get",
    			url: "/hms/board/cinquiry/saveComment.do",
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
    			    button.value = item.ccIdx;
    			    button.setAttribute("data-id", item.cinquiryEntity.id);
    			    button.setAttribute("data-userIdx", userIdx);
    			    button.textContent = "삭제";

    			    innerDiv.appendChild(nickDiv);
    			    innerDiv.appendChild(contentDiv);
    			    innerDiv.appendChild(dateDiv);
    			    commentDiv.appendChild(innerDiv);
    			    commentDiv.appendChild(button);

    			    document.getElementById("cinquiry_comment").appendChild(commentDiv);
    	    		
    	    		$(".userComment").val("");
            	},
            	error: function(){
            		console.log("댓글 리스트를 불러오는 데 실패했습니다.");
            	}
    		});
    		
    	});
    	
    	
    	//스토리 댓글 삭제
    	$(document).on("click", "#cinquiry_comment > .comment button", function () {
    		let ccIdx = $(this).val();
    		let id = $(this).data("id");
    		let userIdx = $(this).attr("data-userIdx");
    		
    		$.ajax({
    			type: "get",
    			url: "/hms/board/cinquiry/deleteComment.do",
    			data: {ccIdx: ccIdx, id:id},
    			headers: {"Accept": "application/json"},
    			success: function(ComList) {
    			    // 서버에서 반환된 데이터 확인
    			    if (!ComList || ComList.length === 0) {
    			        console.log("댓글 리스트가 비어 있습니다.");
    			        $("#cinquiry_comment").empty(); // DOM도 초기화
    			        return;
    			    }

    			    // 기존 댓글 DOM 비우기
    			    $("#cinquiry_comment").empty();

    			    // 댓글 리스트를 DOM에 추가
    			    ComList.forEach(function(item) {
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
    			        // DOM 구조 구성
    			        innerDiv.appendChild(nickDiv);
    			        innerDiv.appendChild(contentDiv);
    			        innerDiv.appendChild(dateDiv);
    			        commentDiv.appendChild(innerDiv);
    			        
    			        // 삭제 버튼 추가
    			        if (userIdx == item.userEntity.userIdx) {
    			            const button = document.createElement("button");
    			            button.value = item.ccIdx;
    			            button.setAttribute("data-id", item.cinquiryEntity.id);
    			            button.setAttribute("data-userIdx", userIdx);
    			            button.textContent = "삭제";
    			            commentDiv.appendChild(button);
    			        }

    			    

    			        // 댓글을 DOM에 추가
    			        document.getElementById("cinquiry_comment").appendChild(commentDiv);
    			    });
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
