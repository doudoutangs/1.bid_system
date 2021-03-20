package com.bid.common.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * 通用Service接口
 */
@Service
public interface BaseService<T> {

    T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectByExample(Object example);

}
