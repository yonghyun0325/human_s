<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 배송지 관리</title>
<!-- 마이페이지 전용 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/addressmanagement.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/main/header.jsp"%>

    <h1>나의 배송지 관리</h1>
    <div class="container">
        <!-- 좌측 사이드바 -->
        <%@ include file="/WEB-INF/views/mypage/sidebar.jsp"%>

        <!-- 메인 콘텐츠 -->
        <div class="main-content">

            <div class="info-box">
                <p>자주 쓰는 <span class="highlight">${user.userName}</span>님의 배송지를 등록 및 관리 하실 수 있습니다.</p>
            </div>

            <!-- 이용자님의 배송지 섹션 -->
            <div class="address-section">
                <h2>${user.userName}님의 배송지</h2>
                <table>
                    <thead>
                        <tr>
                            <th><input type="checkbox"></th>
                            <th>[배송지명] 받는 사람 / 주소</th>
                            <th>연락처</th>
                            <th>주문 메시지</th>
                            <th>관리</th>
                        </tr>
                    </thead>
                    <tbody id="addressTableBody">
                        <tr>
                            <td colspan="5">자주 사용하는 배송지를 주소록에 추가해주세요.</td>
                        </tr>
                    </tbody>
                </table>
                <div class="button-container">
                    <button class="add-btn" id="add-address-btn" onclick="openAddAddressPopup()">배송지 추가</button>
                </div>
            </div>

            <!-- 최근 배송지 섹션 -->
            <div class="recent-address">
                <h2>최근 배송지</h2>
                <table>
                    <thead>
                        <tr>
                            <th>받는 사람</th>
                            <th>주소</th>
                            <th>연락처</th>
                            <th>관리</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td colspan="4">최근 배송지 내역이 없습니다.</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <%@ include file ="/WEB-INF/views/main/footer.jsp" %>
    
    <script>
        // 팝업 열기 함수
        function openAddAddressPopup() {
            window.open(
                'addaddress.do',
                '배송지 추가',
                'width=600,height=700,scrollbars=yes'
            );
        }
        
     // 팝업에서 데이터를 추가하는 함수
        function addAddressToTable(addressData) {
            const tableBody = document.getElementById('addressTableBody');

            // 기존 "자주 사용하는 배송지를 주소록에 추가해주세요." 행 제거
            if (tableBody.children.length === 1 && tableBody.children[0].cells.length === 1) {
                tableBody.innerHTML = '';
            }

            // 새 행 생성
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                <td><input type="checkbox"></td>
                <td>[${addressData.addressName}] ${addressData.recipient} / ${addressData.address}</td>
                <td>${addressData.phone}</td>
                <td>${addressData.message || '없음'}</td>
                <td><button type="button" onclick="removeRow(this)">삭제</button></td>
            `;

            // 테이블에 새 행 추가
            tableBody.appendChild(newRow);
        }

        // 행 삭제 기능
        function removeRow(button) {
            const row = button.closest('tr');
            row.remove();

            // 테이블이 비었으면 안내 문구 추가
            const tableBody = document.getElementById('addressTableBody');
            if (tableBody.children.length === 0) {
                tableBody.innerHTML = `<tr><td colspan="5">자주 사용하는 배송지를 주소록에 추가해주세요.</td></tr>`;
            }
        }
    </script>
</body>
</html>