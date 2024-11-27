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
	//상단 검색 엔터키로도 구현
	$(".headerTop > .search > .searchProduct").on("keydown", function(event){
	    if (event.key === "Enter" || event.keyCode === 13) {
	        let select = $(this).val();
	        location.href = "/hms/product/checkBoxList.no?select=" + select;
	    }
	});
	
	//주소창에서 /텍스트.no의 텍스트를 추출해냄
	const extractContent = () => {
	    let url = window.location.href;
	    return (url.match(/\/([^\/]+)\./) || [])[1] || null;
	};
	let nav = extractContent();
	
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
	
	switch(nav){
		case 'popNewList' : {
			let select = getParameterByName("select").trim();
			if(select === 'pop'){
				$(".otherPage > a:first-child").css("color", "#7cb43f"); break;
			}else{
				$(".otherPage > a:nth-child(2)").css("color", "#7cb43f"); break;
			}
		}
		case 'checkBoxList' : {
			let select = getParameterByName("select").trim();
			if(select === 'areaSelect'){
				$(".otherPage > a:nth-child(3)").css("color", "#7cb43f"); break;
			}else if(select === 'category'){
				$(".otherPage > a:nth-child(4)").css("color", "#7cb43f"); break;
			}else{
				$(".otherPage > a").css("color", "#000"); break;
			}
		}
		case 'hiddenList' : $(".otherPage > a:nth-child(4)").css("color", "#7cb43f"); break;
		case 'farmstory' : $(".otherPage > a:nth-child(5)").css("color", "#7cb43f"); break;
		case 'notice' : $(".otherPage > a:nth-child(6)").css("color", "#7cb43f"); break;
		case 'manager' : $(".otherPage > a:nth-child(7)").css("color", "#7cb43f"); break;
		default : $(".otherPage > a").css("color", "#000");
	}

//footer.jsp
	

});