<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 등록</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/review_write.css">
</head>
<body>
<%@ include file="../main/header.jsp"%>

<div class="layout">
    <h3>상품후기</h3>
<%@ include file="../board/topmenu.jsp" %>
    <div class="board-container">
        <form name="frmBoardWrite" action="writeProcess.do" method="post" onsubmit="return validateForm()">

            <!-- 작성자 (사용자가 직접 입력) -->
            <div class="form-group">
                <label for="author">이름</label>
                <input type="text" id="author" name="author" placeholder="이름을 입력하세요" required>
            </div>

            <!-- 제목 -->
            <div class="form-group">
                <label for="review_title">제목</label>
                <input type="text" id="reviewTitle" name="reviewTitle" placeholder="제목을 입력하세요" required>
            </div>
            
            <!-- 별점 선택 -->
            <div class="form-group">
                <label for="reviewRating">평점</label>
                <select id="reviewRating" name="Rating" required>
                    <option value="">평점을 선택하세요</option>
                    <option value="1">★☆☆☆☆</option>
                    <option value="2">★★☆☆☆</option>
                    <option value="3">★★★☆☆</option>
                    <option value="4">★★★★☆</option>
                    <option value="5">★★★★★</option>
                </select>
            </div>

            <!-- 내용 -->
            <div class="form-group">
                <label for="review_content">내용</label>
                <textarea id="reviewContent" name="reviewContent" rows="10" placeholder="내용을 입력하세요" required></textarea>
            </div>

            <!-- 제출 버튼 -->
            <div class="form-group button-group">
                <input type="submit" value="등록하기" class="btn">
                <input type="reset" value="다시입력" class="btn">
                <input type="button" value="목록보기" onclick="location.href='${pageContext.request.contextPath}/board/review.no'" class="btn">
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