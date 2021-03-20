package com.bid.sys.service.impl;

import com.bid.common.utils.PageHelperUtils;
import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseServiceImpl;
import com.bid.sys.mapper.SysLogMapper;
import com.bid.sys.model.SysLog;
import com.bid.sys.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements SysLogService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysLogServiceImpl.class);

    @Autowired
    private SysLogMapper sysLogMapper;


    @Override
    public List findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
        PageHelperUtils.startPage(pageInfo);
        return sysLogMapper.findLogPageCondition(pageInfo,condition);
    }


    /**
     * 保存日志
     * @param request
     * @param log
     */
    @Override
    public void save(HttpServletRequest request, Boolean saveParament, SysLog log) {
//        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
//        String os=userAgent.getOperatingSystem().getName();	//获取客户端操作系统
//        String browser=userAgent.getBrowser().getName();	//获取客户端浏览器
//        String operationCode=StringUtils.substringAfter(request.getRequestURI(),request.getContextPath());	//操作代号
//		String requestParam=(new JsonMapper()).toJson(request.getParameterMap());	//请求参数
//        SysLog sysLog =  new SysLog(log.getLogType(),log.getDescription());
//        sysLog.setCreateDate(log.getCreateDate() != null ? log.getCreateDate() : new Date());
//        sysLog.setOperateCode(operationCode);
//        if (saveParament){
//            sysLog.setRequestParam(requestParam);
//        }
//        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
//        if(shiroUser != null){
//            sysLog.setUserId(shiroUser.getUserId());
//            sysLog.setLoginName(shiroUser.getUserName());
//        } else {    //未登录
//            sysLog.setLoginName(log.getLoginName());
//        }
//        sysLog.setOs(os);
//        sysLog.setBrowser(browser);
//        String ip = IPUtil.getIpAddress(request);
//        sysLog.setIp(ip);
//        try {
//            sysLog.setMac(IPUtil.getMACAddress(ip));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        super.save(sysLog);
    }
    
    @Override
	public void save(HttpServletRequest request, SysLog sysLog) {
//		this.save(request, true, sysLog);
	}

}
