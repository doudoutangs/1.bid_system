package com.bid.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bid.common.web.PageInfo;
import com.bid.sys.model.SysRole;
import com.bid.sys.model.result.SysRoleVo;

public interface SysRoleMapper extends Mapper<SysRole>{
	
	/**
	 * 通过UserId查询角色
	 * @param userId
	 * @return
	 */
	List<SysRole> findByUserId(String userId);

	/**
	 * 根据UserId查询角色Id、Name集合
	 * @param userId
	 * @return Map<String,xx> value类型不确定
	 */
	@SuppressWarnings("unchecked")
	List<Map> findRoleIdAndNameByUserId(String userId);
	
	/**
	 * 查找角色列表
	 * @param pageInfo
	 * @return
	 */
	List<SysRoleVo> findRolePageCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);

	/**
	 * 统计角色
	 * @param pageInfo
	 * @return
	 */
	int findRolePageCount(PageInfo pageInfo);
	
	/**
	 * 查找所有角色
	 * @return
	 */
	List<SysRole> findRoleAll();

}