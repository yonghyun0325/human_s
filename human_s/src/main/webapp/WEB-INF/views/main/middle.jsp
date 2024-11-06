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
	            	<button>양파</button>
			        <button>토마토</button>
			        <button class="active">사과</button>
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
            <div>
	           	<button class="newPrev"><i class="fas fa-angle-left"></i></button>
	            <div class="newContainer container">
	            	<div class="newList list">
	<c:forEach var="i" begin="0" end="5">
		                <div class="newItem item">
		                	<div>산지 직송 햇사과</div>
						</div>
	</c:forEach>
	            	</div>
	            </div>
	           	<button class="newNext"><i class="fas fa-angle-right"></i></button>
           	</div>
        </div>
        <div class="areaProduct product">
            <h4>지역 특산물</h4>
            <div>
	           	<button class="areaPrev"><i class="fas fa-angle-left"></i></button>
	            <div class="areaContainer container">
	            	<div class="areaList list">
	<c:forEach var="i" begin="0" end="5">
		                <div class="areaItem item">
		                	<div>산지 직송 햇사과</div>
						</div>
	</c:forEach>
	            	</div>
	            </div>
	           	<button class="areaNext"><i class="fas fa-angle-right"></i></button>
           	</div>
        </div>
        <div class="farmStoryProduct product">
            <h4>농부들 이야기</h4>
            <div>
	            <div class="fStoryContainer">
	            	<div class="fStoryList">
	<c:forEach var="i" begin="0" end="4">
		                <div class="fStoryItem" data-fSNum="${ i }">
		                	<img alt="" src="">
		                	<div>
		                		<img alt="" src="">
		                		<img alt="" src="">
		                	</div>
		                	<div>이번달 농사가 어떻게 잘 됐고...</div>
						</div>
	</c:forEach>
	            	</div>
	            </div>
	            <div class="fStroyNum">
	            	<i class="fa-solid fa-circle"></i>
	            	<i class="fa-solid fa-circle"></i>
	            	<i class="fa-solid fa-circle"></i>
	            	<i class="fa-solid fa-circle"></i>
	            	<i class="fa-solid fa-circle"></i>
	            </div>
           	</div>
        </div>
    </section>
</body>
</html>