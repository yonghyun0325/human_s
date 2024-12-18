<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/userupdate.css">
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/popup_2.js"></script>
</head>
<body>
    <%@ include file="../main/header.jsp"%>

    <h1>회원정보 수정</h1>
    <div class="userupdate-container">
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp" %>
        
        <div class="userupdate_content">
            <div class="content-header">
                <h3>회원정보 입력</h3>
                <p class="notice">* 표시는 필수입력항목입니다.</p>
            </div>
            <hr>
    
            <form action="updateProcess.do" method="post">
                    	<input type="hidden" name="userIdx" value="${user.userIdx}"/>
                    	<input type="hidden" name="userAdd" value="${add.addIdx}"/>
                <table>
                    <tr>
                        <th><label for="name"><span class="red-star">*</span> 이름</label></th>
                        <td><input type="text" id="name" name="userName" value="${user.userName}"></td>
                    </tr>
                    <tr>
                        <th><label for="username"><span class="red-star">*</span> 아이디</label></th>
                        <td><input type="text" id="username"  value="${user.userEmail}" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="password"><span class="red-star">*</span> 비밀번호</label></th>
                        <td><input type="password" id="password" name="userPw"><span class="pw-option">* 영문 대소문자/숫자/특수문자를 혼용하여 2종류 10~16자 또는 3종류 8~16자</span></td>
                    </tr>
                    <tr>
                        <th><label for="confirm_password"><span class="red-star">*</span> 비밀번호 확인</label></th>
                        <td><input type="password" id="confirm_password"></td>
                    </tr>
                    <tr>
                        <th><label for="birth"><span class="red-star">*</span> 생년/성별</label></th>
                        <td><input type="text" id="birth" value="${user.birth}" readonly></td>
                    </tr>
                    <tr>
                        <th><label for="address"><span class="red-star">*</span> 주소</label></th>
                        <td>
                            <input type="text" id="sample6_postcode" name="addPost" value="${add.addPost}" readonly>
                            <button type="button" class="btn" onclick="sample6_execDaumPostcode()" >우편번호검색</button><br>
                            <input type="text" id="sample6_address" name="add1" class="address_particular" value="${add.add1}" readonly>
                            <input type="text" id="sample6_detailAddress" name="add2" value="${add.add2}">
                            <input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
                        </td>
                    </tr>
                    <tr>
                        
                    </tr>
                    <tr>
                        <th><label for="userPhone"><span class="red-star">*</span> 휴대폰</label></th>
                        <td>
                     			<input type="text" name="userPhone" id="phoneNum" maxlength="13" value="${ user.userPhone }">
                        </td>
                    </tr>
                    <tr>
                        <th><label for="userNick"><span class="red-star">*</span> 닉네임</label></th>
                        <td>
                            <input type="text" name="userNick" value="${ user.userNick }">
                        </td>
                    </tr>
                    <tr>
                        <th>환불계좌</th>
                        <td>
                            <select name="bank">
                                <option>은행 선택</option>
                                <option value="국민">국민</option>
                                <option value="신한">신한</option>
                                <option value="카카오뱅크">카카오뱅크</option>
                            </select>
                            <input type="text">
                        </td>
                    </tr>
                    <tr>
                        <th>뉴스메일</th>
                        <td>
                            <label><input type="radio"  value="yes" checked> 받습니다.</label>
                            <label><input type="radio"  value="no"> 받지 않습니다.</label>
                        </td>
                    </tr>
                    <tr>
                        <th>SMS안내</th>
                        <td>
                            <label><input type="radio"  value="yes" checked> 받습니다.</label>
                            <label><input type="radio"  value="no"> 받지 않습니다.</label>
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
    <!-- 우편번호버튼 눌렀을때 불러오는 스크립트 -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
    	$(function(){
    		
    		$("#phoneNum").on("input", function(){
    			
	   			 let input = $(this).val().replace(/[^0-9]/g, ""); // 숫자만 남기기
	   			    let formatted = "";
	
	   			    if (input.length <= 3) {
	   			        // 3자리 이하일 때 그대로 유지
	   			        formatted = input;
	   			    } else if (input.length <= 7) {
	   			        // 4~7자리일 때 010-123 형식
	   			        formatted = input.slice(0, 3) + "-" + input.slice(3);
	   			    } else {
	   			        // 8자리 이상일 때 010-1234-5678 형식
	   			        formatted = input.slice(0, 3) + "-" + input.slice(3, 7) + "-" + input.slice(7, 11);
	   			    }
	
	   			    $(this).val(formatted); // 포맷팅된 값 설정
    			
    		});
    		
    	});
    </script>
</body>
</html>