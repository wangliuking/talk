package com.talk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录页面跳转处理
 */
@Controller
@RequestMapping("/customer")
public class IndexController {
	@RequestMapping(value="/index")
	public String loginPage(){
		return "login";	
	}
	
}
