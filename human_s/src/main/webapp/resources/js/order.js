$(function(){

	$('.cardInfo').css('display', 'none');
	$('.bankInfo').css('display', 'none');

	$('#checkbox1').on('click',function(){
		$('#checkbox2').prop('checked', this.checked);
		$('#checkbox3').prop('checked', this.checked);
		$('#checkbox4').prop('checked', this.checked);
	});
	
	$('#cardBtn').on('click', function(){
		$('#cardBtn').css({'background-color':'#333', 'color': 'white'});
		$('.cardInfo').css('display','flex');
		$('#bankBtn').css({'background-color':'#eee', 'color' : 'black'});
		$('.bankInfo').css('display','none');
		$('#orPayType').val('신용카드');
	})
	
	$('#bankBtn').on('click', function(){
		$('#bankBtn').css({'background-color':'#333', 'color': 'white'});
		$('.bankInfo').css('display','flex');
		$('#cardBtn').css({'background-color':'#eee', 'color' : 'black'});
		$('.cardInfo').css('display','none');
		$('#orPayType').val('계좌이체');
	})


});