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
		let idx = $(this).data("idx");
		
		location.href = "/hms/product/viewDetail.no?idx="+idx;
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
		
		//인기순 1-3위만 순위 박스 빨갛게
		let rowNum = document.querySelectorAll(".productRow");
		for(let i=0; i<3; i++){
			rowNum[i].style.backgroundColor = "#C76B6B";
		}
	}else{
		$("section > img:nth-child(2)").show();
		$(".productItem > .productRow").hide();
	}
	
	
	
//checkBoxList.jsp
	//주소값에 따라 달라지는 체크박스 창
	if(select === "category"){ //전체상품
		$(".checkContainer .category").show();
		$(".checkContainer .areaSelect").hide();
		$(".checkContainer + h3").hide();
		
	}else if(select === "areaSelect"){ //지역특산물
		$(".checkContainer .category").hide();
		$(".checkContainer .areaSelect").show();
		$(".checkContainer + h3").hide();
		
	}else if(select === "hidden"){ //히든네비 과일~기타
		$(".checkContainer").hide();
		
		let checked = getParameterByName("checked").trim();
		let checkText = "";
		switch(checked){
			case 'fruit': checkText = "과일 (과실, 수실, 과일과채)"; break;
			case 'vagetable': checkText = "채소 (근채, 엽경채, 조미채소, 양채, 과채, 산채)"; break;
			case 'grainsNuts': checkText = "곡물 및 견과 (두류, 서류, 특용작물류, 약용작물류)"; break;
			case 'other': checkText = "기타 (버섯, 관엽식물)"; break;
		};
		
		$(".checkContainer + h3").show().text(checkText);
	
	}else{
		$(".checkContainer").hide();
		$(".checkContainer + h3").hide();
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
	
	//체크에 맞게 상품 리스트 변경하기
	$(".checkList input[type='checkbox']").change(function() {
		let checkeds = [];
	
		$(".checkList input[type='checkbox']:checked").each(function() {
			checkeds.push($(this).val());
		});
		
		//체크박스가 모두 해제되면
		if (checkeds.length === 0) {
			//'전체' 체크박스를 선택
			$(".checkAll input[type='checkbox']").prop("checked", true);
		    checkeds.push($(".checkAll input[type='checkbox']").val());
		}
		console.log(checkeds);
		
		//ajax 호출
		productAjaxList(select, checkeds);
	});
	
	//ajax 요청 함수
	function productAjaxList(select, checkeds){
		$.ajax({
        	type: "get",
        	url: "/hms/product/getProductList.no",
        	data: {select:select, 'checkeds[]':checkeds},
        	headers: {"Accept": "application/json"},
        	success: function(productList){
        		loadProductList(productList);
        	},
        	error: function(){
        		console.log("전체 리스트를 불러오는 데 실패했습니다.");
        		$(".productList").html("<div class='error'>상품 리스트를 불러오는 데 실패했습니다. 다시 시도해주세요.</div>");
        	}
        });
	};
	
	//ajax 요청으로 받아온 productList 입력 함수
	function loadProductList(productList){
		let htmlContent = "";
		
		if (productList.length === 0) {
			htmlContent += `
				<div>검색 결과가 없습니다.</div>
			`;
		
		}else{
			productList.forEach(function(item){
				htmlContent += `
					<div class="productItem" data-idx="${ item.pdtIdx }">
						<div class="productRow" style="background-color: #fff0"></div>
						<div class="popNum">
				`;
				
				if(item.img != null){
					htmlContent += `
						<img src="${ item.img }" alt="${ item.pdtTitle }">
					`;
				}else{
					htmlContent += `
						<img src="${contextPath}/resources/uploads/${item.pdtSave}" alt="${ item.pdtOrigin }">
					`;
				}
				
				htmlContent += `
						</div>
						<div class="productContent">
							<div class="title">${ item.pdtTitle }</div>
							<div class="price">(100g당 ${ item.pdtGPrice }원)<span>${ item.pdtPrice }</span></div>
							<div class="company">${ item.userEntity.userNick }</div>
						</div>
					</div>
				`;
			});
		}
		
		$(".productList").html(htmlContent);
		
		//상품 클릭 시 상품 상세정보 창으로 이동
		$(".productList > .productItem").click(function(){
			let idx = $(this).data("idx");
			
			location.href = "/hms/product/viewDetail.no?idx="+idx;
		});
	};

});