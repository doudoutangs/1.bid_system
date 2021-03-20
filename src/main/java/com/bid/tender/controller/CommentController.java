package com.bid.tender.controller;

import com.bid.common.constant.TenderConst;
import com.bid.common.utils.ServletUtils;
import com.bid.common.web.PageInfo;
import com.bid.common.web.controller.BaseController;
import com.bid.tender.model.BadStatus;
import com.bid.tender.model.Notice;
import com.bid.tender.model.Tender;
import com.bid.tender.service.CommentService;
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
 * 评标管理
 *
 * @author sugar 2016-8-29 14:00:09
 */
@Controller
@RequestMapping(value = "comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private TenderService tenderService;
    @Autowired
    private NoticeService noticeService;

    /**
     * 评标管理页面
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manager(Model model) {
        model.addAttribute("userId", getUserId());
        return "comment/comment";
    }

    /**
     * 评标列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("tender:comment:view")
    @RequestMapping(value = "/dataGrid/json", method = RequestMethod.POST)
    @ResponseBody

    public Object dataGrid(Integer page, Integer rows, String sort, String order, HttpServletRequest request) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = ServletUtils.getParmFilter(request);
        pageInfo.setPageResult(commentService.findDataGrid(pageInfo, condition));
        return pageInfo;
    }

    /**
     * 增加评标页面
     *
     * @return
     */
    @RequiresPermissions("tender:comment:add")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage(Model model, String id) {
        Tender tender = tenderService.selectByKey(id);
        model.addAttribute("tender", tender);
        return "comment/commentAdd";
    }

    /**
     * 添加评标
     *
     * @param badStatus
     * @return
     */
    @RequiresPermissions("tender:comment:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(BadStatus badStatus) {
        badStatus.setUserId(getUserId());
        String tenderId = badStatus.getTenderId();
        String userId = badStatus.getUserId();
        Notice n = noticeService.selectByKey(badStatus.getBadId());
        if (TenderConst.TENDER_TATUS_OFF.equals(n.getStatus())) {
            return resultFail("投标已结束，无法评标");
        }
        List<BadStatus> list = commentService.findByBadIdAndUserId(tenderId, userId);
        if (list.size() > 0) {
            return resultFail(" 请勿重复评标");
        }
        //修改状态为评标中
        Notice notice = new Notice();
        notice.setId(badStatus.getBadId());
        String status = badStatus.getStatus();
        notice.setStatus(TenderConst.TENDER_TATUS_BAD);
        if (TenderConst.PASS.equals(status)) {
            //如果评标通过，则招标结束,否则状态修改为评标中
            notice.setStatus(TenderConst.TENDER_TATUS_OFF);
        }
        int updateResult = noticeService.updateNotNull(notice);
        int result = commentService.save(badStatus);
        if (result == 1) {
            return resultSuccess("添加成功！");
        } else {
            return resultFail("添加失败");
        }
    }

    /**
     * 评标详情页
     *
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("tender:comment:detail")
    @RequestMapping("/detailPage")
    public String detailPage(Model model, String id) {
        BadStatus badStatus = commentService.selectByKey(id);
        model.addAttribute("comment", badStatus);
        return "comment/commentDetail";
    }

    /**
     * 编辑评标页
     *
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("tender:comment:edit")
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
        BadStatus badStatus = commentService.selectByKey(id);
        model.addAttribute("comment", badStatus);
        return "comment/commentEdit";
    }

    /**
     * 编辑评标
     *
     * @param
     * @return
     */
    @RequiresPermissions("tender:comment:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(BadStatus badStatus) {
        BadStatus bad = commentService.selectByKey(badStatus.getBadId());
        if(!getUserId().equals(bad.getUserId())){
            return resultFail("非当前用户添加记录无权修改");
        }
        //评标通过后要修改招标公告状态为结束
        Notice notice = new Notice();
        notice.setId(badStatus.getBadId());
        String status = badStatus.getStatus();
        notice.setStatus(TenderConst.TENDER_TATUS_BAD);
        if (TenderConst.PASS.equals(status)) {
            //如果评标通过，则招标结束,否则状态修改为评标中
            notice.setStatus(TenderConst.TENDER_TATUS_OFF);
        }
        int updateResult = noticeService.updateNotNull(notice);


        int result = commentService.updateNotNull(badStatus);
        if (result == 1) {
            return resultSuccess("编辑成功！");
        } else {
            return resultFail("编辑失败");
        }
    }

    /**
     * 删除评标
     *
     * @param id
     * @return
     */
    @RequiresPermissions("tender:comment:del")
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
        BadStatus badStatus = commentService.selectByKey(id);
        if(!getUserId().equals(badStatus.getUserId())){
            return resultFail("非当前用户添加记录无权删除");
        }
        int result = commentService.delete(id);
        if (result == 1) {
            return resultSuccess("删除成功！");
        } else {
            return resultFail("删除失败");
        }
    }

}