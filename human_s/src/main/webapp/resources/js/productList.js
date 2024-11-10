$(function(){

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
//공통
	//상품 클릭 시 상품 상세정보 창으로 이동
	$(".productList > .productItem").click(function(){
		location.href = "/hms/product/viewDetail.no";
	});


//popNewList.jsp
	//인기순/최신순 선택창 hover 효과
	$(".selectType>a").on("mouseover", function(){
        $(this).find("i").css("color", "#4CAF50");
    });
    $(".selectType>a").on("mouseleave", function(){
        $(this).find("i").css("color", "#000");
    });
    
	//인기순,최신순 배너 / 인기순일때만 순위 표시
	let select = getParameterByName("select").trim();
	$("section > img").hide();
	if(select === "pop"){
		$("section > img:nth-child(1)").show();
		$(".productItem > .productRow").show();
	}else{
		$("section > img:nth-child(2)").show();
		$(".productItem > .productRow").hide();
	}
	
	//인기순 1-3위만 순위 박스 빨갛게
	let rowNum = document.querySelectorAll(".productRow");
	for(let i=0; i<3; i++){
		rowNum[i].style.backgroundColor = "#C76B6B";
	}
	
	
//checkBoxList.jsp
	//주소값에 따라 달라지는 체크박스 창
	if(select === "category"){
		$(".checkContainer .category").show();
		$(".checkContainer .areaSelect").hide();
	}else{
		$(".checkContainer .category").hide();
		$(".checkContainer .areaSelect").show();
	}
	
	//체크박스 '전체'가 선택되면 나머지 해제, 나머지 중 하나가 선택되면 '전체'해제
	$(".checkAll input[type='checkbox']").change(function() {
	    if ($(this).is(":checked")) {
	        // 다른 모든 체크박스를 해제
	        $("input[type='checkbox']").not(this).prop("checked", false);
	    }
	});
	$(".checkItem input[type='checkbox']").change(function() {
	    if ($(this).is(":checked")) {
	        // '전체' 체크박스를 해제
	        $(".checkAll input[type='checkbox']").prop("checked", false);
	    }
	});

});