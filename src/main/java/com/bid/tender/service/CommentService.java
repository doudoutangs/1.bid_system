package com.bid.tender.service;

import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseService;
import com.bid.tender.model.BadStatus;
import com.bid.tender.model.result.BadStatusVo;

import java.util.List;
import java.util.Map;

/**
 * 权限管理
 *
 * @author sugar 2016-7-5 16:23:31
 */
public interface CommentService extends BaseService<BadStatus> {


    /**
     * 查询公告列表
     *
     * @param pageInfo
     */
    List<BadStatusVo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition);

    /**
     * 发布公告
     *
     * @param badStatus
     */
    int save(BadStatus badStatus);

    List<BadStatus> findByBadIdAndUserId(String tenderId, String userId);

}
