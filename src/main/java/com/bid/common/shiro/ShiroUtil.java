package com.bid.common.shiro;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Shiro 权限操作工具类
 * @author sugar 2016-8-21 21:33:46
 */
public class ShiroUtil {
	
	/**
	 * 通过UserId删除用户权限缓存
	 * @param httpSession
	 * @param roleId
	 */
	@SuppressWarnings("unchecked")
	public static void clearPermCacheByUserId(HttpSession httpSession, String userId){
		HashMap<String, HttpSession> sessionAll=(HashMap<String, HttpSession>) httpSession.getServletContext().getAttribute("sessions");
    	if (sessionAll != null) {
    		HttpSession userSession = sessionAll.get(userId);//获取该用户Session
    		if (userSession != null) {
    			PrincipalCollection principals = (PrincipalCollection)userSession.getAttribute(userId);
    			if (principals != null) {
					clearUserPermCache(principals);//清空该用户权限缓存
					userSession.removeAttribute(userId);//移除Session值：principals
				}
    		}
    	}
	}
	
	/**
	 * 通过RoleId删除所有用户权限缓存
	 * @param httpSession
	 * @param roleId
	 */
	@SuppressWarnings("unchecked")
	public static void clearPermCacheByRoleId(HttpSession httpSession, String roleId){
		HashMap<String, HttpSession> sessionAll=(HashMap<String, HttpSession>)httpSession.getServletContext().getAttribute("sessions");
    	if(sessionAll != null) {
    		PrincipalCollection principals = null;
    		HttpSession userSession = null;
    		ShiroUser shiroUser = null;
    		for (Entry<String, HttpSession> entry : sessionAll.entrySet()) { //循环所有在线用户的Session
    			userSession = entry.getValue();
				String userId = entry.getKey();
				principals = (PrincipalCollection)userSession.getAttribute(userId); 
				if (principals != null) {
					shiroUser = (ShiroUser)principals.getPrimaryPrincipal();//获取当前循环用户ShiroUser信息
					Map<String, String> roleMap = shiroUser.getRoleMap();//获取角色集合
					if (roleMap != null){
						for (Map.Entry<String, String> sysRoleEntry : roleMap.entrySet()) {
							String roleCode = sysRoleEntry.getKey();
							if (roleId.equals(roleCode)) { //用户的角色 包含 当前修改的角色权限
								clearUserPermCache(principals);//清空该用户权限缓存
								userSession.removeAttribute(userId);//移除Session值：principals
								break;
							}
						}
					}
				}
    		}
    	}
	}
	
	/**
	 * 清空用户的权限缓存
	 */
	private static void clearUserPermCache(PrincipalCollection principals){
		RealmSecurityManager securityManager =  (RealmSecurityManager) SecurityUtils.getSecurityManager();
		ShiroRealm userRealm = (ShiroRealm) securityManager.getRealms().iterator().next();
	    userRealm.clearCachedAuthorizationInfo(principals);
	}

}
