$(function(){

 
 	$('#beforeButton').on('click', function() {
    window.location.href = '/hms/user/login.no';
	});
	
	
	 // "모두 동의합니다" 체크박스를 클릭했을 때
    $('#allAgree').on('change', function() {
        // "모두 동의합니다" 체크박스의 상태를 가져와서, 다른 체크박스들의 상태를 설정
        const isAllChecked = $(this).prop('checked');
        
        // 모든 개별 체크박스들의 상태를 "모두 동의합니다" 체크박스와 동일하게 설정
        $('.individual-checkbox').prop('checked', isAllChecked);
    });

    // "다음 단계" 버튼을 클릭했을 때
    $('#nextButton').on('click', function() {
        // 모든 개별 체크박스가 체크되었는지 확인
        const allChecked = $('.individual-checkbox').length === $('.individual-checkbox:checked').length;

        // 만약 하나라도 체크되지 않은 체크박스가 있으면 경고창을 표시
        if (!allChecked) {
            alert("모든 항목에 동의해주세요");
        } else {
            // 모든 체크박스가 체크된 경우 다음 단계를 진행
            // (여기에서 실제 다음 단계를 수행하는 코드를 추가하시면 됩니다)
            console.log("모든 약관에 동의했습니다. 다음 단계로 이동합니다.");
            location.href='/hms/user/jointype.no';
        }
    });

	




});
