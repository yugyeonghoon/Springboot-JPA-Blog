package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML파일)
//@Controller


// 사용자가 요청 -> 응답(data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest :";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("Ssar").password("1234").email("aaaa@naver.com").build();
		System.out.println(TAG+"getter :" +m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter :" + m.getUsername());
		return "lombokTest 완료";
	}
	
	
	//인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
	//http://localhost:8000/blog/http/get(select)
	@GetMapping("/http/get")
	public String getTest(Member m) { //?id=1&username=aa&password=1234&email=ssar@naver.com //MessageConverter ( 스프링부트)
		//get 방식은 쿼리스트링 방식밖에없음
		
		return "get 요청:  " + m.getId() +","+ m.getUsername() + m.getPassword() + m.getEmail();
	}
	
	//http://localhost:8092/http/post(insert)
	@PostMapping("/http/post") //text/plain, application/json
	public String postTest(@RequestBody Member m) { //MessageConverter ( 스프링부트)
		return "post 요청 :" + m.getId() +","+ m.getUsername() + m.getPassword() + m.getEmail();
	}
	
	//http://localhost:8092/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 :" + m.getId() +","+ m.getUsername() + m.getPassword() + m.getEmail();
	}
	
	//http://localhost:8092/http/delete(delete)
	@DeleteMapping("/http/put")
	public String deleteTest() {
		return "delete 요청";
	}
}
