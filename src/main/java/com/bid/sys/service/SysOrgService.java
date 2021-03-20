package com.bid.sys.service;

import java.util.List;

import com.bid.common.web.Tree;
import com.bid.common.web.service.BaseService;
import com.bid.sys.model.SysOrg;
import com.bid.sys.model.result.SysOrgVo;

/**
 * 机构管理
 * @author sugar 2016-08-29 14:28:40
 */
public interface SysOrgService extends BaseService<SysOrg>{
	
	/**
	 * 获取所有机构树
	 * @return
	 */
	public List<Tree> findOrgAllTree();

	/**
     * 查询所有机构
     *
     * @return
     */
	List<SysOrgVo> findAllOrgVo();
	
}
