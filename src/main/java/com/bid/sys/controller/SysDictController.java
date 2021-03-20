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
import com.bid.sys.model.SysDict;
import com.bid.sys.service.SysDictService;

/**
 * 字典管理
 * @author sugar 2016-9-12 08:31:16
 */
@Controller
@RequestMapping("/sysDict")
public class SysDictController extends BaseController {

    @Autowired
    private SysDictService sysDictService;

    /**
     * 所有字典树
     * @return
     */
    @RequestMapping("/allTree/json")
    @ResponseBody
    public Object allTree() {
        return sysDictService.findDictAllTree(null);
    }
    
    /**
     * 字典管理页面
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manager() {
    	return "sys/sysDict";
    }

    /**
     * 字典管理列表
     * @return
     */
    @RequiresPermissions("sys:dict:view")
    @RequestMapping(value = "/treeGrid/json", method = RequestMethod.POST)
    @ResponseBody
    public Object treeGrid() {
        return sysDictService.findDictAllVo(null);
    }

    /**
     * 添加页面
     * @return
     */
    @RequiresPermissions("sys:dict:add")
    @RequestMapping("/addPage")
    public String addPage() {
        return "sys/sysDictAdd";
    }

    /**
     * 添加字典数据
     * @param sysDict
     * @return
     */
    @RequiresPermissions("sys:dict:add")
    @RequestMapping("/add")
    @ResponseBody
    public Object add(SysDict sysDict) {
		if (StringUtils.isBlanks(sysDict.getDictPid())) {
            sysDict.setDictPid(null);
		}
    	sysDictService.save(sysDict);
        return resultSuccess("添加成功！");
    }


    /**
     * 编辑字典页面
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("sys:dict:edit")
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
        SysDict sysDict = sysDictService.selectByKey(id);
        model.addAttribute("sysDict", sysDict);
        return "sys/sysDictEdit";
    }

    /**
     * 编辑字典
     * @param dict
     * @return
     */
    @RequiresPermissions("sys:dict:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(SysDict dict) {
		SysDict sysDict = sysDictService.selectByKey(dict.getDictId());
        if (sysDict != null) {
            sysDict.setDictPid(StringUtils.isBlanks(dict.getDictPid())?null:dict.getDictPid());
            sysDict.setDictCode(dict.getDictCode());
            sysDict.setDictCode(dict.getDictCode());
            sysDict.setDictName(dict.getDictName());
            sysDict.setDictType(dict.getDictType());
            sysDict.setDictSeq(dict.getDictSeq());
            sysDict.setDescription(dict.getDescription());
            //PID可能为空且有外键约束，所以update全部
            sysDictService.updateAll(sysDict);
        }
        return resultSuccess("编辑成功！");
    }

    /**
     * 删除字典
     * @param id
     * @return
     */
    @RequiresPermissions("sys:resource:del")
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
        sysDictService.delete(id);
        return resultSuccess("删除成功！");
    }

}
