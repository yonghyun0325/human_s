$(function(){

//viewDetail.jsp
	//detailNav 눌렀을 때 해당 상세보기로 이동하기
	$(".detailNav > button").click(function(){
		const targetId = $(this).val();
		const target = $('.' + targetId);
		
		$("html, body").animate({
			scrollTop: target.offset().top
		}, 800);
	
	});
	
	//사이드바 보이게 하기
	$(window).on("scroll", function() {
        var currentScrollTop = $(window).scrollTop();  // 현재 스크롤 위치

        if (currentScrollTop > 600) {  // detailNav 높이만큼 내려갔을 때
            $(".sideBar").fadeIn();  // 사이드바 보이게
        } else {
            $(".sideBar").fadeOut();  // 사이드바 숨기기
        }
    });
    
    //상단 수량과 사이드바 수량 값 동기화
    $(".productQty").on("input", function () {
        // 현재 변경된 값
        const currentValue = $(this).val();

        // 동일 클래스의 다른 input 요소에 값 반영
        $(".productQty").not(this).val(currentValue);
    });
    
    
	//주소창에서 name이 들어간 뒤 부분을 추출해냄
	function getParameterByName(name) {
        let url = window.location.href;
        name = name.replace(/[\[\]]/g, '\\$&');
        let regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, ' '));
    }
    
    //찜하기, 장바구니, 바로구매 버튼 클릭
    $(".btn").click(function(){
    	let btn = $(this).val();
    	let idx = getParameterByName("idx");
    
	    if ($(".productQty").val() === "0" && (btn === "cart" || btn === "buy")) {
	        alert("수량을 입력해주세요.");
	    }else{
	    	if(btn === 'wishlist'){
	    		location.href="/hms/mypage/favorite.do";
	    	}else if(btn === 'cart'){
	    		location.href="/hms/mypage/basket.do";
	    	}else{
	    		location.href="/hms/orderdetails/order.no?p_idx="+idx;
	    	}
	    }
	});

});