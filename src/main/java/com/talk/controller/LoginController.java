package com.talk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.talk.common.WebConstant;
import com.talk.dto.User;
import com.talk.service.UserService;

/**
 * 登录处理类
 */
@Controller
@RequestMapping("/customer")
public class LoginController {
	@Resource
	private UserService userService;
	/**
	 * ajax登录
	 * @param userId
	 * @param password
	 * @param vcode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/loginAjax")
	public Map<String,Object> login(String userName,String password,HttpServletRequest req){
			String browserType = req.getParameter("browType");
			Map<String,Object> map = new HashMap<>();
			//获取ServletAPI
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
			User u = new User();
			u.setUserName(userName);
			u.setPassword(password);
			//查询登录用户是否存在
			User user = userService.getUserByUserIdAndByPassword(u);			
			if(user != null){
				//将当前浏览器类型存入user
				user.setBrowserType(browserType);
				//存在将用户添加到session中方便后面使用
				request.getSession().setAttribute(WebConstant.SESSION_USER, user);
				if("admin".equals(user.getUserId())){
					userService.updateLogin(user.getUserId());
					map.put("tip", "超级管理员登录成功");
					map.put("status", 0);
				}else{
					userService.updateLogin(user.getUserId());
					map.put("tip", "普通用户登录成功");
					map.put("status", 1);
				}
			}else{
				map.put("tip", "用户名或密码不正确！");
				map.put("status", 2);
			}
			
		/*
		 * 	登录成功 = 0
			验证码不正确 = 1
			账号或密码不能为空 = 2
			账号或密码格式不正确 = 3
			用户还未激活 = 4
		 */
		return map;
	}
	/**
	 * 用户注销处理方法
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "index";
	}
}
