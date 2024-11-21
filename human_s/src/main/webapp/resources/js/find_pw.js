$(function() {

		
		let authCode;
	$('#sendEmailNumBtn').on('click', function(){
		console.log("실행")
		let email = $('#emailInput').val();
		if(email.length == 0){
			alert("이메일을 입력해주세요");
			return;
		}
		$.ajax({
			type:"post",
			url:"checkEmail.no",
			data:{email:email},
			success: function(code){
				if(code != null){
						alert("인증번호가 발송되었습니다");
						authCode = code;			
					
				}else {
					alert("이메일이 존재하지 않습니다");
				}
			}, //end of if
			error: function(){
				console.log("ajax요청 중 오류 발생");
			}		

		})//end of ajax
	
	});//end of #sendEmailNumBtn on click

		$('#confirmAuthNumBtn').on('click', function(){
			const authNum = $('#authNum').val();
			if(authNum == authCode){
				alert("정상적으로 인증되었습니다");
			}else{
				alert("인증에 실패하였습니다");
				return;
			}
		
		
		});//end of click









});