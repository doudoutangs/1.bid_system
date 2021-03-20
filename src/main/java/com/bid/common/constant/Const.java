package com.bid.common.constant;

/**
 * 全局常量
 */
public class Const {
	
	/**
	 * ServletContext中,保存在线用户Session信息
	 * 		 parm:'sessions'  value:HashMap<String,HttpSession>{Map中key:userId}
	 * session
	 * 		 parm:userId value:principals
	 */
	public static final String SESSION_USER_ID = "session_userId";//用户ID
	
	/**
	 * 用户状态
	 */
	public static final String USER_TATUS_OFF = "0";//停用
	
	/**
	 * 资源类型
	 */
	public static final String RESOURCE_MENU = "1"; // 菜单
	public static final String RESOURCE_BUTTON = "2"; // 按钮
	
	/**
	 * 资源状态
	 */
	public static final String RESOURCE_TATUS_ON = "1";//启用
	public static final String RESOURCE_TATUS_OFF = "0";//停用

	/**
	 * 字典类型
	 */
	public static final String DICT_TYPE_LOG_TYPE = "logType"; //日志类型

	/**
	 * 日志类型
	 */
	public static final String LOG_TYPE_SYS = "system";//系统日志
	public static final String LOG_TYPE_LOGIN = "login";//登陆日志
	public static final String LOG_TYPE_EXCEPTION = "exception";//导常日志
	
	
	/**
	 * 数据源
	 */
	public static final String DATASOURCE_MASTER = "master";
	public static final String DATASOURCE_SLAVE = "slave";
	
}
