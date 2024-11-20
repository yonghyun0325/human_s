$(document).ready(function () {
    // 필터 버튼 클릭 이벤트
    $('.filter-btn').on('click', function () {
        const filterType = $(this).data('period'); // 버튼의 data-period 값 가져오기
        
        
        const baseDate = $('#baseDate').val(); // 기준 날짜 입력값
        const endDate = $('#endDate').val(); // 종료 날짜 입력값

        let calculatedDate = null;

        // 필터 버튼의 타입에 따라 날짜 계산
        if (filterType) {
            calculatedDate = calculateDate(baseDate, filterType);
        } else if (baseDate && endDate) {
            calculatedDate = { startDate: baseDate, endDate: endDate };
        } else {
            alert("유효한 날짜를 선택해주세요.");
            return;
        }

        // Ajax 요청으로 주문 데이터 필터링
        fetchFilteredOrders(calculatedDate);
    });

    // 날짜 계산 함수
    function calculateDate(baseDate, filterType) {
        const base = new Date(baseDate);
        let startDate = new Date(base);

        switch (filterType) {
            case 'today':
                break; // 오늘 그대로
            case 'yesterday':
                startDate.setDate(base.getDate() - 1);
                break;
            case 'week':
                startDate.setDate(base.getDate() - 7);
                break;
            case 'month':
                startDate.setMonth(base.getMonth() - 1);
                break;
            case 'threeMonths':
                startDate.setMonth(base.getMonth() - 3);
                break;
            case 'year':
                startDate.setFullYear(base.getFullYear() - 1);
                break;
        }

        return {
            startDate: startDate.toISOString().split('T')[0],
            endDate: base.toISOString().split('T')[0],
        };
    }

    // Ajax 요청 함수
    function fetchFilteredOrders(dateRange) {
        $.ajax({
            url: '/hms/mypage/orders/filter',
            type: 'GET',
            data: {
                startDate: dateRange.startDate,
                endDate: dateRange.endDate,
            },
            success: function (data) {
                updateOrderTable(data);
            },
            error: function (error) {
                console.error("오류 발생:", error);
            },
        });
    }

    // 테이블 업데이트 함수
    function updateOrderTable(orderList) {
        const tbody = $('.order-table tbody');
        tbody.empty();

        if (orderList && orderList.length > 0) {
            orderList.forEach(order => {
                const row = `
                    <tr>
                        <td>${order.orPayDate}</td>
                        <td>${order.orName}</td>
                        <td>${order.orCount}</td>
                        <td>${order.orPayAmount}원</td>
                        <td>${order.orStatus}</td>
                    </tr>
                `;
                tbody.append(row);
            });
        } else {
            tbody.append('<tr><td colspan="5" class="no-data">주문내역이 없습니다.</td></tr>');
        }
    }
});
