package com.bid.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bid.common.exception.ServiceException;
import com.bid.common.web.Tree;
import com.bid.common.web.service.BaseServiceImpl;
import com.bid.sys.mapper.SysDictMapper;
import com.bid.sys.model.SysDict;
import com.bid.sys.model.result.SysDictVo;
import com.bid.sys.service.SysDictService;

@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDict> implements SysDictService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysDictServiceImpl.class);

    @Autowired
    private SysDictMapper sysDictMapper;


	@Override
	public List<SysDictVo> findDictAllVo(String dictType) {
		return sysDictMapper.findDicAlltVo(dictType);
	}
	
	@Override
	public List<Tree> findDictAllTree(String dictType) {
		List<Tree> TreeList = new ArrayList<Tree>();
		List<SysDictVo> dictList = sysDictMapper.findDicAlltVo(dictType);
		for (SysDictVo sysDictVo : dictList) {
			if (sysDictVo != null) {
				Tree tree = new Tree();
				tree.setId(sysDictVo.getDictId());
				tree.setText(sysDictVo.getDictName());
				tree.setPid(sysDictVo.getDictPid());
				Map<String,String> treeAttributes = new HashMap<String,String>();
				treeAttributes.put("dictCode",sysDictVo.getDictCode());
				tree.setAttributes(treeAttributes);
				TreeList.add(tree);
			}
		}
		return TreeList;
	}

	@Override
	public int save(SysDict sysDict) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("dictType",sysDict.getDictType());
		condition.put("dictCode",sysDict.getDictCode());
		List<SysDict> sysDicts = sysDictMapper.findByCondition(condition);
		//校验字典数据是否冲突
		if (sysDicts != null && sysDicts.size() > 0) {
			throw new ServiceException("操作失败，请检查：类型|参数，是否已存在");
		}
		return sysDictMapper.insert(sysDict);
	}

	@Override
	public int updateAll(SysDict sysDict) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("dictType",sysDict.getDictType());
		condition.put("dictCode",sysDict.getDictCode());
		List<SysDict> sysDicts = sysDictMapper.findByCondition(condition);
		//校验字典数据是否冲突
		if (sysDicts != null && sysDicts.size() > 0 ) {
			for (SysDict dict : sysDicts) {
				if (dict != null && !dict.getDictId().equals(sysDict.getDictId())){
					throw new ServiceException("操作失败，请检查：类型|参数，是否已存在");
				}
			}

		}
		return super.updateAll(sysDict);
	}
}
