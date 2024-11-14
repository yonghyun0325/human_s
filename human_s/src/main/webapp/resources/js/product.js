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
    
//writeDetail.jsp 
    //분류코드 select option 필터링
    //처음에 과실류에 해당하는 값으로 중.소분류코드 세팅
    $(".midCode").each(function(){
    	if($(this).data("large") != '06'){
    		$(this).hide();
    	}
    });
    $(".smallCode").each(function(){
    	let code = $(this).data("large")+','+$(this).data("mid");
    	if(code != '06,01'){
    		$(this).hide();
    	}
    });
    
    minMaxAvgData();
    
    //대분류코드가 바뀔때마다 중.소분류 선택지 세팅
    $("#pdtLargeCode").change(function(){
    
    	//중분류 선택지 세팅
    	let selectLarge = $(this).val();
    	let count = 0;
    	$(".midCode").hide();
    	
    	$(".midCode").each(function(){
    		if($(this).data("large") == selectLarge){
    			if(count == 0){
    				$(this).prop("selected", true);
    			}
    			$(this).show();
    			count++;
    		}
    	});
    	
    	//소분류 선택지 세팅
    	let selectMid = $("#pdtMidCode").val();
    	count = 0;
    	$(".smallCode").hide();
    	
    	$(".smallCode").each(function(){
    		if($(this).data("large") == selectLarge && $(this).data("mid") == selectMid){
    			if(count == 0){
    				$(this).prop("selected", true);
    			}
    			$(this).show();
    			count++;
    		}
    	});
    	
    	minMaxAvgData();
    	
    });
    
    //중분류코드가 바뀔때마다 소분류 선택지 세팅
    $("#pdtMidCode").change(function(){
    	let selectLarge = $("#pdtLargeCode").val();
    	let selectMid = $(this).val();
    	let count = 0;
    	$(".smallCode").hide();
    	
    	$(".smallCode").each(function(){
    		if($(this).data("large") == selectLarge && $(this).data("mid") == selectMid){
    			if(count == 0){
    				$(this).prop("selected", true);
    			}
    			$(this).show();
    			count++;
    		}
    	});
    	
    	minMaxAvgData();
    	
    });
    
    //대.중.소 분류에 맞는 최대,최소,평균값 가져오기
    function minMaxAvgData(){
    	let largeCode = $("#pdtLargeCode").val();
    	let midCode = $("#pdtMidCode").val();
    	let smallCode = $("#pdtSmallCode").val();
    	
    	$.ajax({
    		type:"get",
    		url:"/hms/product/getGraphData.do",
    		data:{ largeCode: largeCode, midCode: midCode, smallCode: smallCode },
    		headers: {"Accept": "application/json"},
    		success:function(data){
				
				let htmlContent = ``;
				data.forEach((item, index) => {
					let avgValue = Math.round(item.avg);
			        htmlContent += `<option value="${index}" data-min="${item.min}" data-max="${item.max}" 
			        	data-avg="${avgValue}">${item.std}</option>`;
			    });
			    
			    $('.graphStd').html(htmlContent);
				
				let avgValue = Math.round(data[0].avg);
    			graphData(data[0].min, data[0].max, avgValue);
    		},
    		error:function(){
    			console.log("해당 분류에 맞는 최대,최소,평균값을 불러오는데 실패했습니다.");
    		}
    	});
    };
    
    $(".graphStd").change(function(){
    	let selectedOption = $(this).find("option:selected"); // 선택된 <option> 요소 가져오기
    	let minData = selectedOption.data("min");
    	let maxData = selectedOption.data("max");
    	let avgData = selectedOption.data("avg");
    	
    	graphData(minData, maxData, avgData);
    });
    
    function graphData(minData, maxData, avgData){
    
		if (window.mychart) {
		    window.mychart.destroy(); // 기존 차트를 삭제
		}
		
    	var ctx = document.getElementById('myChart').getContext('2d');
        window.mychart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['최소값','최대값','평균'],
                datasets: [{
                	label: '금액',
                    backgroundColor: '#AFD485',
                    borderColor: '#AFD485',
                    data: [minData, maxData, avgData]
                }]
            },
            options: {
                maintainAspectRatio: false,
                scales: {
                    y: {
                        beginAtZero: true,
                        max: Math.ceil(maxData * 1.1) // 최대값보다 10% 더 높게 설정
                    }
                },
                plugins: {
                    // ChartDataLabels 플러그인 설정
                    datalabels: {
                        color: '#777',
                        font: {
                            size: 12
                        },
                        anchor: 'end',
                        align: 'top',
                        offset: 2 // 막대에서 2px 떨어지도록 설정
                    }
                }
            },
            plugins:[ChartDataLabels], //바 위에 수치가 표시하게 하기 위한 플러그인
		});
    }

});