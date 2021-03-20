package com.bid.tender.service.impl;

import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseServiceImpl;
import com.bid.tender.mapper.FileManageMapper;
import com.bid.tender.model.FileManage;
import com.bid.tender.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FileServiceImpl extends BaseServiceImpl<FileManage> implements FileService {
    private static Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);
    @Autowired
    private FileManageMapper fileManageMapper;



    @Override
    public int save(FileManage notice) {
        int count = 0;
        count = fileManageMapper.insert(notice);
        return count;
    }

    @Override
    public List<FileManage> findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
        return null;
    }

    @Override
    public List<FileManage> queryByRelationId(String relationId) {
        return fileManageMapper.queryByRelationId(relationId) ;
    }
}
