<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글수정</title>


<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/review_update.css">
  <style>
        /* 기본적으로 select와 option에 빨간색 텍스트를 적용 */
        #reviewRating {
            color: red; /* 기본 색을 빨간색으로 설정 */
        }

        /* 옵션 선택 전 기본 옵션 색상 변경 */
        #reviewRating option[value=""] {
            color: gray; /* 별점 선택 메시지의 색상을 회색으로 */
        }
    </style>

</head>
<body>
<%@ include file="../main/header.jsp"%>
<h2>상품후기</h2>
<%@ include file="../board/topmenu.jsp" %>
<div class="layout">
    <div id="board-container">
        <form action="${pageContext.request.contextPath}/board/review/updateProcess.do" method="post" onsubmit="return validateForm()">
            <input type="hidden" name="reviewIdx" value="${review.id}"/>
            
            <div>
                <label for="reviewTitle">제목:</label>
                <input type="text" id="reviewTitle" name="reviewTitle" value="${review.reviewTitle}" required>
            </div>
            
            <!-- 평점 수정 옵션 추가 -->
            <div>
                <label for="reviewRating">평점:</label>
                <select id="reviewRating" name="rating" required>
                    <option value="">평점을 선택하세요</option>
                    <option value="1" ${review.rating == 1 ? 'selected' : ''}> ★☆☆☆☆</option>
                    <option value="2" ${review.rating == 2 ? 'selected' : ''}> ★★☆☆☆</option>
                    <option value="3" ${review.rating == 3 ? 'selected' : ''}> ★★★☆☆</option>
                    <option value="4" ${review.rating == 4 ? 'selected' : ''}> ★★★★☆</option>
                    <option value="5" ${review.rating == 5 ? 'selected' : ''}> ★★★★★</option>
                </select>
            </div>

            <div>
                <label for="reviewContent">내용:</label>
                <textarea id="reviewContent" name="reviewContent" required>${review.reviewContent}</textarea>
            </div>
            
            <div id="detail-btn-group">
                <button type="submit" class="btn btn-edit">수정하기</button>
                <button type="button" class="btn btn-back" onclick="location.href='${pageContext.request.contextPath}/board/review.no'">목록으로</button>
            </div>
        </form>
    </div>
</div>

<%@ include file="../main/footer.jsp"%>

<script>
function validateForm() {
    const rating = document.getElementById("reviewRating").value;
    if (!rating) {
        alert("평점을 선택하세요.");
        return false; // 폼 제출을 막음
    }
    return true; // 폼 제출 진행
}
</script>
</body>
</html>