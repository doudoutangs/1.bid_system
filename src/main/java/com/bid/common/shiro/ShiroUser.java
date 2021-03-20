package com.bid.common.shiro;

import java.io.Serializable;
import java.util.Map;

import com.google.common.base.Objects;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author sugar 2016-6-26 09:21:33
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;
    public String userId;
    public String loginName;
    public String userName;
    public Map<String, String> roleMap;

    public ShiroUser(String userId, String loginName, String userName) {
		super();
		this.userId = userId;
		this.loginName = loginName;
		this.userName = userName;
	}
    
	public String getUserId() {
		return userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getUserName() {
		return userName;
	}

	public Map<String, String> getRoleMap() {
		return roleMap;
	}

	/**
	 * 重载hashCode,只计算loginName;
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(loginName);
	}

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return userName;
    }
}