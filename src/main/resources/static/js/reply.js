let indexReply = {
	init: function() {
		$('#btn-reply-save').on('click', () => {
			this.save();
		});

	},
	
	save: function() {
		let data = {
			id: $('#id').val(),
			content: $('#content').val()
		};
		
		console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/api/reply/save",
			data: JSON.stringify(data), // JSON 문자열
			contentType: "application/json; charset=utf-8", // body 타입이 무엇인지
			dataType: "text" // 요청이 서버로해서 응답이 왔을 때
		}).done(function(resp) {
			alert('댓글 작성되었습니다.');
			console.log(resp)
			location.href="/board/detail/" + data.id;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경

	},
	
	replyDelete: function(boardId, replyId) { // html 에서 던진 값을 받는다.
	
		console.log(boardId, replyId);
		
		$.ajax({
			type: "DELETE",
			url: "/api/reply/" + replyId,
			dataType: "text"
		}).done(function(resp) {
			alert('댓글 삭제 완료되었습니다.');
			location.href='/board/detail/' + boardId;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})
	}
	
}

indexReply.init();