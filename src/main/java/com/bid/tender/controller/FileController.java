package com.bid.tender.controller;

import com.bid.common.utils.RandomUtils;
import com.bid.common.utils.ServletUtils;
import com.bid.common.web.PageInfo;
import com.bid.common.web.controller.BaseController;
import com.bid.tender.model.FileManage;
import com.bid.tender.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 附件管理
 *
 * @author sugar 2016-8-29 14:00:09
 */
@Controller
@RequestMapping(value = "file")
public class FileController extends BaseController {
    @Autowired
    private FileService fileService;

    /**
     * 公告管理页面
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manager() {
        return "file/files";
    }

    //    @RequiresPermissions("tender:file:view")
    @RequestMapping(value = "/dataGrid/json", method = RequestMethod.POST)
    @ResponseBody

    public Object dataGrid(Integer page, Integer rows, String sort, String order, HttpServletRequest request) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = ServletUtils.getParmFilter(request);
        pageInfo.setPageResult(fileService.findDataGrid(pageInfo, condition));
        return pageInfo;
    }

    /**
     * 添加附件
     *
     * @param files
     * @return
     */
//    @RequiresPermissions("tender:file:add")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
                         @RequestParam(value = "model", required = false) String model,
                         HttpServletRequest request) {
        String id = RandomUtils.randomString32();
        System.out.println("上传文件开始！");
        long startTime = System.currentTimeMillis();
        //上传文件保存位置
        String pathString = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
        //判断文件夹是否存在
        File file = new File(pathString);
        if (!file.isDirectory()) {
            //创建文件夹
            file.mkdirs();
            System.out.println("创建文件夹：" + pathString);
        }
        //批量上传
        for (int i = 0; i < files.length; i++) {
            CommonsMultipartFile fileTemp = files[i];
            if (!fileTemp.getOriginalFilename().isEmpty()) {
                String path = pathString + "/" + fileTemp.getOriginalFilename();
                System.out.println("fileName：" + path);
                //保存文件记录
                File newFile = new File(path);
                FileManage fileManage = new FileManage();
                String filename = fileTemp.getOriginalFilename();
                fileManage.setFileName(filename);
                fileManage.setRelationId(id);
                fileManage.setFilePath(path);
                fileManage.setModel(model);
                fileManage.setSuffix(filename.substring(filename.lastIndexOf(".") + 1));
                fileService.save(fileManage);

                try {
                    //上传日志
                    files[i].transferTo(newFile);
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
            }


        }
        long endTime = System.currentTimeMillis();
        System.out.println("运行时间：" + String.valueOf(endTime - startTime) + "ms");
        Map<String, String> data = new HashMap<String, String>();
        data.put("relationId", id);
        return resultSuccess(data);

    }

    //    @RequiresPermissions("tender:file:download")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(HttpServletResponse response, HttpServletRequest request, String fileId) {
        FileManage fileBean = fileService.selectByKey(fileId);
        HttpHeaders headers = new HttpHeaders();
        // 响应头设置ContentType
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        if (null != fileBean) {
            try {
                String abstFilePath = fileBean.getFilePath();
                // 附件形式
                headers.setContentDispositionFormData("attachment",
                        URLEncoder.encode(fileBean.getFileName(), "UTF-8"));
                byte[] bytes = Files.readAllBytes(Paths.get(abstFilePath));
                ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
                return responseEntity;
            } catch (IOException e) {
                return new ResponseEntity<byte[]>(null, headers,
                        HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<byte[]>(null, headers,
                    HttpStatus.NOT_FOUND);
        }
    }


    /**
     * 删除附件
     *
     * @param id
     * @return
     */
//    @RequiresPermissions("tender:file:del")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String id) {
        fileService.delete(id);
        return resultSuccess("删除成功！");
    }

}