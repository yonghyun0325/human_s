$(function() {
    // 중복 확인 버튼 클릭 이벤트
    $('#sameIdBtn').on('click', function() {
        let userEmail = $(".email").val();
        let emailInput = $(".email").val();
       if (!emailInput) { // 값이 비어 있는지 확인
		    alert("이메일을 입력해주세요."); // 이메일이 비어 있으면 알림창 표시
		    return; // 함수 종료 (아래 코드가 실행되지 않음)
		}
        
        
        
        console.log("코딱지");
        
        const $button = $('#sameIdBtn')
        $.ajax({
            url: "sameidcheck.no",  // 정보를 처리할 서버의 URL
            type: "get",
            data: { userEmail: userEmail },
            success: function(email) {		            
                if (email.trim() === "SUCCESS") {
                    alert("정보가 성공적으로 저장되었습니다.");
                     // 버튼 텍스트와 ID 변경 및 기존 클릭 이벤트 제거 후 새 이벤트 추가
                    $button.text("인증번호 전송")
                           .attr("id", "email_auth_btn")
                           .off("click") // 기존 클릭 이벤트 제거
                           .on("click", function() {
                               sendEmail();
                           });
                           $(".email").attr("readonly", true);
                            $(".email").css('color', 'green');
                } else {
                    alert("정보 저장에 실패했습니다. 다시 시도해주세요.");
                }
            },
            error: function() {
                alert("서버 요청 중 오류가 발생했습니다.");
            }
        }); // end of ajax
        
         
		   
		    
		    
    }); // end of sameIdBtn click event
        
     //Email 인증 관련 추가 구문
	let code;
	const confirmEmailBtn = $("#emailNumberBtn"); //인증 확인 버튼
	
	
	function sendEmail(){
		const email = $(".email").val(); //메일 주소
		console.log(email);
		$.ajax({
			type: "post",
			url: "checkEmail.no",
			data: {email:email},
			success: function(data){
				code=data.trim(); //공백 제거 후 서버에서 받은 인증번호를 code에 저장
				alert("인증번호가 전송되었습니다.");
			},
			error: function(e){
				console.log("인증번호 받기 중 오류 발생");
			}
		});
	}
	
	//사용자가 자신의 메일에서 인증번호를 확인한 후 인증번호 입력란에 인증번호를 입력하고 난 뒤 인증확인 버튼을 클릭한 경우 처리 구문
	confirmEmailBtn.on("click", function(){
		
		const inputCode = $("#emailNumInput").val(); //인증번호 입력란에 입력된 값
		
		if(inputCode == code){ //입력된 인증번호와 서버에서 받은 인증코드가 일치하면
			alert("정상적으로 인증되었습니다.");
			$("#emailNumInput").attr("readonly", true);
		}else{
			alert("인증번호가 일치하지 않습니다. 다시 확인해주세요.");
			console.log(code +','+ inputCode);
		}
	});
    
  // 사업자조회 확인 버튼 클릭 시 AJAX 요청
		$('#sellerCheckBtn').on('click', function() {
		    let bzmnNm = $('.seName').val();
		    let seIdNum = $('.seIdNum').val();
			console.log(bzmnNm);
		    
		    $.ajax({
		        type: "GET",
		        url: "sellerInfoCheck.no",
		        data: { bzmnNm: bzmnNm, seIdNum: seIdNum },
		        success: function(data) {
		            if (data.trim() == "ok") {
		               alert("사업자 정보 중복 체크 및 조회 성공");
		                // 추가적인 성공 처리 코드를 여기에 작성하세요.
							$('.nor_seller_info > input').prop('disabled', true);
							$('.nor_seller_info > button').prop('disabled', true);
		            } else {
		                // 데이터를 받지 못한 경우 처리
		                console.log("사업자 정보 조회 실패");
		            }
		        },
		      		error: function() {
		            // AJAX 요청이 실패한 경우 처리
		            alert("사업자 정보를 조회하는 중 오류가 발생했습니다.");
		        }
		    }); // end of ajax
		}); 
    
    
    
   

    // 회원가입 버튼 클릭 시 유효성 검사
    $('.joinbutton').on('click', function(event) {
        let isValid = true;
        let errorMessage = "";

        // 필수 입력 필드 가져오기
        const email = $('.email').val();
        const password = $('input[name="userPw"]').val();
        const confirmPassword = $('input[placeholder="비밀번호 재입력"]').val();
        const nickname = $('input[name="userNick"]').val();
        const name = $('input[name="userName"]').val();
        const phone = $('input[name="userPhone"]').val();
        const birth = $('input[name="birth"]').val();
        const postCode = $('#sample6_postcode').val();
        const address = $('#sample6_address').val();
        const detailAddress = $('#sample6_detailAddress').val();

        // 빈 필드 확인
        if (!email || !password || !confirmPassword || !nickname || !name || !phone || !birth || !postCode || !address || !detailAddress) {
            isValid = false;
            errorMessage = "모든 필수 입력 필드를 채워주세요.";
        }

        // 비밀번호 유효성 검사 (영문, 숫자, 특수문자 포함 8자 이상)
        const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
        if (!passwordPattern.test(password)) {
            isValid = false;
            errorMessage = "비밀번호는 영문, 숫자, 특수문자를 포함하여 8자 이상이어야 합니다.";
        }

        // 비밀번호 일치 확인
        if (password !== confirmPassword) {
            isValid = false;
            errorMessage = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
        }

        // 유효성 검사 실패 시, 경고 메시지 출력 및 제출 중단
        if (!isValid) {
            alert(errorMessage);
            event.preventDefault(); // 폼 제출 방지
        }
    });
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
});