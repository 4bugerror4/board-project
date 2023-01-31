let index = {
	init: function() {
		$('#btn-modify').on('click', () => {
			this.modify();
		});
		
		$('#btn-delete').on('click', () => {
			this.delete();
		});
	},
	
	modify: function() {
		let data = {
			id: $('#id').val(),
			title: $('#title').val(),
			content: $('#content').val()
		};
		
		$.ajax({
			type: "PUT",
			url: "/api/board/modify/" + data.id,
			data: JSON.stringify(data), // JSON 문자열
			contentType: "application/json; charset=utf-8", // body 타입이 무엇인지
			dataType: "json" // 요청이 서버로해서 응답이 왔을 때
		}).done(function(resp) {
			alert('게시 글 수정되었습니다.');
			console.log(resp); // UserApiController 에서 리턴 해준값을 resp로 받는다는 것
			location.href="/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경

	},
	
	delete: function() {
		let id = $('#id').val();

		
		$.ajax({
			type: "DELETE",
			url: "/api/board/delete/" + id,
			dataType: "text"
		}).done(function(resp) {
			alert('게시 글 삭제되었습니다.');
			location.href='/';
		}).fail(function(error) {
			alert(JSON.stringify(error));
		})


	}
	
}

index.init();