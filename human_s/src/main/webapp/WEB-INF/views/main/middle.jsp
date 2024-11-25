<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0"></script>
<script src="${pageContext.request.contextPath}/resources/js/middle.js"></script>
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
        		<div>
			        <div class="infoSection">
			            <div class="priceImage"></div>
			            <div class="title"></div>
			            <div class="date"></div>
			            <div class="price"></div>
			            <div class="unit"></div>
			            <div class="priceUnit"></div>
			        </div>
			        <div class="comparisonSection">
			            <div class="comparisonItem">
			                <div class="label">전일 평균 가격</div>
			                <div class="date"></div>
			                <div class="price"></div>
			                <div class="unit"></div>
			                <div class="priceUnit"></div>
			            </div>
			            <div class="comparisonItem">
			                <div class="label">전년 동일대비</div>
			                <div class="date"></div>
			                <div class="price"></div>
			                <div class="unit"></div>
			                <div class="priceUnit"></div>
			            </div>
			        </div>
        		</div>
		        <div class="graphSection">
			        <canvas id="myChart" style="height: 35vh; width: 45vw; max-width:580px;"></canvas>
		        </div>
	        </div>
        </div>
        <div class="bestProduct product">
            <h4>Best 상품</h4>
            <div class="bestList">
	<c:forEach var="i" begin="0" end="7">
                <div class="bestItem" data-idx="${ popList[i].pdtIdx }">
                    <div class="productRow">${ i+1 }위</div>
					<div class="popNum">
		<c:if test="${ not empty popList[i].img }">
						<img src="${ popList[i].img }" alt="${ popList[i].pdtTitle }">
		</c:if>
		<c:if test="${ empty popList[i].img }">
						<img src="${pageContext.request.contextPath}/resources/uploads/${popList[i].pdtSave}" alt="${ popList[i].pdtOrigin }">
		</c:if>
					</div>
					<div class="productContent">
						<div class="title">${ popList[i].pdtTitle }</div>
						<div class="price">(100g당 ${ popList[i].pdtGPrice }원)<span>${ popList[i].pdtPrice } 원</span></div>
						<div class="company">${ popList[i].pdtWriter }</div>
					</div>
                </div>
	</c:forEach>
            </div>
        </div>
        <div class="newProduct product">
            <h4>따끈따끈한 신상</h4>
            <div class="newContainer container">
            	<div class="newList list">
	<c:forEach var="i" begin="0" end="5">
					<a href="/hms/product/viewDetail.no?idx=${ newList[i].pdtIdx }">
		<c:choose>
			<c:when test="${ not empty newList[i].img }">
	                <div class="newItem item" style="background-image: url('${ newList[i].img }');">
	                    <div>${ newList[i].pdtTitle }</div>
	                    <div class="price">(100g당 ${ newList[i].pdtGPrice }원) <span>${ newList[i].pdtPrice } 원</span></div>
	                </div>
			</c:when>
			<c:otherwise>
					<div class="newItem item" style="background-image: url('${pageContext.request.contextPath}/resources/uploads/${newList[i].pdtSave}');">
	                    <div>${ newList[i].pdtTitle }</div>
	                    <div class="price">(100g당 ${ newList[i].pdtGPrice }원) <span>${ newList[i].pdtPrice } 원</span></div>
	                </div>
			</c:otherwise>
		</c:choose>
					</a>
	</c:forEach>
            	</div>
            </div>
        </div>
        <div class="areaProduct product">
            <h4>지역 특산물</h4>
            <div class="areaContainer container">
            	<div class="areaList list">
    <c:forEach var="i" begin="0" end="${ areaList.size()-1 }">
					<a href="/hms/product/viewDetail.no?idx=${ areaList[i].pdtIdx }">
		<c:choose>
			<c:when test="${ not empty areaList[i].img }">
	                <div class="areaItem item" style="background-image: url('${ areaList[i].img }');">
	                	<div>${ areaList[i].pdtArea }</div>
	                	<div class="areaBox">
		                    <div>${ areaList[i].pdtTitle }</div>
		                    <div class="price">(100g당 ${ areaList[i].pdtGPrice }원) <span>${ areaList[i].pdtPrice } 원</span></div>
	                	</div>
	                </div>
			</c:when>
			<c:otherwise>
					<div class="areaItem item" style="background-image: url('${pageContext.request.contextPath}/resources/uploads/${areaList[i].pdtSave}');">
	                    <div>${ areaList[i].pdtArea }</div>
	                    <div class="areaBox">
		                    <div>${ areaList[i].pdtTitle }</div>
		                    <div class="price">(100g당 ${ areaList[i].pdtGPrice }원) <span>${ areaList[i].pdtPrice } 원</span></div>
	                	</div>
	                </div>
			</c:otherwise>
		</c:choose>
					</a>
	</c:forEach>
            	</div>
            </div>
        </div>
        <div class="farmAndNotice">
	        <div class="farmStoryBox box">
	            <h4>팜스토리</h4>
	            <div>
	            	<hr>
	<c:forEach var="i" begin="0" end="5">
					<div class="storyItem" data-id="${ storyList[i].id }">
						<div>${ storyList[i].storyTitle }</div>
						<div>
							<fmt:formatDate value="${storyList[i].createdDate}" type="date" pattern="yyyy-MM-dd"/>
						</div>
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
					<div class="noticeItem" data-id="${ noticeList[i].id }">
						<div>${ noticeList[i].noticeTitle }</div>
						<div>
							<fmt:formatDate value="${noticeList[i].createdDate}" type="date" pattern="yyyy-MM-dd"/>
						</div>
					</div>
					<hr>
	</c:forEach>
	            </div>
	        </div>
        </div>
    </section>

	<script>
		const contextPath = '${pageContext.request.contextPath}';
	</script>
</body>
</html>