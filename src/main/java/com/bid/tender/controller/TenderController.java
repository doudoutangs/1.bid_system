package com.bid.tender.controller;

import com.bid.common.utils.DateUtils;
import com.bid.common.utils.ServletUtils;
import com.bid.common.web.PageInfo;
import com.bid.common.web.controller.BaseController;
import com.bid.tender.model.FileManage;
import com.bid.tender.model.Notice;
import com.bid.tender.model.Tender;
import com.bid.tender.service.FileService;
import com.bid.tender.service.NoticeService;
import com.bid.tender.service.TenderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 投标管理
 *
 * @author sugar 2016-8-29 14:00:09
 */
@Controller
@RequestMapping(value = "tender")
public class TenderController extends BaseController {
    @Autowired
    private TenderService tenderService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private FileService fileService;

    /**
     * 投标管理页面
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manager(Model model) {
        model.addAttribute("userId", getUserId());
        return "tender/tender";
    }

    /**
     * 投标列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("tender:tender:view")
    @RequestMapping(value = "/dataGrid/json", method = RequestMethod.POST)
    @ResponseBody

    public Object dataGrid(Integer page, Integer rows, String sort, String order, HttpServletRequest request) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = ServletUtils.getParmFilter(request);
        pageInfo.setPageResult(tenderService.findDataGrid(pageInfo, condition));
        return pageInfo;
    }

    /**
     * 增加投标页面
     *
     * @return
     */
    @RequiresPermissions("tender:tender:add")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage(Model model, String id) {
        Notice notice = noticeService.selectByKey(id);
        model.addAttribute("notice", notice);
        return "tender/tenderAdd";
    }

    /**
     * 添加投标
     *
     * @param tender
     * @return
     */
    @RequiresPermissions("tender:tender:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(Tender tender) {
        tender.setUserId(getUserId());
        tender.setTenderDate(DateUtils.getDate());
        String badId = tender.getBadId();
        String userId = tender.getUserId();
        List<Tender> list = tenderService.findByBadIdAndUserId(badId, userId);
        if (list.size() > 0) {
            return resultFail(" 请勿重复投标");
        }
        Notice notice = noticeService.selectByKey(badId);

        if (!"0".equals(notice.getStatus())) {
            return resultSuccess("已停止投标");
        }
        int result = tenderService.save(tender);
        if (result == 1) {
            return resultSuccess("添加成功！");
        } else {
            return resultFail("添加失败");
        }
    }
    /**
     * 投标详情页
     *
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("tender:tender:detail")
    @RequestMapping("/detailPage")
    public String detailPage(Model model, String id) {
        Tender tender = tenderService.selectByKey(id);
        String fileId = tender.getFileId();
        List<FileManage> list =  fileService.queryByRelationId(fileId);
        model.addAttribute("tender", tender);
        model.addAttribute("list", list);
        return "tender/tenderDetail";
    }
    /**
     * 编辑投标页
     *
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("tender:tender:edit")
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
        Tender tender = tenderService.selectByKey(id);
        List<FileManage> list =  fileService.queryByRelationId(tender.getFileId());
        model.addAttribute("tender", tender);
        model.addAttribute("list", list);
        return "tender/tenderEdit";
    }

    /**
     * 编辑投标
     *
     * @param tender
     * @return
     */
    @RequiresPermissions("tender:tender:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Tender tender) {
        Tender t = tenderService.selectByKey(tender.getId());
        if(!getUserId().equals(t.getUserId())){
            return resultFail("非当前用户添加记录无权修改");
        }
        int result = tenderService.updateNotNull(tender);
        if (result == 1) {
            return resultSuccess("编辑成功！");
        } else {
            return resultFail("编辑失败");
        }
    }

    /**
     * 删除投标
     *
     * @param id
     * @return
     */
    @RequiresPermissions("tender:tender:del")
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
        Tender t = tenderService.selectByKey(id);
        if(!getUserId().equals(t.getUserId())){
            return resultFail("非当前用户添加记录无权删除");
        }
        int result = tenderService.delete(id);
        if (result == 1) {
            return resultSuccess("删除成功！");
        } else {
            return resultFail("删除失败");
        }
    }

}