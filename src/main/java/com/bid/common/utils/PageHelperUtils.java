package com.bid.common.utils;

import com.bid.common.web.PageInfo;
import com.github.pagehelper.PageHelper;

/**
 * 分页工具
 * @author sugar 2016-09-03 23:35:39
 *
 */
public class PageHelperUtils {
	
	public static void startPage(PageInfo pageInfo){
		PageHelper.startPage(pageInfo.getNowpage(), pageInfo.getPagesize());
	}
	
	public static void startPage(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
	}

}
