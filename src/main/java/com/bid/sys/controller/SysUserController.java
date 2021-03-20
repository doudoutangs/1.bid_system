package com.bid.sys.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
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
import com.bid.sys.model.SysUser;
import com.bid.sys.model.result.SysUserVo;
import com.bid.sys.service.SysUserService;

/**
 * 用户管理
 * @author sugar 2016-8-21 13:42:46
 */
@Controller
@RequestMapping(value="sysUser")
public class SysUserController extends BaseController {
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 修改密码界面
	 * @return
	 */
	@RequestMapping(value = "/editPwdPage", method = RequestMethod.GET)
    public String editPwdPage() {
        return "sys/sysUserEditPwd";
    }
	
	/**
	 * 修改密码
	 * @param oldPwd
	 * @param pwd
	 * @return
	 */
	@RequestMapping(value = "/editUserPwd", method = RequestMethod.POST)
    @ResponseBody
	public Object editUserPwd(String oldPwd, String pwd) {
		SysUser sysUser = getCurrentUser();
		if (sysUserService.checkPassword(sysUser, oldPwd)) {
			sysUserService.updateUserPassword(sysUser, pwd);
			return resultSuccess("密码修改成功，请牢记！");
		}else{
			return resultFail("旧密码不正确");
		}
    }
	
	/**
	 * 获取当前用户信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/currentUserView", method = RequestMethod.GET)
    public String currentUserView(Model model) {
		SysUserVo sysUserVo =  sysUserService.findUserVoById(getUserId());
		model.addAttribute("userVo", sysUserVo);
        return "sys/sysUserView";
    }
	
	/**
	 * 编辑当前用户信息
	 * @return
	 */
	@RequestMapping(value = "/currentUserEdit", method = RequestMethod.POST)
	@ResponseBody
    public Object currentUserEdit(String sex, String phone, String email) {
		SysUser sysUser = getCurrentUser();
		//修改以下几个属性
		sysUser.setUserId(getUserId());
		sysUser.setSex(sex);
		sysUser.setPhone(phone);
		sysUser.setEmail(email);
		sysUserService.updateUserAllAndRole(sysUser,null);
        return resultSuccess("修改成功");
    }
	
	/**
	 * 用户管理页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
    public String manager() {
        return "sys/sysUser";
    }
	
	/**
	 * 用户列表
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "/dataGrid/json", method = RequestMethod.POST)
	@ResponseBody
	public Object dataGrid(SysUserVo sysUserVo, Integer page, Integer rows, String sort, String order, HttpServletRequest request) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = ServletUtils.getParmFilter(request);
		pageInfo.setPageResult(sysUserService.findDataGrid(pageInfo, condition));
        return pageInfo;
	 }
	
	
	/**
    * 增加用户页面
    * @return
    */
	@RequiresPermissions("sys:user:add")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "sys/sysUserAdd";
    }
    
	
    /**
     * 添加用户
     * @return
     */
	@RequiresPermissions("sys:user:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SysUserVo sysUserVo) {
        SysUser u = sysUserService.findUserByLoginName(sysUserVo.getLoginName());
        if (u != null) {
            return resultFail("用户名已存在!");
        }
        sysUserService.addUser(sysUserVo);
        return resultSuccess("添加成功");
    }
	
	
	 /**
     * 编辑用户页面
     * @param id
     * @param model
     * @return
     */
	@RequiresPermissions("sys:user:edit")
    @RequestMapping("/editPage")
    public String editPage(String id, Model model) {
    	SysUserVo sysUserVo = sysUserService.findUserVoById(id);
    	List<SysRole> sysRoleList = sysUserVo.getRoleList();
    	List<String> roleIds = new ArrayList<String>();
    	if (sysRoleList != null) {
    		for (SysRole sysRole : sysRoleList) {
    			if (sysRole != null) {
    				roleIds.add(sysRole.getRoleId());
    			}
    		}
    	}
    	model.addAttribute("roleIds", roleIds);
    	model.addAttribute("userVo", sysUserVo);
    	return "sys/sysUserEdit";
    }
	
    /**
     * 编辑用户
     * @param userVo
     * @return
     */
	@RequiresPermissions("sys:user:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SysUserVo userVo, HttpSession httpSession ){
    	SysUser user = sysUserService.findUserByLoginName(userVo.getLoginName());
        if (!user.getUserId().equals(userVo.getUserId())) {
            return resultFail("登录名已存在!");
        }
        SysUser sysUser = sysUserService.selectByKey(userVo.getUserId());
        sysUser.setLoginName(userVo.getLoginName());
        sysUser.setUserName(userVo.getUserName());
        sysUser.setSex(userVo.getSex());
        sysUser.setOrgId(userVo.getOrgId());
        sysUser.setUserStatus(userVo.getUserStatus());
        sysUser.setPhone(userVo.getPhone());
        sysUser.setEmail(userVo.getEmail());
        sysUser.setAddress(userVo.getAddress());
        sysUser.setDescription(userVo.getDescription());
        String roleIds = null;
        if (SecurityUtils.getSubject().isPermitted("sys:user:editRole")) {//判断 有修改角色的权限
			roleIds = userVo.getRoleIds() != null ? userVo.getRoleIds() : "";
        }
        sysUserService.updateUserAllAndRole(sysUser,roleIds);
        
        //清除该用户的权限缓存
        ShiroUtil.clearPermCacheByUserId(httpSession, sysUser.getUserId());
        return resultSuccess("修改成功");
        
    }
    
	 /**
     * 删除用户
     *
     * @param id
     * @return
     */
	@RequiresPermissions("sys:user:del")
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
        sysUserService.delete(id);
        return resultSuccess("删除成功！");
    }

}
