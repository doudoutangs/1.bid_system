package com.bid.tender.service;

import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseService;
import com.bid.sys.model.result.NoticeVo;
import com.bid.tender.model.Notice;

import java.util.List;
import java.util.Map;

/**
 * 权限管理
 *
 * @author sugar 2016-7-5 16:23:31
 */
public interface NoticeService extends BaseService<Notice> {


    /**
     * 查询公告列表
     *
     * @param pageInfo
     */
    List<NoticeVo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition);

    /**
     * 发布公告
     *
     * @param notice
     */
    int save(Notice notice);

}
