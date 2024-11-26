<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GreenCart</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/product.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/product.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<section>
	<c:if test="${ (product.userEntity.userIdx == user.userIdx) or user.grade == 3}">
		<div class="updateDetail">
		<c:if test="${ product.userEntity.userIdx == user.userIdx }">
			<a href="/hms/product/updateDetail.do?idx=${ product.pdtIdx }">수정</a>
		</c:if>
			<button value="${ product.pdtIdx }">삭제</button>
		</div>
	</c:if>
		<div class="productAll">
			<div class="productImage">
	<c:choose>
		<c:when test="${ not empty product.img }">
				<img src="${ product.img }" alt="${ product.pdtTitle }">
		</c:when>
		<c:otherwise>
				<img src="${pageContext.request.contextPath}/resources/uploads/${product.pdtSave}" alt="${ product.pdtOrigin }">
		</c:otherwise>
	</c:choose>
			</div>
			<div class="productInfo">
				<h2>${ product.pdtTitle }</h2>
				<hr>
				<div class="productPrice">
					<div>판매가격</div>
					<h2>${ product.pdtPrice }원<span>(100g당 ${ product.pdtGPrice }원)</span></h2>
					
				</div>
				<hr>
				<div class="productDetail">
					<div>
						<p>상품코드</p>
						<p>${ product.pdtIdx }</p>
					</div>
					<div>
						<p>원산지</p>
						<p>${ product.pdtArea } ${ product.pdtArea2 }</p>
					</div>
					<div>
						<p>배송비</p>
						<p>배송조건 (택배)</p>
					</div>
					<div>
						<p>지역별 배송비</p>
						<p>아래 지역에 배송비가 추가됩니다<br>제주,울릉군,백령면:5,000원</p>
					</div>
					<div>
						<p>제조원</p>
						<p>${ product.userEntity.userNick }</p>
					</div>
					<div>
						<p>옵션</p>
						<select>
							<option value="${ product.pdtKg }">${ product.pdtKg }kg</option>
						</select>
					</div>
					<div>
						<p>수량</p>
						<input type="number" name="product_qty" class="productQty" value="0"> 개
					</div>
				</div>
				<div class="totalPrice">
					<p>총 상품 금액</p>
					<p><span>${ product.pdtPrice }</span>원</p>
				</div>
				<div class="buttons">
	<c:choose>
		<c:when test="${ not empty user }">
					<button class="btn wishlist" value="wishlist">찜하기</button>
					<button class="btn cart" value="cart">장바구니</button>		
		</c:when>
		<c:otherwise>
					<button class="btn wishlist" value="wishlistNo">찜하기</button>
					<button class="btn cart" value="cartNo">장바구니</button>
		</c:otherwise>
	</c:choose>
					<button class="btn buy" value="buy">바로구매</button>
				</div>
			</div>
		</div>
		<div class="detailAll">
			<div class="detailList">
				<div class="detailImgs">
					<div class="detailNav">
						<button value="detailImgs" class="selected">상품상세정보</button>
						<button value="shopInfo">배송/교환/환불</button>
						<button value="productQuestion">상품문의</button>
						<button value="productComment">상품후기</button>
					</div>
					<div class="imgList">
	<c:choose>
		<c:when test="${ not empty product.attachedList }">
			<c:forEach var="i" begin="0" end="${ product.attachedList.size()-1 }">
						<img src="${pageContext.request.contextPath}/resources/uploads/${ product.attachedList[i].piSave }" alt="${ i }">
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div>상세 정보가 없습니다.</div>
		</c:otherwise>
	</c:choose>
					</div>
				</div>
				<div class="shopInfo">
					<div class="detailNav">
						<button value="detailImgs">상품상세정보</button>
						<button value="shopInfo" class="selected">배송/교환/환불</button>
						<button value="productQuestion">상품문의</button>
						<button value="productComment">상품후기</button>
					</div>
					<div class="shopComment">
						<h2>배송/교환/환불</h2>
						<jsp:include page="shiExcRef.jsp" />
					</div>
				</div>
				<div class="productQuestion">
					<div class="detailNav">
						<button value="detailImgs">상품상세정보</button>
						<button value="shopInfo">배송/교환/환불</button>
						<button value="productQuestion" class="selected">상품문의</button>
						<button value="productComment">상품후기</button>
					</div>
					<div class="shopComment">
						<h2>상품문의</h2>
						<table class="questionTable">
							<thead>
						        <tr>
						            <th class="qNumber">번호</th>
						            <th class="qTitle">제목</th>
						            <th class="qWriter">작성자</th>
						            <th class="qDate">작성일</th>
						            <th class="qNumber">조회</th>
					        	</tr>
					        </thead>
					        <tbody>
	<c:if test="${ not empty pinquiryList }">
		<c:forEach var="item" items="${ pinquiryList }" varStatus="i">
					        	<tr>
						            <td class="cNumber">${ i.index+1 }</td>
						            <td class="cTitle">${ item.reviewTitle }</td>
						            <td class="cWriter">${ item.author }</td>
						            <td class="cDate">${ item.createdDate }</td>
						            <td class="cStar">⭐⭐⭐⭐</td>
						            <td class="cNumber">${ item.views }</td>
						        </tr>
		</c:forEach>
	</c:if>		  
	<c:if test="${ empty pinquiryList }">
						        <tr>
						        	<td colspan="6">등록된 문의가 없습니다.</td>
						        </tr>
	</c:if>    
					        </tbody>
						</table>
						<!-- 페이지네이션 -->
						<div>
						    <div class="pagination">
						        <a href="#">&laquo;</a>
						        <a href="#" class="active">1</a>
						        <a href="#">2</a>
						        <a href="#">&raquo;</a>
						    </div>
						    <div>
						    	<a href="/hms/board/pinquiry.no">전체보기</a>
						    	<a href="/hms/board/pinquiry/write.do">문의하기</a>
						    </div>
						</div>
					</div>
				</div>
				<div class="productComment">
					<div class="detailNav">
						<button value="detailImgs">상품상세정보</button>
						<button value="shopInfo">배송/교환/환불</button>
						<button value="productQuestion">상품문의</button>
						<button value="productComment" class="selected">상품후기</button>
					</div>
					<div class="shopComment">
						<h2>상품후기</h2>
						<table class="commentTable">
							<thead>
						        <tr>
						            <th class="cNumber">번호</th>
						            <th class="cTitle">제목</th>
						            <th class="cWriter">작성자</th>
						            <th class="cDate">작성일</th>
						            <th class="cStar">평점</th>
						            <th class="cNumber">조회</th>
					        	</tr>
					        </thead>
					        <tbody>
	<c:if test="${ not empty reviewList }">
		<c:forEach var="item" items="${ reviewList }" varStatus="i">
					        	<tr>
						            <td class="cNumber">${ i.index+1 }</td>
						            <td class="cTitle">${ item.reviewTitle }</td>
						            <td class="cWriter">${ item.author }</td>
						            <td class="cDate">${ item.createdDate }</td>
						            <td class="cStar">⭐⭐⭐⭐</td>
						            <td class="cNumber">${ item.views }</td>
						        </tr>
		</c:forEach>
	</c:if>		  
	<c:if test="${ empty reviewList }">
						        <tr>
						        	<td colspan="6">등록된 후기가 없습니다.</td>
						        </tr>
	</c:if>      
					        </tbody>
						</table>
						<!-- 페이지네이션 -->
						<div>
						    <div class="pagination">
						        <a href="#">&laquo;</a>
						        <a href="#" class="active">1</a>
						        <a href="#">2</a>
						        <a href="#">&raquo;</a>
						    </div>
						    <div>
						    	<a href="/hms/board/review.no">전체보기</a>
						    	<a href="/hms/board/review/write.do">후기쓰기</a>
						    </div>
						</div>
					</div>
				</div>
			</div>
			<div class="sideBar">
				<div class="productInfo">
					<h2>${ product.pdtTitle }</h2>
					<hr>
					<div class="productPrice">
						<div>판매가격</div>
						<h2>${ product.pdtPrice }원<span>(100g당 ${ product.pdtGPrice }원)</span></h2>
						
					</div>
					<hr>
					<div class="productDetail">
						<div>
							<p>상품코드</p>
							<p>${ product.pdtIdx }</p>
						</div>
						<div>
							<p>원산지</p>
							<p>${ product.pdtArea } ${ product.pdtArea2 }</p>
						</div>
						<div>
							<p>배송비</p>
							<p>배송조건 (택배)</p>
						</div>
						<div>
							<p>지역별 배송비</p>
							<p>아래 지역에 배송비가 추가됩니다<br>제주,울릉군,백령면:5,000원</p>
						</div>
						<div>
							<p>제조원</p>
							<p>${ product.userEntity.userNick }</p>
						</div>
						<div>
							<p>옵션</p>
							<select>
								<option value="${ product.pdtKg }">${ product.pdtKg }kg</option>
							</select>
						</div>
						<div>
							<p>수량</p>
							<input type="number" name="product_qty" class="productQty" value="0"> 개
						</div>
					</div>
					<div class="totalPrice">
						<p>총 상품 금액</p>
						<p><span>${ product.pdtPrice }</span>원</p>
					</div>
					<div class="buttons">
						<c:choose>
		<c:when test="${ not empty user }">
						<button class="btn wishlist" value="wishlist">찜하기</button>
						<button class="btn cart" value="cart">장바구니</button>		
		</c:when>
		<c:otherwise>
						<button class="btn wishlist" value="wishlistNo">찜하기</button>
						<button class="btn cart" value="cartNo">장바구니</button>
		</c:otherwise>
	</c:choose>
					</div>
					<button class="btn buy" value="buy">바로구매</button>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>