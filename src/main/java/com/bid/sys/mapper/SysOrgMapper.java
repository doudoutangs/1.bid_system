package com.bid.sys.mapper;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.bid.sys.model.SysOrg;
import com.bid.sys.model.result.SysOrgVo;

public interface SysOrgMapper extends Mapper<SysOrg>{
	
	/**
	 * 查询所有OrgVo
	 * @return
	 */
	List<SysOrgVo> findAllOrgVo();
	
	/**
	 * 查询所有部门
	 * @return
	 */
	List<SysOrg> findAllOrg();
	
}