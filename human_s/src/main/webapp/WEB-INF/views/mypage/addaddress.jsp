<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송지 추가</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addaddress.css">
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

</head>
<body>
	<div class="popup-container">
        <h2>배송지 추가</h2>
        <p class="description">배송을 받을 수취인 정보를 입력합니다.</p>
        <form id="addressForm">
            <table class="form-table">
                <tr>
                    <th><label for="addressName">배송지명</label></th>
                    <td><input type="text" id="addressName" name="addressName" required></td>
                </tr>
                <tr>
                    <th><label for="recipient">받는 사람(이름)</label></th>
                    <td><input type="text" id="recipient" name="recipient" required></td>
                </tr>
                <tr>
                    <th><label for="address">주소</label></th>
                    <td>
                        <div class="address-wrapper">
                            <input type="text" id="postcode" name="postcode" placeholder="우편번호" required>
                            <button type="button" class="btn-postcode" onclick="searchPostcode()">우편번호</button>
                        </div>
                        <input type="text" id="address" name="address" placeholder="기본 주소" required>
                        <input type="text" id="addressDetail" name="addressDetail" placeholder="상세 주소">
                    </td>
                </tr>
                <tr>
                    <th><label for="phone">연락처1</label></th>
                    <td>
                        <div class="phone-wrapper">
                            <input type="tel" id="phone1" name="phone1" maxlength="4" placeholder="010" required>
                            <span>-</span>
                            <input type="tel" id="phone2" name="phone2" maxlength="4" placeholder="1234" required>
                            <span>-</span>
                            <input type="tel" id="phone3" name="phone3" maxlength="4" placeholder="5678" required>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><label for="message">주문 메시지</label></th>
                    <td><textarea id="message" name="message" maxlength="300" placeholder="300자 이내"></textarea></td>
                </tr>
            </table>
            
            <!-- 체크박스 영역 -->
            <div class="checkbox-group">
                <label class="checkbox-label">
                    <input type="checkbox" name="default" id="default">
                    기본 배송지로 설정
                </label>
                <label class="checkbox-label">
                    <input type="checkbox" name="agreement" id="agreement" required>
                    <a href="#">(준수)개인정보 수집·이용 동의</a>
                </label>
            </div>
            
            <div class="info-box">
                <table>
                    <tr>
                        <td>목적</td>
                        <td>상품 배송(구매/환불/취소/교환)을 위한 수취인 정보 저장하고 필요 시, 배송지로 사용</td>
                    </tr>
                    <tr>
                        <td>항목</td>
                        <td>수취인 정보(이름, 연락처1, 연락처2, 주소)</td>
                    </tr>
                    <tr>
                        <td>보유기간</td>
                        <td>배송지 삭제 시까지<br>(또는 회원 탈퇴 시까지)</td>
                    </tr>
                </table>
            </div>
            <div class="button-group">
                <button type="submit" class="btn-save">저장</button>
                <button type="button" class="btn-cancel" onclick="window.close()">취소</button>
            </div>
        </form>
    </div>

    <script>
        // 폼 제출 이벤트 처리
        document.getElementById('addressForm').addEventListener('submit', function (e) {
            e.preventDefault(); // 기본 제출 동작 막기
            const formData = new FormData(this);

            // 데이터 확인용 (추후 서버로 전달 가능)
            console.log(Object.fromEntries(formData.entries()));

            alert('배송지가 저장되었습니다.');
            window.close(); // 팝업 닫기
        });
        
    	 // 우편번호 검색 함수
        function searchPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 우편번호와 주소 정보를 받아와서 입력 필드에 채움
                    document.getElementById('postcode').vajlue = data.zonecode; // 우편번호
                    document.getElementById('address').value = data.address; // 기본 주소

                    // 상세 주소 필드로 포커스 이동
                    document.getElementById('addressDetail').focus();
                }
            }).open();
        }
    	 
        document.getElementById('addressForm').addEventListener('submit', function (e) {
            e.preventDefault(); // 기본 제출 동작 막기

            // 폼 데이터 읽기
            const formData = new FormData(this);

            // 입력값 가져오기
            const addressData = {
                addressName: formData.get('addressName'), // 배송지명
                recipient: formData.get('recipient'), // 받는 사람
                address: `${formData.get('postcode')} ${formData.get('address')} ${formData.get('addressDetail')}`, // 주소
                phone: `${formData.get('phone1')}-${formData.get('phone2')}-${formData.get('phone3')}`, // 연락처
                message: formData.get('message') || '없음' // 메시지 (없으면 기본값)
            };

            // 부모 창으로 데이터 전달
            if (window.opener && !window.opener.closed) {
                window.opener.addAddressToTable(addressData);
            }

            alert('배송지가 추가되었습니다.');
            window.close(); // 팝업 닫기
        });
    </script>
</body>
</html>