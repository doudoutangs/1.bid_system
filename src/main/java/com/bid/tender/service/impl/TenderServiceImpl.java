package com.bid.tender.service.impl;

import com.bid.common.utils.PageHelperUtils;
import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseServiceImpl;
import com.bid.tender.mapper.TenderMapper;
import com.bid.tender.model.Tender;
import com.bid.tender.model.result.TenderVo;
import com.bid.tender.service.TenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TenderServiceImpl extends BaseServiceImpl<Tender> implements TenderService {

    private static Logger LOGGER = LoggerFactory.getLogger(TenderServiceImpl.class);
    @Autowired
    private TenderMapper tenderMapper;


    @Override
    public List<TenderVo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
        PageHelperUtils.startPage(pageInfo);
        return tenderMapper.findPageCondition(pageInfo, condition);
    }

    @Override
    public int save(Tender tender) {
        int count = 0;
        count = tenderMapper.insert(tender);
        return count;
    }

    @Override
    public List<Tender>findByBadIdAndUserId(String badId, String userId) {
        return tenderMapper.findByBadIdAndUserId(badId, userId);
    }
}
