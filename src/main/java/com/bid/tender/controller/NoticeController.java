package com.bid.tender.controller;

import com.bid.common.utils.DateUtils;
import com.bid.common.utils.ServletUtils;
import com.bid.common.web.PageInfo;
import com.bid.common.web.controller.BaseController;
import com.bid.tender.model.FileManage;
import com.bid.tender.model.Notice;
import com.bid.tender.service.FileService;
import com.bid.tender.service.NoticeService;
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
 * @author: QQ:553039957
 * @Date: 2023/9/25 16:33
 * @Description:
 * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 * 
 */
@Controller
@RequestMapping(value = "notice")
public class NoticeController extends BaseController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private FileService fileService;

    /**
     * 公告管理页面
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manager() {
        return "notice/notice";
    }

    /**
     * 公告列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequiresPermissions("tender:notice:view")
    @RequestMapping(value = "/dataGrid/json", method = RequestMethod.POST)
    @ResponseBody

    public Object dataGrid(Integer page, Integer rows, String sort, String order, HttpServletRequest request) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = ServletUtils.getParmFilter(request);
        pageInfo.setPageResult(noticeService.findDataGrid(pageInfo, condition));
        return pageInfo;
    }

    /**
     * 增加公告页面
     *
     * @return
     */
    @RequiresPermissions("tender:notice:add")
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "notice/noticeAdd";
    }

    /**
     * 添加公告
     *
     * @param notice
     * @return
     */
    @RequiresPermissions("tender:notice:add")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(Notice notice) {
        notice.setUserId(getUserId());
        notice.setDelFlag("0");
        notice.setNoticeDate(DateUtils.getDate());
        int result = noticeService.save(notice);
        String industryType = notice.getIndustryType();
        Double amount = Double.parseDouble(notice.getAmount());
        if(industryType.equals("2")&& amount>1000000){
            return resultFail("招标类型为商品时，招标金额不得大于100万");
        }
        if (result == 1) {
            return resultSuccess("添加成功！");
        } else {
            return resultFail("添加失败");
        }
    }

    /**
     * 编辑公告页
     *
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("tender:notice:edit")
    @RequestMapping("/editPage")
    public String editPage(Model model, String id) {
        Notice notice = noticeService.selectByKey(id);
        String fileId = notice.getFileId();
        List<FileManage> list =  fileService.queryByRelationId(fileId);
        model.addAttribute("notice", notice);
        model.addAttribute("list", list);
        return "notice/noticeEdit";
    }

    /**
     * 编辑公告
     *
     * @param notice
     * @return
     */
    @RequiresPermissions("tender:notice:edit")
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Notice notice) {
        Notice n = noticeService.selectByKey(notice.getId());
        if(!getUserId().equals(n.getUserId())){
            return resultFail("非当前用户添加记录无权修改");
        }
        int result = noticeService.updateNotNull(notice);
        if (result == 1) {
            return resultSuccess("编辑成功！");
        } else {
            return resultFail("编辑失败");
        }
    }
    /**
     * 公告详情页
     *
     * @param model
     * @param id
     * @return
     */
    @RequiresPermissions("tender:notice:detail")
    @RequestMapping("/detailPage")
    public String detailPage(Model model, String id) {
        Notice notice = noticeService.selectByKey(id);
        String fileId = notice.getFileId();
        List<FileManage> list =  fileService.queryByRelationId(fileId);
        model.addAttribute("notice", notice);
        model.addAttribute("list", list);
        return "notice/noticeDetail";
    }

    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    @RequiresPermissions("tender:notice:del")
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String id) {
        Notice n = noticeService.selectByKey(id);
        if(!getUserId().equals(n.getUserId())){
            return resultFail("非当前用户添加记录无权删除");
        }
        int result = noticeService.delete(id);
        if (result == 1) {
            return resultSuccess("删除成功！");
        } else {
            return resultFail("删除失败");
        }
    }

}