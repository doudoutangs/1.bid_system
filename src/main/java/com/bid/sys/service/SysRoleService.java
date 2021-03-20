package com.bid.sys.service;

import java.util.List;
import java.util.Map;

import com.bid.common.web.PageInfo;
import com.bid.common.web.Tree;
import com.bid.common.web.service.BaseService;
import com.bid.sys.model.SysRole;
import com.bid.sys.model.result.SysRoleVo;

/**
 * 权限管理
 * @author sugar 2016-7-5 16:23:31
 */
public interface SysRoleService extends BaseService<SysRole>{
	
	/**
	 * 获取所有角色树
	 * @return
	 */
	public List<Tree> findRoleAllTree();
    
    /**
     * 根据UserId查询角色Id、Name集合
     * @param userId
     * @return
     */
    @SuppressWarnings("unchecked")
	List<Map> findRoleIdAndNameByUserId(String userId);

    /**
     * 查询角色列表
     * @param pageInfo
     */
	List<SysRoleVo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition);

	/**
	 * 角色授权菜单
	 * @param roleId
	 * @param resourceIds
	 */
	int updateRoleResourceByRoleId(String roleId, String resourceIds);
	
}
