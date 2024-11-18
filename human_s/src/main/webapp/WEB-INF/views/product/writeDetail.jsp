<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GreenCart</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/product.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-datalabels@2.0.0"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/productWrite.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<section>
	    <h3>판매 상품 등록</h3>
	    <div class="productContainer">
	        <form name="frmProductWrite" action="writeProduct.do" method="post" enctype="multipart/form-data">
	            <div class="title">
	                <label for="pdtTitle">제목</label>
	                <input type="text" id="pdtTitle" name="pdtTitle" required>
	            </div>
	            <div class="writer">
	                <label for="pdtWriter">작성자</label>
	                <input type="text" id="pdtWriter" name="pdtWriter" value="${ user.userNick }" readonly>
	                <input type="hidden" name="userIdx" value="${ user.userIdx }">
	            </div>
	            <div class="area">
	            	<div>
		            	<label for="pdtArea">지역</label>
		                <select id="pdtArea" name="pdtArea">
		<c:forEach var="item" items="${ areaList }">
		                    <option class="areaName" value="${ item[0] }">${ item[0] }</option>
		</c:forEach>
		                </select>
	            	</div>
	            	<div>
		                <label for="pdtArea2">상세지역</label>
		                <select id="pdtArea2" name="pdtArea2">
		<c:forEach var="item" items="${ area2List }">
		                    <option class="area2Name" value="${ item[1] }" data-area="${ item[0] }">${ item[1] }</option>
		</c:forEach>
		                </select>
	            	</div>
	            </div>
	            <div class="code">
	            	<div>
		            	<label for="pdtLargeCode">대분류코드</label>
		                <select id="pdtLargeCode" name="pdtLargeCode">
		<c:forEach var="item" items="${ largeList }">
		                    <option class="largeCode" value="${ item[0] }">${ item[1] }</option>
		</c:forEach>
		                </select>
	            	</div>
	            	<div>
		                <label for="pdtMidCode">중분류코드</label>
		                <select id="pdtMidCode" name="pdtMidCode">
		<c:forEach var="item" items="${ midList }">
		                    <option class="midCode" value="${ item[1] }" data-large="${ item[0] }">${ item[2] }</option>
		</c:forEach>
		                </select>
	            	</div>
	            	<div>
		                <label for="pdtSmallCode">소분류코드</label>
		                <select id="pdtSmallCode" name="pdtSmallCode">
		<c:forEach var="item" items="${ smallList }">
		                    <option class="smallCode" value="${ item[2] }" data-large="${ item[0] }"  data-mid="${ item[1] }">
		                    	${ item[3] }</option>
		</c:forEach>
		                </select>
	            	</div>
	            </div>
	            <div class="price">
	            	<div>
	            		<div>
		            		동일 상품 금일 도매 금액 
		            		<select class="graphStd"></select>
	            		</div>
	            		<div>
		                	<canvas id="myChart" style="height: 35vh; width: 40vw;"></canvas>
	            		</div>
	            	</div>
		            <div>
		                <label for="pdtPrice">판매금액<br>
		                <input type="text" id="pdtPrice" name="pdtPrice" required> 원</label>
		            </div>
	            </div>
	            <div class="img">
	                <label>썸네일이미지</label>
	                <input type="file" name="pdtFile" class="pdtFile" accept="image/*" required>
	            </div>
	            <div class="content">
	                <label>내용은 이미지 파일로 등록해주세요. (최대 4개, 5MB 이하)<br></label>
	                <input type="file" name="uploadFiles" class="uploadFiles" accept="image/*" multiple>
	                <div id="fileNames"></div>
	            </div>
	            <div class="btnDiv">
	                <input type="submit" value="등록하기">
	                <input type="reset" value="다시입력">
	                <input type="button" value="목록보기" onclick="location.href='/hms/index.no'">
	            </div>
	        </form>
	    </div>
	</section>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>
