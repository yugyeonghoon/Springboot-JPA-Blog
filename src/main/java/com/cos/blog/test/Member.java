package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@Data // getter , setter 두개 한번에

//@RequiredArgsConstructor	// final이 붙은 애들에 한해 생성자(constructor)를 만들어줌
@NoArgsConstructor	// 빈 생성자

public class Member {
	private  int id;
	private  String username;
	private  String password;
	private  String email;
	
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
}
