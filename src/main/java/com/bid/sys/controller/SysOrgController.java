package com.bid.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bid.common.utils.StringUtils;
import com.bid.common.web.controller.BaseController;
import com.bid.sys.model.SysOrg;
import com.bid.sys.service.SysOrgService;

/**
 * 机构管理
 * @author sugar 2016-8-29 14:00:09
 */
@Controller
@RequestMapping(value="sysOrg")
public class SysOrgController extends BaseController{
	
	@Autowired
	private SysOrgService sysOrgService;
	
	/**
     * 所有机构-tree
     * @return
     */
    @RequestMapping("/allTree/json")
    @ResponseBody
    public Object allTree() {
        return sysOrgService.findOrgAllTree();
    }
	
	/**
	 * 机构管理页面
	 * @return
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String manager() {
        return "sys/sysOrg";
    }
    
    /**
     * 机构管理列表
     * @return
     */
    @RequiresPermissions("sys:org:view")
    @RequestMapping(value = "/treeGrid/json", method = RequestMethod.POST)
    @ResponseBody
    public Object treeGrid() {
        return sysOrgService.findAllOrgVo();
    }
    
   /**
    * 增加机构页面
    * @return
    */
    @RequiresPermissions("sys:org:add")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "sys/sysOrgAdd";
    }
    
    /**
     * 添加机构
     * @param sysOrg
     * @return
     */
    @RequiresPermissions("sys:org:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(SysOrg sysOrg){
    	if (StringUtils.isBlanks(sysOrg.getOrgPid())) {
    		sysOrg.setOrgPid(null);
    	}
    	sysOrgService.save(sysOrg);
		return resultSuccess("添加成功！");
    }
    
    /**
     * 编辑机构页
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("sys:org:edit")
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
    	SysOrg sysOrg = sysOrgService.selectByKey(id);
        model.addAttribute("sysOrg", sysOrg);
        return "sys/sysOrgEdit";
    }
    
    /**
     * 编辑机构
     * @param org
     * @return
     */
    @RequiresPermissions("sys:org:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SysOrg org) {
    	SysOrg SysOrg = sysOrgService.selectByKey(org.getOrgId());
    	SysOrg.setOrgPid(StringUtils.isBlanks(org.getOrgPid())?null:org.getOrgPid());
    	SysOrg.setOrgName(org.getOrgName());
    	SysOrg.setOrgSeq(org.getOrgSeq());
    	SysOrg.setOrgAddress(org.getOrgAddress());
    	SysOrg.setDescription(org.getDescription());
    	sysOrgService.updateAll(SysOrg);
        return resultSuccess("编辑成功！");
    }
    
    /**
     * 删除机构
     * @param id
     * @return
     */
    @RequiresPermissions("sys:org:del")
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
    	sysOrgService.delete(id);
        return resultSuccess("删除成功！");
    }
}
