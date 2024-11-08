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
		<div class="productAll">
			<div class="productImage">
				<%-- <img src="${pageContext.request.contextPath}/resources/img/인기순.png" alt="상품타이틀"> --%>
			</div>
			<div class="productInfo">
				<h2>2024년 햅쌀 충남서천쌀 10kg, 20kg</h2>
				<hr>
				<div class="productPrice">
					<div>판매가격</div>
					<h2>32,100원<span>(100g당 가격)</span></h2>
					
				</div>
				<hr>
				<div class="productDetail">
					<div>
						<p>상품코드</p>
						<p>02002100</p>
					</div>
					<div>
						<p>원산지</p>
						<p>쌀/국내산</p>
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
						<p>Ns0582</p>
					</div>
					<div>
						<p>옵션</p>
						<select>
							<option value="0">옵션 선택</option>
							<option value="1">10kg</option>
							<option value="2">20kg(+20,000)</option>
						</select>
					</div>
				</div>
				<div class="totalPrice">
					<p>총 상품 금액</p>
					<p><span>0</span>원</p>
				</div>
				<div class="buttons">
					<button class="btn wishlist">찜하기</button>
					<button class="btn cart">장바구니</button>
					<button class="btn buy">바로구매</button>
				</div>
			</div>
		</div>
		<div class="detailAll">
			<div class="detailList">
				<div class="detailImgs">
					<div class="detailNav">
						<button value="detailImgs" class="selected">상품상세정보</button>
						<button value="otherProduct">관련상품</button>
						<button value="shopInfo">배송/교환/환불</button>
						<button value="productQuestion">상품문의</button>
						<button value="productComment">상품후기</button>
					</div>
					<div class="imgList">
					
					</div>
				</div>
				<div class="otherProduct">
					<div class="detailNav">
						<button value="detailImgs">상품상세정보</button>
						<button value="otherProduct" class="selected">관련상품</button>
						<button value="shopInfo">배송/교환/환불</button>
						<button value="productQuestion">상품문의</button>
						<button value="productComment">상품후기</button>
					</div>
					<div class="shopComment">
						<h2>관련상품</h2>
					</div>
				</div>
				<div class="shopInfo">
					<div class="detailNav">
						<button value="detailImgs">상품상세정보</button>
						<button value="otherProduct">관련상품</button>
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
						<button value="otherProduct">관련상품</button>
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
					        	<tr>
						            <td class="qNumber">1</td>
						            <td class="qTitle">title</td>
						            <td class="qWriter">writer</td>
						            <td class="qDate">date</td>
						            <td class="qNumber">view</td>
						        </tr>
						        <tr>
						        	<td colspan="5">내용</td>
						        </tr>
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
						    	<a href="/hms/board/product">전체보기</a>
						    	<a href="">문의하기</a>
						    </div>
						</div>
					</div>
				</div>
				<div class="productComment">
					<div class="detailNav">
						<button value="detailImgs">상품상세정보</button>
						<button value="otherProduct">관련상품</button>
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
					        	<tr>
						            <td class="cNumber">1</td>
						            <td class="cTitle">title</td>
						            <td class="cWriter">writer</td>
						            <td class="cDate">date</td>
						            <td class="cStar">⭐⭐⭐⭐</td>
						            <td class="cNumber">view</td>
						        </tr>
						        <tr>
						        	<td colspan="6">내용</td>
						        </tr>
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
						    	<a href="/hms/board/review">전체보기</a>
						    	<a href="">후기쓰기</a>
						    </div>
						</div>
					</div>
				</div>
			</div>
			<div class="sideBar">
				<div class="productInfo">
					<h2>2024년 햅쌀 충남서천쌀 10kg, 20kg</h2>
					<hr>
					<div class="productPrice">
						<div>판매가격</div>
						<h2>32,100원<br><span>(100g당 가격)</span></h2>
					</div>
					<hr>
					<div class="productDetail">
						<div>
							<p>상품코드</p>
							<p>02002100</p>
						</div>
						<div>
							<p>원산지</p>
							<p>쌀/국내산</p>
						</div>
						<div>
							<p>배송비</p>
							<p>배송조건 (택배)</p>
						</div>
						<div>
							<p>지역별<br>배송비</p>
							<p>아래 지역에 배송비가 추가됩니다<br>제주,울릉군,백령면:5,000원</p>
						</div>
						<div>
							<p>제조원</p>
							<p>Ns0582</p>
						</div>
						<div>
							<p>옵션</p>
							<select>
								<option value="0">옵션 선택</option>
								<option value="1">10kg</option>
								<option value="2">20kg(+20,000)</option>
							</select>
						</div>
					</div>
					<div class="totalPrice">
						<p>총 상품 금액</p>
						<p><span>0</span>원</p>
					</div>
					<div class="buttons">
						<button class="btn wishlist">찜하기</button>
						<button class="btn cart">장바구니</button>
					</div>
					<button class="btn buy">바로구매</button>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>