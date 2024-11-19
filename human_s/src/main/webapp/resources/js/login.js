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
    
 });
 