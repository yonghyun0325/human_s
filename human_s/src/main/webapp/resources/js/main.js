$(function(){

//header.jsp
	//카테고리 보이기
	$(".navigation > .fa-bars, .navigation > .hiddenBack").on("mouseover", function(){
        $(".navigation > .hiddenBack").show();
    });
    //카테고리 숨기기
    $(".navigation > .fa-bars, .navigation > .hiddenBack").on("mouseleave", function() {
	    $(".navigation > .hiddenBack").hide();
	});

//middle.jsp
	$(".selectItem > button").click(function(){
		$(".selectItem > button").css("background-color", "#ccc");
		$(this).css("background-color", "#AFD485");
	});
	

});