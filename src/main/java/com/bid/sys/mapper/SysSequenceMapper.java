package com.bid.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bid.common.web.PageInfo;
import com.bid.sys.model.SysSequence;

public interface SysSequenceMapper extends Mapper<SysSequence>{
	
	/**
	 * 查询序列
	 * @param pageInfo
	 * @return
	 */
	List findSeqPageCondition(PageInfo pageInfo);
	
	/**
	 * 统计符合条件的序列
	 * @param pageInfo
	 * @return
	 */
	int countSeqPage(PageInfo pageInfo);

	/**
	 * 更新序列
	 * @param newSeq 新序列
	 * @param seqCode 序列代号
	 * @param currentSeq 修改前序列
	 * @return
	 */
	int updateCurrentSeq(@Param("newSeq")Integer newSeq, @Param("seqCode")String seqCode, @Param("currentSeq")Integer currentSeq);
	
}