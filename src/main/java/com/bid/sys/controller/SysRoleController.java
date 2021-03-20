package com.bid.sys.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bid.common.shiro.ShiroUtil;
import com.bid.common.utils.ServletUtils;
import com.bid.common.web.PageInfo;
import com.bid.common.web.controller.BaseController;
import com.bid.sys.model.SysRole;
import com.bid.sys.service.SysRoleService;

/**
 * 角色权限管理
 * @author sugar 2016-8-16 19:39:03
 */
@Controller
@RequestMapping(value="sysRole")
public class SysRoleController extends BaseController{
	
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
     * 所有角色树
     * @return
     */
    @RequestMapping("/allTree/json")
    @ResponseBody
    public Object allTree() {
        return sysRoleService.findRoleAllTree();
    }
	
	/**
	 * 角色管理页面
	 * @return
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String manager() {
        return "sys/sysRole";
    }
    
    /**
     * 角色列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("sys:role:view")
    @RequestMapping(value = "/dataGrid/json", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(Integer page, Integer rows, String sort, String order, HttpServletRequest request) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = ServletUtils.getParmFilter(request);
        pageInfo.setPageResult(sysRoleService.findDataGrid(pageInfo, condition));
        return pageInfo;
    }
    
   /**
    * 增加角色页面
    * @return
    */
    @RequiresPermissions("sys:role:add")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "sys/sysRoleAdd";
    }
    
    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    @RequiresPermissions("sys:role:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SysRole sysRole){
    	sysRoleService.save(sysRole);
		return resultSuccess("添加成功！");
    }
    
    /**
     * 编辑角色页
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("sys:role:edit")
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
    	SysRole sysRole = sysRoleService.selectByKey(id);
        model.addAttribute("sysRole", sysRole);
        return "sys/sysRoleEdit";
    }
    
    /**
     * 编辑角色
     * @param role
     * @return
     */
    @RequiresPermissions("sys:role:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SysRole role) {
    	SysRole sysRole = new SysRole();
    	sysRole.setRoleId(role.getRoleId());
    	sysRole.setRoleName(role.getRoleName());
    	sysRole.setRoleSeq(role.getRoleSeq());
    	sysRole.setRoleStatus(role.getRoleStatus());
    	sysRole.setDescription(role.getDescription());
    	sysRoleService.updateNotNull(sysRole);
        return resultSuccess("编辑成功！");
    }
    
    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequiresPermissions("sys:role:del")
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
    	sysRoleService.delete(id);
        return resultSuccess("删除成功！");
    }
    
    /**
     * 授权菜单页面
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("sys:role:grant")
    @RequestMapping("/grantPage")
    public String grantPage(String id, Model model) {
        model.addAttribute("id", id);
        return "sys/sysRoleGrant";
    }
    
    /**
     * 角色授权菜单
     * @param id
     * @param resourceIds
     * @return
     */
    @RequiresPermissions("sys:role:grant")
	@RequestMapping("/grant")
    @ResponseBody
    public Object grant(String id, String resourceIds, HttpSession httpSession) {
    	sysRoleService.updateRoleResourceByRoleId(id, resourceIds);
    	ShiroUtil.clearPermCacheByRoleId(httpSession, id);
        return resultSuccess("授权成功！");
    }
}
