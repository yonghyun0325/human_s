$(function(){

	//따끈따끈한 신상, 지역특산물 무한스크롤
	//(header가 모든 페이지에 붙어있기 때문에 main.js에 있으면 다른 페이지에는 없는 클래스 때문에 콘솔에 오류가 계속해서 찍힘)
	// 스크롤 속도 (밀리초 단위)
	const scrollSpeed = 2; // 2px마다 스크롤
	const scrollThreshold = 230;
	
	// 무한 스크롤을 위한 변수
	const areaContainer = $(".areaContainer").get(0);
	const areaList = $(".areaList").get(0);
	const newContainer = $(".newContainer").get(0);
	const newList = $(".newList").get(0);
	
	// 한 번 스크롤한 거리
	let scrollDistance = 0;
	
	// 무한 스크롤 실행 함수
	function infiniteScroll(container, list) {
    	container.scrollLeft += scrollSpeed;
    
	    // 스크롤이 지정된 거리(scrollThreshold) 이상 이동하면 첫 번째 아이템을 맨 뒤로 보냄
	    if (container.scrollLeft >= scrollThreshold) {
	        // 첫 번째 아이템을 맨 뒤로 보내기
	        const firstItem = list.firstElementChild;
	        list.appendChild(firstItem); // 첫 번째 아이템을 맨 뒤로 이동시킴
	        
	        // 스크롤을 230px만큼 이동시킨 후, 다시 스크롤 위치를 맞춤
	        container.scrollLeft -= scrollThreshold; // 230px 만큼 돌려서 스크롤 계속 진행
	    }
	}
	
	// 무한 스크롤 시작
	setInterval(function() {
	    infiniteScroll(newContainer, newList);
	    infiniteScroll(areaContainer, areaList);
	}, 50);	
	

	//그래프 select option 필터링
	//처음에 2번째에 쌀에 해당하는 선택지만 나오게
	$(".kindCode").each(function() {
		let selectedItemCode = $(".itemCodeSelect").val();
        if ($(this).data("itemcode") != selectedItemCode) {
            $(this).hide();
        }
    });
    $(".rankCode").each(function() {
    	let selectedItemCode = $(".itemCodeSelect").val();
    	let selectedKindCode = $(".kindCodeSelect").val();
    	if($(this).data("itemcode") != selectedItemCode || $(this).data("kindcode") != selectedKindCode) {
    		$(this).hide();
    	}
    });
    //그래프 데이터 가져오기
    graphData();
	
	//첫번째꺼 select가 바뀔때마다 두번째의 선택지를 바꿔줌
	$(".itemCodeSelect").change(function(){
		let selectedItemCode = $(this).val();
		let count = 0;
		$(".kindCode").hide();
		
		$(".kindCode").each(function() {
		
	        if ($(this).data("itemcode") == selectedItemCode) {
	    		if(count == 0){
	    			$(this).prop("selected", true); // 첫 번째 일치하는 옵션에 selected 설정
	    		}	
	            $(this).show();
	            count++;
	        }
	    });
	    
	    // 두 번째 select의 선택된 옵션에 따라 세 번째 select 업데이트
	    let selectedKindCode = $(".kindCodeSelect").val(); // 두 번째 select의 선택된 값
	    count = 0;
	    $(".rankCode").hide();
	    $(".rankCode").each(function() {
	        if ($(this).data("itemcode") == selectedItemCode && $(this).data("kindcode") == selectedKindCode) {
	            if(count == 0){
	                $(this).prop("selected", true); // 첫 번째 일치하는 옵션을 선택
	            }
	            $(this).show();
	            count++;
	        }
	    });
	    
	    graphData();
	});
	
	// 두 번째 select의 선택에 따라 세 번째 select 옵션을 업데이트
	$(".kindCodeSelect").change(function(){
	    let selectedKindCode = $(this).val();  // 두 번째 select의 선택된 kindCode 값
	    let selectedItemCode = $(".itemCodeSelect").val(); // 첫 번째 select의 선택된 itemCode 값
	    let count = 0;
	    $(".rankCode").hide();
	
	    $(".rankCode").each(function() {
	        // itemCode와 kindCode가 모두 일치하는 rankCode만 표시
	        if ($(this).data("itemcode") == selectedItemCode && $(this).data("kindcode") == selectedKindCode) {
	            if(count == 0){
	                $(this).prop("selected", true); // 첫 번째 일치하는 옵션을 선택
	            }
	            $(this).show();
	            count++;
	        }
	    });
	    
	    graphData();
	});
	
	$(".rankCodeSelect").change(function(){
		graphData();
	});
	
	
	//농산물 가격동향 내용 가져오기(그래프 데이터 넣기)
	function graphData(){
		let selectedItemCode = $(".itemCodeSelect").val();
	    let selectedKindCode = $(".kindCodeSelect").val();
	    let selectedRankCode = $(".rankCodeSelect").val();
	    
	    //날짜 형식 변경
		let currentDate = new Date(); // 현재 날짜 가져오기
		let year = currentDate.getFullYear(); //현재 연도 추출
	    
	    $.ajax({ 
            type:"get",
            url:"/hms/getgraphData.no",
            data:{ selectedItemCode: selectedItemCode, selectedKindCode: selectedKindCode, 
            		selectedRankCode: selectedRankCode},
            headers: {"Accept": "application/json"},
            success:function(graphData){
            	if(graphData.img == null){
            		$(".infoSection>.priceImage").css("background-image", `url(${contextPath}/resources/img/쌀.jpg)`);   
            	}else{
	            	$(".infoSection>.priceImage").css("background-image", `url(${graphData.img})`);  
            	}
            	
                $(".infoSection>.title").text(graphData.item_name+" ("+graphData.rank.substring(0, 1)+")");
                
                let match = graphData.day1.match(/\((\d{1,2})\/(\d{1,2})\)/);
                let month = match ? match[1].padStart(2, '0') : '00';
				let day = match ? match[2].padStart(2, '0') : '00';
				let graphDay1 = `${year}.${month}.${day}`;
                $(".infoSection>.date").text(graphDay1);
                
                let unit = graphData.unit;
                let unitMatch = unit.match(/^(\d+)(kg|개)(?=\([^)]*\)|$)/);
                let reDpr1 = parseFloat(graphData.dpr1.replace(/,/g, ''));
                let reDpr2 = parseFloat(graphData.dpr2.replace(/,/g, ''));
                let reDpr6 = parseFloat(graphData.dpr6.replace(/,/g, ''));
                let number = 0;
                
                
                
                
                let unitType = "";
                if (unitMatch) {
				    number = unitMatch[1];  // 숫자 부분
				    unitType = unitMatch[2]; // 단위 부분 (kg 또는 개)
				    
				    if(unitType == '개'){
				    	let roundDpr1 = (reDpr1/number).toFixed(1);
		                let roundDpr2 = (reDpr2/number).toFixed(1);
		                let roundDpr6 = (reDpr6/number).toFixed(1);
	                	$(".infoSection>.priceUnit").text('개당 '+roundDpr1+'원');  
	                	$(".comparisonItem:first-child>.priceUnit").text('개당 '+roundDpr2+'원');
	                	$(".comparisonItem:nth-child(2)>.priceUnit").text('개당 '+roundDpr6+'원');
				    }else{
				    	let roundDpr1 = (reDpr1/number/10).toFixed(1);
		                let roundDpr2 = (reDpr2/number/10).toFixed(1);
		                let roundDpr6 = (reDpr6/number/10).toFixed(1);
				    	$(".infoSection>.priceUnit").text('100g당 '+roundDpr1+'원');  
				    	$(".comparisonItem:first-child>.priceUnit").text('100g당 '+roundDpr2+'원');
	                	$(".comparisonItem:nth-child(2)>.priceUnit").text('100g당 '+roundDpr6+'원');
				    }
				    
				}
                
                $(".infoSection>.price").text(graphData.dpr1+"원");
                $(".infoSection>.unit").text(unit);
                
                let match2 = graphData.day2.match(/\((\d{1,2})\/(\d{1,2})\)/);
                let month2 = match2 ? match2[1].padStart(2, '0') : '00';
				let day2 = match2 ? match2[2].padStart(2, '0') : '00';
				let graphDay2 = `${year}.${month2}.${day2}`;
                $(".comparisonItem:first-child>.date").text(graphDay2);
                
                $(".comparisonItem:first-child>.price").text(graphData.dpr2+"원");
                $(".comparisonItem>.unit").text(unit);
                
				let graphDay3 = `${year-1}.${month}.${day}`;
                $(".comparisonItem:nth-child(2)>.date").text(graphDay3);
                
                $(".comparisonItem:nth-child(2)>.price").text(graphData.dpr6+"원");
                
                let dpr1 = dprMatch(graphData.dpr1);
                let dpr2 = dprMatch(graphData.dpr2);
                let dpr3 = dprMatch(graphData.dpr3);
                let dpr4 = dprMatch(graphData.dpr4);
                let dpr5 = dprMatch(graphData.dpr5);
                let dpr6 = dprMatch(graphData.dpr6);
                
                if (window.mychart) {
				    window.mychart.destroy(); // 기존 차트를 삭제
				}
                
                var ctx = document.getElementById('myChart').getContext('2d');
		        window.mychart = new Chart(ctx, {
		            type: 'line',
		            data: {
		                labels: ['1년전','1달전','2주전', '1주전', '어제', '오늘'],
		                datasets: [{
		                	label: '금액',
		                    backgroundColor: '#AFD485',
		                    borderColor: '#AFD485',
		                    data: [dpr6, dpr5, dpr4, dpr3, dpr2, dpr1]
		                }]
		            },
		            options: {
						maintainAspectRatio: false,
						plugins: {
		                    // ChartDataLabels 플러그인 설정
		                    datalabels: {
		                        color: '#777',
		                        font: {
		                            size: 12
		                        },
		                        formatter: function(value, context) { return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","); },
		                        anchor: 'end',
		                        align: 'top',
		                        offset: 5 // 점에서 5px 떨어지도록 설정
		                    }
		                }
					},
					plugins:[ChartDataLabels], //점 위에 수치가 표시하게 하기 위한 플러그인
        		});
            },
            error:function(){
                console.log("해당 제품의 일별 가격 정보를 불러오는 데 실패했습니다.");
            }
        });
	}
	
	function dprMatch(dpr){
		let match = dpr.replace(/,/g, '');
		let numberDpr = parseInt(match, 10);
		
		return numberDpr;
	};
	
	//팜스토리 클릭 시 해당 팜스토리 내용 보여주기
	$(".storyItem").click(function(){
		let id = $(this).data("id");
		location.href = "/hms/story/view?storyId="+id;
	});
	
	//공지사항 클릭 시 해당 공지사항 내용 보여주기
	$(".noticeItem").click(function(){
		let id = $(this).data("id");
		location.href = "/hms/board/notice/view.do?noticeId="+id;
	});
	
	//Best 상품 클릭 시 해당 상품 상세보기
	$(".bestItem").click(function(){
		let idx = $(this).data("idx");
		location.href = "/hms/product/viewDetail.no?idx="+idx;
	});

});