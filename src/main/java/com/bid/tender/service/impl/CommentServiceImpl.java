package com.bid.tender.service.impl;

import com.bid.common.utils.PageHelperUtils;
import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseServiceImpl;
import com.bid.tender.mapper.BadStatusMapper;
import com.bid.tender.model.BadStatus;
import com.bid.tender.model.result.BadStatusVo;
import com.bid.tender.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends BaseServiceImpl<BadStatus> implements CommentService {

    private static Logger LOGGER = LoggerFactory.getLogger(CommentServiceImpl.class);
    @Autowired
    private BadStatusMapper badStatusMapper;


    @Override
    public List<BadStatusVo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
        PageHelperUtils.startPage(pageInfo);
        return badStatusMapper.findPageCondition(pageInfo, condition);
    }

    @Override
    public int save(BadStatus tender) {
        int count = 0;
        count = badStatusMapper.insert(tender);
        return count;
    }

    @Override
    public List<BadStatus>findByBadIdAndUserId(String tenderId, String userId) {
        return badStatusMapper.findByBadIdAndUserId(tenderId, userId);
    }
}
