<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글 등록</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/story_write.css">
</head>
<body>
    <%@ include file="../main/header.jsp" %>

    <div class="layout">
        <h1>글 등록</h1>
        <form action="${pageContext.request.contextPath}/story/create" method="post" enctype="multipart/form-data" id="postForm">
            <!-- 제목 입력 -->
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" class="form-control" placeholder="제목을 입력하세요" required>
            </div>

            <!-- 내용 입력 -->
            <div class="form-group">
                <label for="content">내용</label>
                <textarea id="content" name="content" class="form-control" rows="5" placeholder="내용을 입력하세요" required></textarea>
            </div>

            <!-- 태그할 상품 선택 -->
            <div class="form-group">
                <label for="product">태그할 상품</label>
                <select id="product" name="productId" class="form-control" >
                    <option value="">상품을 선택하세요</option> 
                    <c:forEach var="product" items="${products}">
                        <option value="${product.pdtIdx}">${product.pdtTitle}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- 파일 업로드 -->
            <div class="form-group">
                <label for="image">이미지 업로드</label>
                <input type="file" id="image" name="image" class="form-control" accept="image/*">
            </div>

            <!-- 등록 버튼 -->
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">등록</button>
                <button type="reset" class="btn btn-secondary">초기화</button>
                <input type="button" value="목록보기" onclick="location.href='${pageContext.request.contextPath}/story/farmstory.no'" class="btn">
            </div>
        </form>
    </div>

    <%@ include file="../main/footer.jsp" %>
</body>
</html>