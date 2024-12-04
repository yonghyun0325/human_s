<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GreenCart</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/manager.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp"%>
    
    <h1></h1>
    <!-- 마이페이지 컨테이너 -->
    <div class="managerContainer">
        <!-- 좌측 사이드바 -->
        <div class="sidebar">
        	<h3>관리정보</h3>
            <ul>
                <li><a href="/hms/manager/manager.do?select=user">회원관리</a></li>
                <li><a href="/hms/manager/manager.do?select=seller">판매자관리</a></li>
                <li><a href="/hms/manager/manager.do?select=coupon">쿠폰 전송</a></li>
                <li><a href="/hms/manager/manager.do?select=api">API 관리</a></li>
            </ul>
        </div>

        <div class="main-content">
            <div class="user content">
            	<table>
						<tr>
							<th>회원 번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>전화번호</th>
							<th>등급</th>
							<th>삭제</th>
						</tr>
		<c:if test="${ not empty userList }">
			<c:forEach var="item" items="${ userList }">
						<tr>
							<td>${ item.userIdx }</td>
							<td>${ item.userEmail }</td>
							<td>${ item.userName }</td>
							<td>${ item.userPhone }</td>
							<td>${ item.grade }</td>
							<td>
								<i class="fa-solid fa-trash-can deleteMember" data-id="${ item.userIdx }"></i>
							</td>
						</tr>
			</c:forEach>
		</c:if>
					</table>
            </div>
            <div class="seller content">
            	<table>
						<tr>
							<th>판매자 번호</th>
							<th>아이디</th>
							<th>이름</th>
							<th>전화번호</th>
							<th>등급</th>
							<th>삭제</th>
						</tr>
		<c:if test="${ not empty userList }">
			<c:forEach var="item" items="${ sellerList }">
						<tr>
							<td>${ item.userIdx }</td>
							<td>${ item.userEmail }</td>
							<td>${ item.userName }</td>
							<td>${ item.userPhone }</td>
							<td>${ item.grade }</td>
							<td>
								<i class="fa-solid fa-trash-can deleteMember" data-id="${ item.userIdx }"></i>
							</td>
						</tr>
			</c:forEach>
		</c:if>
					</table>
            </div>
            <div class="coupon content">
            	쿠폰
            </div>
            <div class="api content">
	<c:if test="${ not empty msg }">
				<h5>${ msg }</h5>
	</c:if>
            	<table>
            		<tr>
            			<th rowspan="2">도매시장 통합홈페이지</th>
            			<td colspan="2">도매시장 코드 저장</td>
            			<td><a href="/hms/manager/codeWhsal.do">저장하기</a></td>
            		</tr>
            		<tr>
            			<td colspan="2">실시간 경락 정보 저장</td>
            			<td><a href="/hms/manager/priceReal.do">저장하기</a></td>
            		</tr>
            		<tr>
            			<th>KAMIS</th>
            			<td>일별 부류별 도.소매가격정보</td>
            			<td><input type="date" class="regDate"></td>
            			<td><button class="dailyPriceBtn">저장하기</button></td>
            		</tr>
            		<tr>
            			<th>농사로</th>
            			<td colspan="2">지역특산물</td>
            			<td><a href="/hms/manager/areaProduct.do">저장하기</a></td>
            		</tr>
            	</table>
            </div>
        </div>
    </div>

    <%@ include file ="/WEB-INF/views/main/footer.jsp" %>
    <script>
    	$(function(){
    		//주소창에서 name이 들어간 뒤 부분을 추출해냄
    		function getParameterByName(name) {
    	        let url = window.location.href;
    	        name = name.replace(/[\[\]]/g, '\\$&');
    	        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
    	            results = regex.exec(url);
    	        if (!results) return null;
    	        if (!results[2]) return '';
    	        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    	    }
    		
    		let select = getParameterByName('select');
   			$(".main-content > .content").hide();
   			if (select) {
   		        $(".main-content > ." + select).show();
   		    } else {
   		        $(".main-content > .user").show();
   		    }
   			
   			switch(select){
   				case 'user': $("h1").text("회원관리"); break;
   				case 'seller': $("h1").text("판매자관리"); break;
   				case 'coupon': $("h1").text("쿠폰전송"); break;
   				case 'api': $("h1").text("api 관리"); break;
   			}
   			
   			//일별 부류별 가격정보 api 조회 시 필요한 날짜 입력
   			$(".dailyPriceBtn").click(function(){
   				let regDate = $(".regDate").val();
   				
   				if(!regDate){
   					alert("날짜를 선택해 주세요.");
   				}else{
	   				location.href="/hms/manager/dailyPrice.do?regDate="+regDate;   					
   				}
   			});
    		
    	});
    </script>
</body>
</html>