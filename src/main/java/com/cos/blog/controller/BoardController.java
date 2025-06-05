package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({"", "/"})
	public String index() {
		//application.yml 에서 mvc 설정을 prefix, subfix 설정으로 인해
		// /WEB-INF/views/index.jsp 를 찾아감
		return "index";
	}
}
