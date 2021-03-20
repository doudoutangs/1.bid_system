package com.bid.sys.service;

import java.util.List;
import java.util.Map;

import com.bid.common.web.PageInfo;
import com.bid.common.web.service.BaseService;
import com.bid.sys.model.SysUser;
import com.bid.sys.model.result.SysUserVo;

/**
 * 用户管理
 * @author sugar 2016-7-2 21:28:31
 */
public interface SysUserService extends BaseService<SysUser>{
	
	/**加密方法*/
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;	//盐长度
	
    /**
     * 通过用户名查询用户
     *
     * @param username
     * @return
     */
    SysUser findUserByLoginName(String username);
    
    /**
     * 查询用户列表
     * @param pageInfo 
     * @param pageInfo
     */
    List<SysUserVo> findDataGrid(PageInfo pageInfo, Map<String, Object> condition);

	/**
	 * 添加用户
	 * @param sysUserVo
	 * @return
	 */
	int addUser(SysUserVo sysUserVo);

	/**
	 * 通过用户Id查找用户信息
	 * @param userId
	 * @return
	 */
	SysUserVo findUserVoById(String userId);

	/**
	 * 更新用户全部信息和角色
	 * @param sysUser
	 * @param roleIds
	 */
	int updateUserAllAndRole(SysUser sysUser, String roleIds);
	
	/**
	 * 校验旧密码是否正确
	 * @param user
	 * @param oldPwd
	 * @return
	 */
	boolean checkPassword(SysUser user, String oldPwd);

	/**
	 * 更新用户密码
	 * @param user
	 * @param pwd
	 */
	void updateUserPassword(SysUser user, String pwd);

}
