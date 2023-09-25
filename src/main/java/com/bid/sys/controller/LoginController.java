package com.bid.sys.controller;

import com.bid.common.constant.Const;
import com.bid.common.constant.Global;
import com.bid.common.shiro.UsernamePasswordCaptchaToken;
import com.bid.common.utils.StringUtils;
import com.bid.common.web.controller.BaseController;
import com.bid.sys.model.SysLog;
import com.bid.sys.model.SysUser;
import com.bid.sys.service.SysLogService;
import com.bid.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

/**
 * @author: QQ:553039957
 * @Date: 2023/9/25 16:33
 * @Description:
 * 1. gitcode主页： https://gitcode.net/tbb414 （推荐）
 * 2. github主页：https://github.com/doudoutangs
 * 3. gitee(码云)主页：https://gitee.com/spdoudoutang
 */
@Controller
@RequestMapping(value = "{adminPath}")
public class LoginController extends BaseController {

	private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private SysUserService sysUserService;
	
    /**
     * 默认页面
     *
     * @param model
     * @return
     */
    @RequestMapping
    public String index(Model model) {
    	if (SecurityUtils.getSubject().isAuthenticated()) {
            return "sys/index";
        }
    	return "sys/login";
    }

    /**
     * GET 登录
     * @return {String}
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.info("GET请求登录");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:" + Global.getAdminPath();
        }
        return "sys/login";
    }

    /**
     * 注册
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        logger.info("GET请求注册");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:" + Global.getAdminPath();
        }
        return "user/register";
    }

    /**
     * POST 登录 shiro 写法
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object loginPost(String username, String password, String captcha, Model model, HttpServletRequest request) {
    	logger.info("POST请求登录");
        String errMsg = "";
        UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(username, password.toCharArray(), captcha, true, request.getRemoteHost());
        Subject user = SecurityUtils.getSubject();
		try {
			user.login(token);
		}catch (UnknownAccountException e) {
			errMsg = "用户名不存在";
		} catch (IncorrectCredentialsException ice) {
			errMsg = "密码错误";
		} catch (LockedAccountException e) {
			errMsg = "账户锁定状态";
		} catch (DisabledAccountException dax) {
			errMsg = "用户禁用状态";
		} catch (ExcessiveAttemptsException e) {
			errMsg = "登录失败次数过多";
		} catch (ExpiredCredentialsException eca) {
			errMsg = "用户凭证过期";
		} catch (RuntimeException e) {
			errMsg = "未知错误,请联系管理员";
		}finally{
	    	 if(StringUtils.isNotBlanks(errMsg)){
                 SysLog sysLog = new SysLog(Const.LOG_TYPE_LOGIN, "登陆失败，原因:" + errMsg);
                 sysLog.setLoginName(username);
//                 sysLogService.save(request,false,sysLog);
	    		 model.addAttribute("msg", errMsg);
	    		 return "sys/login";
	    	 }
		}
        //登陆成功后
        // 1.更新登陆次数/最后登陆时间
        SysUser sysUser = getCurrentUser();
        sysUser.setLastvisitDate(new Date());
        sysUser.setLoginCount(sysUser.getLoginCount()!=null?sysUser.getLoginCount()+1:1);
        sysUserService.updateAll(sysUser);
		//2.Session中写入用户相关信息
		getSession().setAttribute(Const.SESSION_USER_ID, getUserId());//用户Id存入session中
		//3.当前用户Session 写入 ServletContext
		ServletContext application = request.getServletContext();//获取Servlet上下文环境
		HashMap<String,HttpSession> sessionMap = (HashMap<String, HttpSession>) application.getAttribute("sessions");
		HttpSession httpSession = request.getSession();
		sessionMap.put(getUserId(), httpSession); //SessionID 存入 ServletContext中 （key:用户Id value:Session ID）
		//保存日志
        LOGGER.info("sessions add[userId:{}]：" + httpSession.getId(), getUserId());
        SysLog sysLog = new SysLog(Const.LOG_TYPE_LOGIN, "登陆成功");
        sysLog.setLoginName(username);
        sysLogService.save(request,false,sysLog);
		return "redirect:" + Global.getAdminPath();
    }

    /**
     * 未授权
     * @return {String}
     */
    @RequestMapping(value = "/unauth")
    public String unauth(Model model) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "page/403";
        }
        return "sys/login";
    }

    /**
     * 退出
     * @return {Result}
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Object logout(Model model) {
        logger.info("安全退出,用户名:" + getLoginName());
        SecurityUtils.getSubject().logout();
        return resultSuccess("您已安全退出");
    }
}
