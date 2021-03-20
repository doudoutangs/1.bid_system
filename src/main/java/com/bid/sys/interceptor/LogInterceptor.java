package com.bid.sys.interceptor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bid.common.constant.Const;
import com.bid.common.mapper.JsonMapper;
import com.bid.common.shiro.ShiroUser;
import com.bid.common.utils.IPUtil;
import com.bid.sys.model.SysLog;
import com.bid.sys.service.SysLogService;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * 拦截器 日志记录
 * @author sugar 2016-07-02 23:32:03
 */
public class LogInterceptor implements HandlerInterceptor {

	private static Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);
	@Autowired
	private SysLogService sysLogService;
	
	private Long beginTime;// 1、开始时间
	private Long endTime;// 2、结束时间
	private boolean recordLog = false;//记录日志开关
	private int pathFilterType = 0;//路径过滤类型
	public List<String> filterPath = new ArrayList<String>();//过滤路径
	
	private String operationCode;//操作代号
	private String requestParam;//请求参数

	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		beginTime = System.currentTimeMillis();//计时
		//打印日志
		operationCode=StringUtils.substringAfter(request.getRequestURI(),request.getContextPath());	//操作代号
		requestParam=(new JsonMapper()).toJson(request.getParameterMap());	//请求参数
		String strMessage = String.format("[操作]:%s,[参数]:%s", operationCode, requestParam);
		LOGGER.debug(strMessage);
		return true;
	}

	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler, ModelAndView modelAndView) 
		throws Exception {
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		endTime = System.currentTimeMillis();
		if(isRecordLog(request)){
			Long executeTime = endTime - beginTime; //执行时间
			UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			String os=userAgent.getOperatingSystem().getName();	//获取客户端操作系统
			String browser=userAgent.getBrowser().getName();	//获取客户端浏览器
			ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			SysLog sysLog =  new SysLog(Const.LOG_TYPE_SYS);
			if(shiroUser != null){
				sysLog.setUserId(shiroUser.getUserId());
				sysLog.setLoginName(shiroUser.getUserName());
			}
			sysLog.setCreateDate(new Date());
			sysLog.setOperateCode(operationCode);
			sysLog.setRequestParam(requestParam);
			sysLog.setExecuteTime(executeTime);
			sysLog.setOs(os);
			sysLog.setBrowser(browser);
			String ip = IPUtil.getIpAddress(request);
			sysLog.setIp(ip);
			sysLog.setMac(IPUtil.getMACAddress(ip));
			//sysLog.setDescription(String.format("[操作]:%s,[参数]:%s", operationCode, requestParam));
//			sysLogService.save(sysLog);
//			LOGGER.info("日志入库:" + sysLog.toString());
		}
	}

	/**
	 * 是否记录日志
	 */
	private boolean isRecordLog(HttpServletRequest request) {
		String paths = request.getServletPath();
		if(recordLog && !request.getMethod().equals("GET") ){//日志记录开启状态 && 非GET请求
			switch (pathFilterType) {
			case 1://包含以下路径-记录日志
				for(String path : filterPath){
					return paths.contains(path);
				}
				return false;
			case 2://不含以下路径-记录日志
				for(String path : filterPath){
					return !paths.contains(path);
				}
				return true;
			default://记录日志
				return true;
			}
		}else{
			return false;
		}
	}
	
	public int getPathFilterType() {
		return pathFilterType;
	}
	public void setPathFilterType(int pathFilterType) {
		this.pathFilterType = pathFilterType;
	}
	public boolean isRecordLog() {
		return recordLog;
	}
	public void setRecordLog(boolean recordLog) {
		this.recordLog = recordLog;
	}
	public List<String> getFilterPath() {
		return filterPath;
	}
	public void setFilterPath(List<String> filterPath) {
		this.filterPath = filterPath;
	}

}
