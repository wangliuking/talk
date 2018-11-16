package com.talk.listener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.talk.common.UDPTest;
import com.talk.common.WebConstant;
import com.talk.dto.User;
import com.talk.service.UserService;

public class SessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		UDPTest u = new UDPTest();
		u.getIpAndPort();
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		User u = (User)session.getAttribute(WebConstant.SESSION_USER);
		UserService userService = (UserService)SpringInit.getApplicationContext().getBean("userService");
		userService.updateLogout(u.getUserId());
	}

}
