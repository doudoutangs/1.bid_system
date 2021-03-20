package com.bid.common.scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.bid.common.constant.Const;
import com.bid.common.utils.BeanUtils;
import com.bid.common.web.Result;
import com.bid.sys.model.SysLog;
import com.bid.sys.service.SysLogService;

/**
 * 异常处理，对ajax类型的异常返回ajax错误，避免页面问题
 * @author sugar 2016-7-5 09:22:12
 */
@Component
public class ExceptionResolver implements HandlerExceptionResolver {
	
	private final static Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);
	
	@Autowired
    private SysLogService sysLogService;

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
		
		// log记录异常
		logger.error(e.getMessage(), e);
		SysLog sysLog = new SysLog(Const.LOG_TYPE_EXCEPTION, e.getMessage());
//		sysLogService.save(request,true, sysLog);
		
		// 1、非控制器请求造成的异常
		if (!(handler instanceof HandlerMethod)) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			return new ModelAndView("page/500");
		}
		
		// 2、ajax 异常  - spring ajax 返回含有 ResponseBody 或者 RestController注解
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		ResponseBody annotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
		RestController restAnnotation = handlerMethod.getBean().getClass().getAnnotation(RestController.class);
		if (null != annotation || null != restAnnotation) {
			Result result = new Result();
			result.setMsg(e.getMessage());
			return new ModelAndView(new MappingJackson2JsonView(), BeanUtils.toMap(result));
		}
		
		//3、Shiro权限 异常
		if(e instanceof UnauthorizedException){
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return new ModelAndView("page/403");
		}
		
		//4、 其他异常 -页面指定状态为500，便于上层的resion或者nginx的500页面跳转，由于error/error不适合对用户展示
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return new ModelAndView("page/500");
	}

}