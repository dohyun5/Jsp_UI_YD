//$a.jax()사용
$(document).ready(function(){
	$('form').on('submit',function(e){
		
		e.preventDefault(); //전송차단
		
		$.ajax({
			url:'addNotice.do',
			data:$(this).serialize(),//id=addd&name=hong&age=20 이러한 값을 넘겨주는것 
			method: 'post',
			dataType: 'json',
			error: function(jqxhr, textStatus, error){
				console.log(jqxhr, textStatus, error)
			},
			success: function(data, textStatus, jqXHR){
				console.log(data, textStatus, jqXHR);
				
				let ul = $('<ul />').append($('<li />').text('작성자 : '+data.retVal.noticeWriter),
									$('<li />').text('제목 : '+data.retVal.noticeTitle),
									$('<li />').text('내용 : '+data.retVal.noticeContent))
				$('form').after(ul);
			}
		})
		/* .done(function(result){
			console.log(result);
		}) */ //위의 success코드와 중복 위에 쓰던 아래에서 쓰던 골라서 사용하자 
		/* .fail(function(error){
			console.log(error);
		}) *///위의 error코드와 중복 위에 쓰던 아래에서 쓰던 골라서 사용하자  
		.always(function(){
			console.log('final');
		})
	})
});

