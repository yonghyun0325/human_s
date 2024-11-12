<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>
    <div id="wrap">
        <h2>상세보기</h2>
        <div id="container">
            <div>작성자: ${boardVO.gbWriter}</div>
            <div>제목: ${boardVO.gbTitle}</div>
            <div>내용: ${boardVO.gbContent}</div>
            <div>조회수: ${boardVO.readCnt}</div>
            <div>작성일: <fmt:formatDate value="${boardVO.postDate}" type="date"
                                    pattern="yyyy-MM-dd hh:mm:ss" /></div>
            <div id="div-bundle-btn">
                <!-- 조건: 회원 + 자신이 쓴 게시글 -->
                <c:if test="${(not empty member) and (member.memIdx eq boardVO.memberEntity.memIdx)}">
                    <input type="button" value="수정하기" onclick="location.href='update.do?gbIdx=${boardVO.gbIdx}'">
                    <input type="button" value="삭제하기" onclick="deletePost()">
                </c:if>
                
                <input type="button" value="목록보기" onclick="location.href='${pageContext.request.contextPath}/index.do'">
            </div>
        </div>

    </div>
    
<script>
	function deletePost(){
		const ans = confirm("정말 삭제하겠습니까?");
		
		if(ans){
			location.href="deleteProcess.do?gbIdx=${boardVO.gbIdx}";
		}
	}


</script>    
    
    
    
</body>
</html>