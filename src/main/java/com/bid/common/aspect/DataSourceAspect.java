//package com.bid.common.aspect;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.bid.common.annotation.DataSourceChange;
//import com.bid.common.datasource.DynamicDataSource;
//import com.bid.common.exception.DataSourceAspectException;
//
///**
// * 动态数据源切换
// * @author sugar 2016-9-20 15:47:33
// * {@link com.bid.common.annotation.DataSourceChange} -> 注解方法
// */
//@Aspect
//@Component
//public class DataSourceAspect {
//    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);
//
//    @Around("@annotation(dataSourceChange)")
//    public Object doAround(ProceedingJoinPoint pjp, DataSourceChange dataSourceChange) {
//        Object retVal = null;
//        boolean selectedDataSource = false;
//        try {
//        	selectedDataSource = true;
//        	DynamicDataSource.setDataSource(dataSourceChange.value());
//            retVal = pjp.proceed();
//        } catch (Throwable e) {
//            LOGGER.warn("数据源切换错误", e);
//            throw new DataSourceAspectException("数据源切换错误", e);
//        } finally {
//        	if (selectedDataSource) {
//        		//重置数据源
//                DynamicDataSource.resetDataSource();
//            }
//        }
//        return retVal;
//    }
//}
