<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <title>공지사항</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/notice.css">
</head>
<body>

<%@ include file="../main/header.jsp"%>
<div class="layout">
<h3>공지사항</h3>
<%@ include file="../board/topmenu.jsp" %>
  <div class="board-container">

    <div class="bbs-sch">
    <div class="header">
        <p class="total-posts">총 게시물 수: ${paging.totalCount}</p>
        <div class="search-bar">
            <%-- <form action="${pageContext.request.contextPath}/board/search.do" method="get"> --%>
            <form>
                <!-- searchType 라디오 버튼을 form 안에 넣음 -->
                <div class="check-box">
                    <label><input type="radio" name="searchType" value="name" ${param.searchType == null || param.searchType == 'name' ? 'checked' : ''}> 이름</label>
                    <label><input type="radio" name="searchType" value="title" ${param.searchType == 'title' ? 'checked' : ''}> 제목</label>
                    <label><input type="radio" name="searchType" value="content" ${param.searchType == 'content' ? 'checked' : ''}> 내용</label>
                </div>

                <!-- 검색어 입력 필드 -->
                <div class="search-box">
                    <input type="text" name="search" placeholder="검색어를 입력하세요" value="${fn:escapeXml(param.search)}">
                    <button type="submit">검색</button>
                </div>
            </form>
        </div>
    </div>
</div>


    <table class="board-table">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회</th>
        </tr>
        
        <c:choose>
        <c:when test="${empty noticeList}">
            <tr>
                <td colspan="5">등록된 게시물이 없습니다</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="notice" items="${noticeList}" varStatus="vs">
                <tr>
                    <td>${notice.id}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/board/notice_view.do?noticeId=${notice.id}">
                            ${notice.noticeTitle}
                        </a>
                    </td>
                    <td>${notice.author}</td>
                    <td>
                        <fmt:formatDate value="${notice.createdDate}" type="date" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>${notice.views}</td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </table>
    <div class= bottom-box>
<div class="pagination">
    <c:if test="${paging.currentPage > 1}">
        <a href="?page=${paging.currentPage - 1}&searchType=${param.searchType}&search=${param.search}">&laquo; </a>
    </c:if>
    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="pageNum">
       <a href="?page=${pageNum}&searchType=${param.searchType}&search=${param.search}" class="${pageNum == paging.currentPage ? 'active' : ''}">
    ${pageNum}
		</a>
    </c:forEach>
    <c:if test="${paging.currentPage < paging.totalPages}">
        <a href="?page=${paging.currentPage + 1}&searchType=${param.searchType}&search=${param.search}"> &raquo;</a>
    </c:if>
    </div>
 <div class="write-box">
             <%--  <c:if test="${not empty member}">
                <tr>
                    <td colspan="5"> --%>
                        <a href="${pageContext.request.contextPath}/board/write.do">
                            <button class="notice_write_btn" type="button" id="write_btn">글등록</button> 
                        </a>
<%--                     </td>

                </tr>

             </c:if> --%>
 
 
 </div>
 
 
 
 
</div>
</div>
</div>


<%@ include file="../main/footer.jsp"%>

</body>
<script>
/* $(document).ready(function() {
    // 검색 폼 제출 전에 searchType이 없으면 기본값을 'name'으로 설정
    $('form').submit(function(event) {
        event.preventDefault();  // 폼이 제출되는 것을 방지

        let searchType = $('input[name="searchType"]:checked').val() || 'name'; // 기본값 'name' 설정
        let search = $('input[name="search"]').val() || ''; // 기본값 빈 문자열 설정

        $.ajax({
            url: '/board/search.do',  // 서버로 요청 보냄
            method: 'GET',
            data: {
                searchType: searchType,
                search: search
            },
            success: function(response) {
                // 서버에서 받은 데이터로 테이블 업데이트
                updateSearchResults(response);
            },
            error: function(xhr, status, error) {
                console.log("Error: " + error);
            }
        });
    });

    // 검색 결과 업데이트 함수
    function updateSearchResults(data) {
        // 응답 데이터를 처리하여 noticeList 테이블 갱신
        const noticeList = data.noticeList; // 서버에서 응답받은 noticeList를 처리합니다
        let html = '';
        if (noticeList && noticeList.length > 0) {
            noticeList.forEach(function(notice) {
                html += `<tr>
                            <td>${notice.id}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/board/notice_view.do?noticeId=${notice.id}">
                                    ${notice.noticeTitle}
                                </a>
                            </td>
                            <td>${notice.author}</td>
                            <td>
                                <fmt:formatDate value="${notice.createdDate}" type="date" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>${notice.views}</td>
                          </tr>`;
            });
        } else {
            html = '<tr><td colspan="5">등록된 게시물이 없습니다</td></tr>';
        }
        // 테이블 갱신
        $(".board-table tbody").html(html);
    }
});

 */
</script>
</html>
