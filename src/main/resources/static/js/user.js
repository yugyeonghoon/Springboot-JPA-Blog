let index = {
	init: function() {
		//.on 함수는 첫번째 파라미터에는 이벤트, 두번째 파라미터에는 이벤트가 일어나면 무얼 할지
		$("#btn-save").on("click", () => {
			this.save();
		});
	},
	
	save: function() {
			//alert("user의 save 함수가 호출됨");
			let data = {
				username : $("#username").val(),
				password : $("#password").val(),
				email : $("#email").val()
			}
			 
			//console.log(data);
			$.ajax().done().fail();	//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		}
}

index.init();