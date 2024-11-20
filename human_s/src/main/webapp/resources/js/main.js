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
	
	//상단 검색 구현하기
	$(".headerTop > .search > i").click(function(){
		let select = $(".headerTop > .search > .searchProduct").val();
		
		location.href = "/hms/product/checkBoxList.no?select="+select;
	});

//footer.jsp
	

});