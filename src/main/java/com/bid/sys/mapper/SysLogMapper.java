package com.bid.sys.mapper;

import com.bid.common.web.PageInfo;
import com.bid.sys.model.SysLog;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysLogMapper extends Mapper<SysLog> {

    /**
     * 查询日志
     * @param pageInfo
     * @param condition
     * @return
     */
    List findLogPageCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
}