package com.bid.sys.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseService;
import com.bid.sys.model.SysLog;

/**
 * 日志管理
 *
 * @author sugar 2016-9-17 14:18:37
 */
public interface SysLogService extends BaseService<SysLog> {

    /**
     * 查询日志列表
     * @param pageInfo
     * @param condition
     * @return
     */
    List findDataGrid(PageInfo pageInfo, Map<String, Object> condition);
    
    /**
     * 保存日志
     * @param request
     * @param saveParament 默认保存请求参数
     * @param sysLog
     * @return
     */
    public void save(HttpServletRequest request, SysLog sysLog);
    public void save(HttpServletRequest request, Boolean saveParament, SysLog sysLog);

}
