package com.bid.tender.service;

import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseService;
import com.bid.tender.model.FileManage;

import java.util.List;
import java.util.Map;

/**
 * 权限管理
 *
 * @author sugar 2016-7-5 16:23:31
 */
public interface FileService extends BaseService<FileManage> {


    int save(FileManage notice);
    /**
     * 查询列表
     *
     * @param pageInfo
     */
    List<FileManage> findDataGrid(PageInfo pageInfo, Map<String, Object> condition);
    List<FileManage> queryByRelationId(String relationId);
}
