package com.bid.common.web.controller;

import com.bid.common.shiro.ShiroUser;
import com.bid.sys.model.SysUser;
import com.bid.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础 controller
 * @author sugar 2016-6-31 21:12:18
 */
public abstract class BaseController extends Controller{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserService sysUserService;
    
    /**
	 * 获取当前用户session
	 * @return session
	 */
	public static Session getSession(){
		Session session = SecurityUtils.getSubject().getSession();
		return session;
	}
    
    /**
	 * 获取当前用户对象shiro
	 * @return shirouser
	 */
	public static ShiroUser getShiroUser(){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
	
    /**
     * 获取当前登录用户对象
     * @return
     */
    public SysUser getCurrentUser() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        SysUser currentUser = sysUserService.selectByKey(user.userId);
        return currentUser;
    }
    
    /**
     * 获取当前登录用户id
     * @return
     */
    public String getUserId() {
        return getShiroUser().getUserId();
    }

    /**
     * 获取当前登录用户名
     * @return
     */
    public String getLoginName() {
        return getShiroUser().getLoginName();
    }
    
}
