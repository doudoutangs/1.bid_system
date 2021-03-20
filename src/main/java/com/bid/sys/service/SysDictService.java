package com.bid.sys.service;

import java.util.List;

import com.bid.common.web.Tree;
import com.bid.common.web.service.BaseService;
import com.bid.sys.model.SysDict;
import com.bid.sys.model.result.SysDictVo;

/**
 * 字典管理
 *
 * @author sugar 2016-8-12 15:28:51
 */
public interface SysDictService extends BaseService<SysDict> {

    //查找字典
    List<SysDictVo> findDictAllVo(String dictType);
    
    /**
	 * 获取所有字典树
	 * @return
	 */
	public List<Tree> findDictAllTree(String dictType);

}
