$(function() {
    	
    	// 핸드폰 번호 조합 
        // 각 입력 값 가져오기
        const phone1 = $('.phone1').val(); // 선택된 option의 value
        const phone2 = $('.phone2').val(); // 두 번째 입력 필드 값
        const phone3 = $('.phone3').val(); // 세 번째 입력 필드 값

        // 하이픈(-) 없이 문자열을 연결
        const fullPhoneNumber = phone1 + phone2 + phone3;

        // 조합된 값을 hidden input에 설정
        $('#phoneNum').val(fullPhoneNumber);
	    
	});