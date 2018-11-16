package com.talk.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.talk.service.UserService;
/**
 * 后台请求处理类
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Resource
	private UserService userService;
	
	/**
	 * 超级管理员
	 * @return
	 */
	@RequestMapping(value="/important")
	public String important(){
		return "main";
	}
	
	/**
	 * 普通用户
	 * @return
	 */
	@RequestMapping(value="/normal")
	public String normal(){
		return "normal";
	}
	
	/**
	 * 管理员注销处理方法
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "login";
	}
	
	
}
