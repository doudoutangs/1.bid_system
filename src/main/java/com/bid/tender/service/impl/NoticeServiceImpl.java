package com.bid.tender.service.impl;

import com.bid.common.utils.PageHelperUtils;
import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseServiceImpl;
import com.bid.sys.model.result.NoticeVo;
import com.bid.tender.mapper.NoticeMapper;
import com.bid.tender.model.Notice;
import com.bid.tender.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl extends BaseServiceImpl<Notice> implements NoticeService {

    private static Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);
    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public List<NoticeVo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition) {
        PageHelperUtils.startPage(pageInfo);
        return noticeMapper.findPageCondition(pageInfo, condition);
    }

    @Override
    public int save(Notice notice) {
        int count = 0;
        count = noticeMapper.insert(notice);
        return count;
    }
}
