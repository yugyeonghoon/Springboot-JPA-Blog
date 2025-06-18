let index = {
	init: function() {
		//.on 함수는 첫번째 파라미터에는 이벤트, 두번째 파라미터에는 이벤트가 일어나면 무얼 할지
		$("#btn-save").on("click", () => {	//function(){}, ()=>{} this를 바인딩하기 위해서 ! 
			this.save();
		});
		$("#btn-update").on("click", () => {	
			this.update();
		});
	},

	save: function() {
		//alert("user의 save 함수가 호출됨");
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		//console.log(data);

		//ajax 호출시 default가 비동기 호출
		$.ajax({
			//회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),	//http body 데이터
			contentType: "application/json; charset=utf-8",	//body데이터가 어떤 타입인지(MIME)
			dataType: "json"	//요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript오브젝트로 변경 //적지않아도 기본으로  object로 바꿔줌
		}).done(function(resp) {
			if(resp.status ===500){
			alert("회원가입에 실패하셨습니다.");
			}else{
			alert("회원가입이 완료되었습니다");
			location.href = "/";
			}
			
		}).fail(function(error) {
			alert(JSON.stringify(error));
			console.log("실패하였습니다.")
		});	//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
	},

	update: function() {
		//alert("user의 save 함수가 호출됨");
		let data = {
			id: $("#id").val(),
			username :$("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		$.ajax({
			//회원가입 수행 요청
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),	
			contentType: "application/json; charset=utf-8",	
			dataType: "json"	
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다");
			location.href = "/";
			console.log(resp)
		}).fail(function(error) {
			alert(JSON.stringify(error));
			console.log("실패하였습니다.")
		});	
	},

}

index.init();