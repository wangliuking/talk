package com.talk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.talk.common.WebConstant;
import com.talk.dto.User;
/**
 * 登录检查拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	/**
	 * 在Controller类调用之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		//获取Session
		HttpSession session = request.getSession();
		//获取Session中存储的登录用户数据
		User user = (User)session.getAttribute(WebConstant.SESSION_USER);
		//判断用户数据是否存在
		if(user != null){
			//继续执行Controller请求
			return true;
		}else{
			//用户没有登录，重新登录
			session.invalidate();//消除session
			request.getRequestDispatcher("/customer/index").forward(request, response);
			return false;
		}
	}
}
