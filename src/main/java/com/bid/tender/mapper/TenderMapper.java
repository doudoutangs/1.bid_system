package com.bid.tender.mapper;

import com.bid.common.web.PageInfo;
import com.bid.tender.model.Tender;
import com.bid.tender.model.result.TenderVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TenderMapper  extends Mapper<Tender> {
//    int deleteByPrimaryKey(Integer id);

    List<TenderVo> findPageCondition(@Param("pageInfo") PageInfo pageInfo, @Param("condition") Map<String, Object> condition);
    List<Tender> findByBadIdAndUserId(@Param("badId") String badId, @Param("userId") String userId);

//    int insertSelective(Tender record);
//
//    Tender selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Tender record);

}