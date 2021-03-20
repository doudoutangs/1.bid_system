package com.bid.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 主键序列注解-数据表维护
 * @author sugar 2016-8-16 20:19:40
 */
@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SysSeqHelper  {
	
	public static final String UUDI = "uuid";
	
	public static final String DB = "db";
	
	/**
	 * 序列类型
	 * @return
	 */
	String type() default "";

	/**
	 * 序列表对应code
	 * @return
	 */
	String value() default "";
}
