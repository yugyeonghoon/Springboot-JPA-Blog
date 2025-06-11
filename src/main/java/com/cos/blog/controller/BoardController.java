package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	// @AuthenticationPrincipal PrincipalDetail principal  <이걸 index() 파라미터에 집어 넣으면 세션 확인 가능 아니면 위에 방법으로
	
	@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {	//컨트롤러에서 세션을 어떻게 찾는지 ?
		//application.yml 에서 mvc 설정을 prefix, subfix 설정으로 인해
		// /WEB-INF/views/index.jsp 를 찾아감
		System.out.println("로그인한 사용자 아이디 :" + principal.getUsername());
		return "index";
	}
}
