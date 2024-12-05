 $(function(){

	$('.checkoridx_container').css('display', 'none');
	$('#userBtn').css('background-color', 'rgba(0,0,0,0.1');
 	$('#naverApiBtn').on("click", function(){
 		window.location.href = "/hms/naver_login";
 	});
	    
	$('#unUserBtn').on("click", function() {
	    $('.login_container').css('display', 'none');
	    $('.checkoridx_container').css('display', 'flex');
	    $('#unUserBtn').css('background-color', 'rgba(0,0,0,0.1)');
	    $('#userBtn').css('background-color', 'white');
	});
	    
    $('#userBtn').on("click", function(){
    	 $('.login_container').css('display', 'flex');
   		 $('.checkoridx_container').css('display', 'none');
   		 $('#userBtn').css('background-color', 'rgba(0,0,0,0.1)');
   		 $('#unUserBtn').css('background-color', 'white');
    });
    
    $("#phoneNum").on("input", function(){
    			
		 let input = $(this).val().replace(/[^0-9]/g, ""); // 숫자만 남기기
		    let formatted = "";

		    if (input.length <= 3) {
		        // 3자리 이하일 때 그대로 유지
		        formatted = input;
		    } else if (input.length <= 7) {
		        // 4~7자리일 때 010-123 형식
		        formatted = input.slice(0, 3) + "-" + input.slice(3);
		    } else {
		        // 8자리 이상일 때 010-1234-5678 형식
		        formatted = input.slice(0, 3) + "-" + input.slice(3, 7) + "-" + input.slice(7, 11);
		    }

		    $(this).val(formatted); // 포맷팅된 값 설정
		
	});
    
 });
 