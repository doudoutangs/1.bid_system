package com.bid.tender.mapper;

import com.bid.common.web.PageInfo;
import com.bid.sys.model.result.NoticeVo;
import com.bid.tender.model.Notice;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface NoticeMapper  extends Mapper<Notice>{

	/**
	 * 查找角色列表
	 * @param pageInfo
	 * @return
	 */
	List<NoticeVo> findPageCondition(@Param("pageInfo") PageInfo pageInfo, @Param("condition") Map<String, Object> condition);

	/**
	 * 统计角色
	 * @param pageInfo
	 * @return
	 */
//	int findRolePageCount(PageInfo pageInfo);

	/**
	 * 查找所有角色
	 * @return
	 */
//	List<Notice> findRoleAll();

}