package com.bid.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bid.common.constant.Const;
import com.bid.common.utils.ServletUtils;
import com.bid.common.web.PageInfo;
import com.bid.common.web.controller.BaseController;
import com.bid.sys.model.result.SysUserVo;
import com.bid.sys.service.SysDictService;
import com.bid.sys.service.SysLogService;

/**
 * 日志查询
 * @author sugar 2016-09-17 18:02:23
 */
@Controller
@RequestMapping(value="sysLog")
public class SysLogController extends BaseController {
	
	@Autowired
	private SysLogService sysLogService;

	@Autowired
    private SysDictService sysDictService;
	
	/**
	 * 所有日志类型-tree
	 * @return
	 */
	@RequestMapping("/allTypeTree/json")
	@ResponseBody
	public Object allTypeTree() {
		return sysDictService.findDictAllTree(Const.DICT_TYPE_LOG_TYPE);
	}

	/**
	 * 日志查询页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String manager() {
        return "sys/sysLog";
    }
	
	/**
	 * 日志列表
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = "/dataGrid/json", method = RequestMethod.POST)
	@ResponseBody
	public Object dataGrid(SysUserVo sysUserVo, Integer page, Integer rows, String sort, String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(sysLogService.findDataGrid(pageInfo, condition));
        return pageInfo;
	 }
}
