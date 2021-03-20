package com.bid.tender.mapper;

import com.bid.common.web.PageInfo;
import com.bid.tender.model.BadStatus;
import com.bid.tender.model.result.BadStatusVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface BadStatusMapper  extends Mapper<BadStatus> {
    /**
     * 查找角色列表
     * @param pageInfo
     * @return
     */
    List<BadStatusVo> findPageCondition(@Param("pageInfo") PageInfo pageInfo, @Param("condition") Map<String, Object> condition);
    List<BadStatus> findByBadIdAndUserId(@Param("tenderId") String tenderId, @Param("userId") String userId);
}