$(function(){

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

});