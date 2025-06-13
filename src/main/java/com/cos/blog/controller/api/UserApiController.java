package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return 필요
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);		//Java오브젝트를 JSON으로 변환해서 리턴(JACCKSON)
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user){	//@RequestBody 가 없으면 Json 형태를 받지 못하고, 키-밸류로만 받을 수 있음
		userService.회원수정(user);
		//여기서 트랜잭션이 종료되기 때문에 DB에 값은 변경이 되었지만 세션값은 변경되지 않은 상태이기 때문에 직접 변경해줘야 함.
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
