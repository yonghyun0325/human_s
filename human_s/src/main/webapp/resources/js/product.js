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
    	let qty = $(".productQty").val();
    	let grade = $(".buy").data("grade");
    
    	if(btn === "wishlistNo" || btn === "cartNo"){
    		alert("로그인 후 이용해주세요.");
    		
    		location.href = "/hms/user/login.no";
	    }else if (grade == '2' || grade == '3'){
	    	alert("구매할 수 있는 계정이 아닙니다.");
	    	
	    }else if ($(".productQty").val() === "0" && (btn === "cart" || btn === "buy")) {
	        alert("수량을 입력해주세요.");
	        
	    }else{
	    	if(btn === 'wishlist'){ //찜하기
	    		$.ajax({
	    			type: "get",
	    			url: "/hms/product/inFavorite.do",
	    			data: {idx:idx},
	    			success: function(data){
	    				if(data.trim() == 'in'){ //찜하기 성공
	    					alert("찜 목록에 등록되었습니다.");
	    				}else if(data.trim() == 'out'){ //찜하기 삭제 성공
	    					alert("찜 목록에서 삭제되었습니다.");
	    				}else{ //찜하기 실패
	    					alert("찜하기에 실패했습니다.");
	    				}
	    			},
	    			error: function(){
	    				console.log("찜하기 등록 중 오류 발생");
	    			}
	    		});
	    		
	    	}else if(btn === 'cart'){ //장바구니
	    		$.ajax({
	    			type: "get",
	    			url: "/hms/product/productInCart.do",
	    			data: {qty:qty, idx:idx},
	    			success: function(data){
	    				if(data.trim() == 'ok'){
	    					alert("장바구니에 등록되었습니다");
	    				}else{
	    					alert("장바구니 등록에 실패했습니다");
	    				}
	    			},
	    			error: function(){
	    				console.log("장바구니 등록 중 오류 발생");
	    			}
	    		});
	    		
	    	}else{ //바로구매
	    		location.href="/hms/orderdetails/order.no?p_idx="+idx+"&qty="+qty;
	    	}
	    }
	});
	
	//상품 삭제 버튼 클릭
	$(".updateDetail > button").click(function(){
		let isConfirmed = confirm("이 상품을 삭제하시겠습니까?");
		let idx = $(this).val();
		
		if (isConfirmed) {
        	location.href="/hms/product/deleteDetail.do?idx="+idx;
    	}
	});

});