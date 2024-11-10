<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<section>
        <div class="priceGraph product">
       		<div class="priceGraphTop">
	            <h4>농산물 가격동향</h4>
	            <div class="selectItem">
	            	<select>
	            		<option>종류</option>
	            	</select>
	            	<select>
	            		<option>소분류</option>
	            	</select>
	            </div>
       		</div>
        	<div class="content">
		        <div class="infoSection">
		            <div class="priceImage"></div>
		            <div class="title">사과 (상)</div>
		            <div class="date">2024-10-31</div>
		            <div class="price">65,687원</div>
		            <div class="unit">10kg 상자</div>
		        </div>
		        <div class="comparisonSection">
		            <div class="comparisonItem">
		                <div class="label">전일 평균 가격</div>
		                <div class="date">2024-10-30</div>
		                <div class="price">53,600원</div>
		                <div class="unit">10kg 상자</div>
		            </div>
		            <div class="comparisonItem">
		                <div class="label">전년 동일대비</div>
		                <div class="date">2023-10-31</div>
		                <div class="price">49,649원</div>
		                <div class="unit">10kg 상자</div>
		            </div>
		        </div>
		        <div class="graphSection">
		            그래프
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