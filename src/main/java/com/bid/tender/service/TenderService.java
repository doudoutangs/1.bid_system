package com.bid.tender.service;

import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseService;
import com.bid.tender.model.Tender;
import com.bid.tender.model.result.TenderVo;

import java.util.List;
import java.util.Map;

/**
 * 权限管理
 *
 * @author sugar 2016-7-5 16:23:31
 */
public interface TenderService extends BaseService<Tender> {


    /**
     * 查询公告列表
     *
     * @param pageInfo
     */
    List<TenderVo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition);

    /**
     * 发布公告
     *
     * @param tender
     */
    int save(Tender tender);

    List<Tender> findByBadIdAndUserId(String badId, String userId);

}
