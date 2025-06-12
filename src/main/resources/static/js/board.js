let index = {
	init: function() {
		$("#btn-save").on("click", () => {
			this.save();
		});
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			alert("글작성이 완료되었습니다");
			location.href = "/";
			console.log(resp)
		}).fail(function(error) {
			alert(JSON.stringify(error));
			console.log("실패하였습니다.")
		}); 
	},

}

index.init();