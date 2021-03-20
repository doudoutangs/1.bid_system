package com.bid.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bid.sys.model.SysUserRole;

public interface SysUserRoleMapper {

	/**
	 * 通过UserId查询角色Id集合
	 * @param userId
	 * @return
	 */
	List<String> findRoleIdListByUserId(String userId);
	
	/**
	 * 通过UserId删除
	 * @param userId
	 * @return
	 */
	int deleteByUserId(String userId);
	
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") String roleId);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}