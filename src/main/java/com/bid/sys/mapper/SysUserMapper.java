package com.bid.sys.mapper;

import com.bid.common.web.PageInfo;
import com.bid.sys.model.SysUser;
import com.bid.sys.model.result.SysUserVo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser>{
	
	/**
	 * 用户列表
	 * @param pageInfo 
	 * @param pageInfo
	 * @return
	 */
	List<SysUserVo> findUserPageCondition(@Param("pageInfo")PageInfo pageInfo, @Param("condition")Map<String, Object> condition);
	
	/**
     * 通过登录用户名查询用户
     *
     * @param loginname
     * @return
     */
	SysUser findUserByLoginName(String loginname);
	
	/**
	 * 通过用户Id查找用户信息
	 * @param userId
	 * @return
	 */
	SysUserVo findUserVoById(String userId);
	
}