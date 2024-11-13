<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
	<section>
        <div class="priceGraph product">
       		<div class="priceGraphTop">
	            <h4>농산물 가격동향</h4>
	            <div class="selectItem">
	            	<select class="itemCodeSelect">
	<c:set var="itemCode" value="" />
	<c:forEach var="item" items="${ dailyPriceList }">
	    <c:if test="${ itemCode != item.item_code }">
	        			<option class="itemCode" value="${ item.item_code }">${ item.item_name }</option>
	        <c:set var="itemCode" value="${ item.item_code }" />
	    </c:if>
	</c:forEach>
	            	</select>
	            	<select class="kindCodeSelect">
	<c:set var="kindCode" value="" />
	<c:forEach var="item" items="${ dailyPriceList }">
	    <c:set var="currentCode">
		    <c:out value="${ item.item_code }" />,<c:out value="${ item.kind_code }" />
		</c:set>
	    <c:if test="${ kindCode != currentCode }">
	        <option class="kindCode" value="${ item.kind_code }" data-itemcode="${ item.item_code }">
	            ${ item.kind_name }
	        </option>
	        <c:set var="kindCode" value="${ currentCode }" />
	    </c:if>
	</c:forEach>
	            	</select>
	            	<select class="rankCodeSelect">
	<c:set var="rankCode" value="" />
	<c:forEach var="item" items="${ dailyPriceList }">
		<c:set var="currentCode">
		    <c:out value="${ item.item_code }" />,<c:out value="${ item.kind_code }" />,<c:out value="${ item.rank_code }" />
		</c:set>
	    <c:if test="${ rankCode != currentCode }">
	        			<option class="rankCode" value="${ item.rank_code }" data-itemcode="${ item.item_code }"
	        				data-kindcode="${ item.kind_code }"> ${ item.rank }</option>
	        <c:set var="rankCode" value="${ currentCode }" />
	    </c:if>
	</c:forEach>
	            	</select>
	            </div>
       		</div>
        	<div class="content">
		        <div class="infoSection">
		            <div class="priceImage"></div>
		            <div class="title"></div>
		            <div class="date"></div>
		            <div class="price"></div>
		            <div class="unit"></div>
		        </div>
		        <div class="comparisonSection">
		            <div class="comparisonItem">
		                <div class="label">전일 평균 가격</div>
		                <div class="date"></div>
		                <div class="price"></div>
		                <div class="unit"></div>
		            </div>
		            <div class="comparisonItem">
		                <div class="label">전년 동일대비</div>
		                <div class="date"></div>
		                <div class="price"></div>
		                <div class="unit"></div>
		            </div>
		        </div>
		        <div class="graphSection">
			        <canvas id="myChart" style="height: 35vh; width: 45vw;"></canvas>
		        </div>
	        </div>
        </div>
        <div class="bestProduct product">
            <h4>Best 상품</h4>
            <div class="bestList">
	<c:forEach var="i" begin="0" end="7">
                <div class="bestItem">
                    <div>top ${ i+1 }</div>
                    <div>산지 직송 햇사과</div>
                </div>
	</c:forEach>
            </div>
        </div>
        <div class="newProduct product">
            <h4>따끈따끈한 신상</h4>
            <div class="newContainer container">
            	<div class="newList list">
	<c:forEach var="i" begin="0" end="5">
	                <div class="newItem item">
	                	<div>산지 직송 햇사과 ${ i }</div>
					</div>
	</c:forEach>
            	</div>
            </div>
        </div>
        <div class="areaProduct product">
            <h4>지역 특산물</h4>
            <div class="areaContainer container">
            	<div class="areaList list">
	<c:forEach var="i" begin="0" end="5">
	                <div class="areaItem item">
	                	<div>산지 직송 햇사과 ${ i }</div>
					</div>
	</c:forEach>
            	</div>
            </div>
        </div>
        <div class="farmAndNotice">
	        <div class="farmStoryBox box">
	            <h4>농부들 이야기</h4>
	            <div>
	            	<hr>
	<c:forEach var="i" begin="0" end="5">
					<div>
						<div>title</div>
						<div>writer</div>
					</div>
					<hr>
	</c:forEach>
	            </div>
	        </div>
	        <div class="NoticeBox box">
	            <h4>공지사항</h4>
	            <div>
	            	<hr>
	<c:forEach var="i" begin="0" end="5">
					<div>
						<div>title</div>
						<div>date</div>
					</div>
					<hr>
	</c:forEach>
	            </div>
	        </div>
        </div>
    </section>
    
    <script>
    	//따끈따끈한 신상, 지역특산물 무한스크롤
    	//(header가 모든 페이지에 붙어있기 때문에 main.js에 있으면 다른 페이지에는 없는 클래스 때문에 콘솔에 오류가 계속해서 찍힘)
    	$(function(){
    		
    		// 스크롤 속도 (밀리초 단위)
    		const scrollSpeed = 2; // 2px마다 스크롤
    		const scrollThreshold = 230;
    		
    		// 무한 스크롤을 위한 변수
    		const areaContainer = $(".areaContainer").get(0);
    		const areaList = $(".areaList").get(0);
    		const newContainer = $(".newContainer").get(0);
    		const newList = $(".newList").get(0);
    		
    		// 한 번 스크롤한 거리
    		let scrollDistance = 0;
    		
    		// 무한 스크롤 실행 함수
    		function infiniteScroll(container, list) {
    	    	container.scrollLeft += scrollSpeed;
    	    
    		    // 스크롤이 지정된 거리(scrollThreshold) 이상 이동하면 첫 번째 아이템을 맨 뒤로 보냄
    		    if (container.scrollLeft >= scrollThreshold) {
    		        // 첫 번째 아이템을 맨 뒤로 보내기
    		        const firstItem = list.firstElementChild;
    		        list.appendChild(firstItem); // 첫 번째 아이템을 맨 뒤로 이동시킴
    		        
    		        // 스크롤을 230px만큼 이동시킨 후, 다시 스크롤 위치를 맞춤
    		        container.scrollLeft -= scrollThreshold; // 230px 만큼 돌려서 스크롤 계속 진행
    		    }
    		}
    		
    		// 무한 스크롤 시작
    		setInterval(function() {
    		    infiniteScroll(newContainer, newList);
    		    infiniteScroll(areaContainer, areaList);
    		}, 50);
    		
    	});
    </script>
</body>
</html>