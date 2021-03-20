package com.bid.sys.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bid.common.constant.Const;
import com.bid.common.utils.StringUtils;
import com.bid.common.web.controller.BaseController;
import com.bid.sys.model.SysResource;
import com.bid.sys.service.SysResourceService;

/**
 * 资源管理
 * @author sugar 2016-7-12 09:20:18
 */
@Controller
@RequestMapping("/sysResource")
public class SysResourceController extends BaseController {

    @Autowired
    private SysResourceService sysResourceService;
    
    /**
     * 角色拥有权限的资源Id
     * @return
     */
    @RequestMapping("/findResourceIdByRoleId/json")
    @ResponseBody
    public Object roleResourceIdList(String roleId) {
    	List<String> sysResourceId = sysResourceService.findResourceIdByRoleId(roleId);
        return resultSuccess(sysResourceId);
    }
   
    /**
     * 所有资源树
     * @return
     */
    @RequestMapping("/allTree/json")
    @ResponseBody
    public Object allTree() {
        return sysResourceService.findAllResouceTree(null);
    }
    
    /**
     * 所有菜单资源树
     * @return
     */
    @RequestMapping("/allMenuTree/json")
    @ResponseBody
    public Object allMenuTree() {
        return sysResourceService.findAllResouceTree(Const.RESOURCE_MENU);
    }
    

    /**
     * 用户首页导航菜单树
     * @return
     */
    @RequestMapping(value = "/userMenuTree/json", method = RequestMethod.POST)
    @ResponseBody
    public Object tree() {
        return sysResourceService.findTreeByUserId(getUserId(),Const.RESOURCE_MENU);
    }

    /**
     * 资源管理页面
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manager() {
    	return "sys/sysResource";
    }

    /**
     * 资源管理列表
     * @return
     */
    @RequiresPermissions("sys:resource:view")
    @RequestMapping(value = "/treeGrid/json", method = RequestMethod.POST)
    @ResponseBody
    public Object treeGrid() {
        return sysResourceService.findAllResourceVo();
    }

    /**
     * 添加资源页
     * @return
     */
    @RequiresPermissions("sys:resource:add")
    @RequestMapping("/addPage")
    public String addPage() {
        return "sys/sysResourceAdd";
    }

    /**
     * 添加资源
     * @param sysResource
     * @return
     */
    @RequiresPermissions("sys:resource:add")
    @RequestMapping("/add")
    @ResponseBody
    public Object add(SysResource sysResource) {
		if (StringUtils.isBlanks(sysResource.getResourcePid())) {
			sysResource.setResourcePid(null);
		}
    	sysResourceService.save(sysResource);
        return resultSuccess("添加成功！");
    }


    /**
     * 编辑资源页
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("sys:resource:edit")
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
        SysResource sysResource = sysResourceService.selectByKey(id);
        model.addAttribute("sysResource", sysResource);
        return "sys/sysResourceEdit";
    }

    /**
     * 编辑资源
     * @param resource
     * @return
     */
    @RequiresPermissions("sys:resource:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SysResource resource) {
		SysResource sysResource = sysResourceService.selectByKey(resource.getResourceId());
		sysResource.setResourcePid(StringUtils.isBlanks(resource.getResourcePid())?null:resource.getResourcePid());
		sysResource.setResourceName(resource.getResourceName());
		sysResource.setResourceType(resource.getResourceType());
		sysResource.setPermCode(resource.getPermCode());
		sysResource.setUrl(resource.getUrl());
		sysResource.setIcon(resource.getIcon());
		sysResource.setResourceSeq(resource.getResourceSeq());
		sysResource.setResourceType(resource.getResourceType());
		sysResource.setDescription(resource.getDescription());
		//PID可能为空且有外键约束，所以update全部
        sysResourceService.updateAll(sysResource);
        return resultSuccess("编辑成功！");
    }

    /**
     * 删除资源
     * @param id
     * @return
     */
    @RequiresPermissions("sys:resource:del")
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
        sysResourceService.delete(id);
        return resultSuccess("删除成功！");
    }

}
