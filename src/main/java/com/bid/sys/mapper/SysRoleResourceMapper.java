package com.bid.sys.mapper;

import com.bid.sys.model.SysRoleResource;
import org.apache.ibatis.annotations.Param;

public interface SysRoleResourceMapper {

	/**
	 * 通过RoleId删除
	 * @param roleId
	 * @return
	 */
	int deleteByRoleId(String roleId);
	
    int deleteByPrimaryKey(@Param("roleId") String roleId, @Param("resourceId") String resourceId);

    int insert(SysRoleResource record);

    int insertSelective(SysRoleResource record);
}