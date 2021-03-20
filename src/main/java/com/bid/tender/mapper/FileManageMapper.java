package com.bid.tender.mapper;

import com.bid.tender.model.FileManage;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FileManageMapper  extends Mapper<FileManage> {
    List<FileManage> queryByRelationId(String relationId);
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(FileManage record);
//
//    int insertSelective(FileManage record);
//
//    FileManage selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(FileManage record);
//
//    int updateByPrimaryKey(FileManage record);
}