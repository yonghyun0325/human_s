<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userupdate.css">
</head>
<body>
    <%@ include file="../main/header.jsp"%>

    <h1>회원정보 수정</h1>
    <div class="userupdate-container">
        <!-- 좌측 메뉴 -->
        <div class="sidebar">
            <h3>쇼핑정보</h3>
            <ul>
                <li>주문배송현황</li>
                <li>나의 배송지 관리</li>
                <li>선물함</li>
                <li>정기구독 관리</li>
            </ul>
            <h3>혜택정보</h3>
            <ul>
                <li>할인쿠폰내역</li>
                <li>적립금내역</li>
            </ul>
            <h3>활동정보</h3>
            <ul>
                <li>찜한상품</li>
                <li>최근 본 상품</li>
                <li>장바구니</li>
                <li>1:1 문의내역</li>
                <li>내 게시글 보기</li>
            </ul>
            <h3>개인정보</h3>
            <ul>
                <li><a href="userUpdate.jsp" class="no-underline">회원정보수정</a></li>
                <li>회원탈퇴</li>
            </ul>
        </div>
        <div class="userupdate_content">
            <div class="content-header">
                <h3>회원정보 입력</h3>
                <p class="notice">* 표시는 필수입력항목입니다.</p>
            </div>
            <br>
            <hr>
    
            <form action="#" method="post">
                <table>
                    <tr>
                        <th><label for="name">* 이름</label></th>
                        <td><input type="text" id="name" name="name" value="이용현"></td>
                    </tr>
                    <tr>
                        <th><label for="username">* 아이디</label></th>
                        <td><input type="text" id="username" name="username" value="ka@89a3e3c6b" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="password">* 비밀번호</label></th>
                        <td><input type="password" id="password" name="password"><span class="pw-option">* 영문 대소문자/숫자/특수문자를 혼용하여 2종류 10~16자 또는 3종류 8~16자</span></td>
                    </tr>
                    <tr>
                        <th><label for="confirm_password">* 비밀번호 확인</label></th>
                        <td><input type="password" id="confirm_password" name="confirm_password"></td>
                    </tr>
                    <tr>
                        <th><label for="birth">* 생년/성별</label></th>
                        <td><input type="text" id="birth" name="birth"></td>
                    </tr>
                    <tr>
                        <th><label for="address">* 주소</label></th>
                        <td>
                            <input type="text" id="address" name="address">
                            <button type="button" class="btn">우편번호검색</button>
                        </td>
                    </tr>
                    <tr>
                        <th><label for="phone">* 휴대폰</label></th>
                        <td>
                            <select name="phone1">
                                <option value="010">010</option>
                                <option value="011">011</option>
                            </select>
                            - <input type="text" name="phone2" size="4" maxlength="4">
                            - <input type="text" name="phone3" size="4" maxlength="4">
                        </td>
                    </tr>
                    <tr>
                        <th><label for="email">* 이메일</label></th>
                        <td>
                            <input type="text" name="email1">
                            @ <select name="email2">
                                <option value="kakao.com">kakao.com</option>
                                <option value="naver.com">naver.com</option>
                                <option value="직접입력">직접입력</option>
                            </select>
                            <button type="button" class="btn">이메일 중복확인</button>
                        </td>
                    </tr>
                    <tr>
                        <th>환불계좌</th>
                        <td>
                            <select name="bank">
                                <option>은행 선택</option>
                                <option value="국민">국민</option>
                                <option value="신한">신한</option>
                            </select>
                            <input type="text" name="account_number">
                        </td>
                    </tr>
                    <tr>
                        <th>뉴스메일</th>
                        <td>
                            <label><input type="radio" name="newsletter" value="yes" checked> 받습니다.</label>
                            <label><input type="radio" name="newsletter" value="no"> 받지 않습니다.</label>
                        </td>
                    </tr>
                    <tr>
                        <th>SMS안내</th>
                        <td>
                            <label><input type="radio" name="sms" value="yes" checked> 받습니다.</label>
                            <label><input type="radio" name="sms" value="no"> 받지 않습니다.</label>
                        </td>
                    </tr>
                </table>
    
                <div class="buttons">
                    <button type="submit" class="save-btn">저장하기</button>
                    <button type="button" onclick="history.back()">이전으로</button>
                </div>
            </form>
        </div>
    </div>

    <%@ include file="../main/footer.jsp"%>
</body>
</html>