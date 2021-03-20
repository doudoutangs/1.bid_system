package com.bid.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.bid.sys.model.SysResource;
import com.bid.sys.model.result.SysResourceVo;

public interface SysResourceMapper extends Mapper<SysResource>{

	/**
	 * 查询子节点个数
	 * @param resourceId
	 * @return
	 */
	int countChildByPid(String resourceId);
	
	/**
	 * 通过角色Id获取拥有权限的资源Id
	 * @param roleId
	 * @return
	 */
	List<String> findResourceIdByRoleId(String roleId);
	
	
	/**
	 * 通过角色Id获取拥有权限的资源(自定义)编号
	 * @param roleId
	 * @return
	 */
	List<String> findPermCodeListByRoleId(@Param("roleId")String roleId,@Param("resourceStatus")String resourceStatus);
	
	/**
	 * 资源列表查询
	 * @return
	 */
	List<SysResourceVo> findResourceVo(@Param("resourceType")String resourceType);
	
	/**
	 * 通过角色Id获取拥有权限的资源
	 * @param roleId
	 * @return
	 */
	List<SysResourceVo> findResourceByRoleId(@Param("roleId")String roleId, @Param("resourceType")String resourceType);
	
	/**
	 * 删除子资源
	 * @param resourceId
	 * @return
	 */
    int deleteChildByPid(String resourceId);

}