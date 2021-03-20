package com.bid.common.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bid.common.constant.Const;

/**
 * session监听器
 * @author sugar 2016-08-17 22:07:23
 */
//@WebListener 标注为监听器,可不用在web.xml中配置
public class SessionListener implements HttpSessionListener {
	
	private Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

	@SuppressWarnings("unchecked")
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();

		// 在application范围由一个HashSet集保存所有的session
		HashMap<String,HttpSession> sessionMap = (HashMap) application.getAttribute("sessions");
		if (sessionMap == null) {
			sessionMap = new HashMap();
			application.setAttribute("sessions", sessionMap);
		}

		LOGGER.info("sessions new：" + session.getId());
		// 可以在别处从application范围中取出sessions集合
		// 然后使用sessionMap.size()获取当前活动的session数，即为"在线人数"
	}

	@SuppressWarnings("unchecked")
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		HashMap<String,HttpSession> sessionMap = (HashMap) application.getAttribute("sessions");

		// 销毁的session均从HashMap集中移除
		String userId = (String) session.getAttribute(Const.SESSION_USER_ID); //Session中获取UserId
		sessionMap.remove(userId); //ServletContext中移除该用户SessionId
		LOGGER.info("sessions remove[userId:{}]：" + session.getId(),userId);
	}
  
}
