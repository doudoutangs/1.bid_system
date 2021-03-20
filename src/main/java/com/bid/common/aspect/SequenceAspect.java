package com.bid.common.aspect;

import com.bid.common.annotation.SysSeqHelper;
import com.bid.common.exception.SequenceAspectException;
import com.bid.common.utils.FormatUtils;
import com.bid.common.utils.StringUtils;
import com.bid.sys.mapper.SysSequenceMapper;
import com.bid.sys.model.SysSequence;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 主键字段-序列注入
 * @author sugar 2016-08-13 23:56:50
 * 通过一张表维护主键序列
 */
@Component
@Aspect
public class SequenceAspect  {
	
	private Logger LOGGER = LoggerFactory.getLogger(SequenceAspect.class);

	@Autowired
    private SysSequenceMapper sysSequenceMapper;
	private final int maxNum = 5;//序列最大获取次数
	
	/**
	 * 切入点
	 */
	@Pointcut(" execution(* com.bid.*.mapper.**.insert*(..))")
	public void aspect(){}
	
	@Before("aspect()")
	public void before(JoinPoint point){
		Object[] args = point.getArgs();
		if(args.length == 1){
			Object obj = args[0];
			Field[] fields = obj.getClass().getDeclaredFields();//获取内部所有字段
			String newSeq = null;
			for(Field field : fields){
				field.setAccessible(true); //设置些属性是可以访问的
				SysSeqHelper sysSeqHelper = field.getAnnotation(SysSeqHelper.class);
				if(sysSeqHelper != null){
					String seqType = sysSeqHelper.type();
					String seqCode = sysSeqHelper.value();
					//情况1：类型默认为空
					if(StringUtils.isBlanks(seqType)){
						if (StringUtils.isBlanks(seqCode)) { // SeqCode为空的情况，返回UUID
							newSeq = UUID.randomUUID().toString();
						} else {
							newSeq = getSequence(seqCode);//获取新序列
						}
					//情况2：根据类型处理
					} else {
						if (sysSeqHelper.UUDI.equals(seqType)) { //类型为UUID
							newSeq = UUID.randomUUID().toString();
						} else if (sysSeqHelper.DB.equals(seqType)){ //从数据库获取
							if(StringUtils.isBlanks(seqCode)){
								newSeq = getSequence(obj.getClass());
							}else{
								newSeq = getSequence(seqCode);//获取新序列
							}
						}else{
							throw new SequenceAspectException("注入主键序列异常,注解type参数错误（" + obj + ")");
						}
					}
					try {
						if (field.getType() == Integer.class) {//主键为Integer类型
							String regEx="[^0-9]";   
							Matcher m = Pattern.compile(regEx).matcher(newSeq);  
							Integer newSeqInt =  Integer.valueOf(m.replaceAll("").trim());
							field.set(obj, newSeqInt);
						} else {
							field.set(obj, newSeq);
						}
					} catch (Exception e) {
						throw new SequenceAspectException("注入主键序列异常:" + obj );
					} 
					break;
				}
			}
		}
	}
	
	
	/**
	 * 获取序列
	 * @param seqCode
	 * @return
	 */
	public String getSequence(String seqCode) {
		//序列
		Integer newSeq = null ;
		SysSequence sysSequence = null;
		
		int forNum = 0;//当前获取次数
		int resultCount = 0; //update result
		while(resultCount == 0 && forNum < maxNum){
			sysSequence = sysSequenceMapper.selectByPrimaryKey(seqCode);
			if(sysSequence == null){
				String errMSg = "序列查询失败,请检查数据库序列表配置：" + seqCode;
				LOGGER.error(errMSg);
				throw new SequenceAspectException(errMSg);
			}
			//序列+1
			newSeq = sysSequence.getCurrentSeq() + 1;
			resultCount = sysSequenceMapper.updateCurrentSeq( newSeq ,sysSequence.getSeqCode(), sysSequence.getCurrentSeq());
			
			forNum ++;
		}
		if(forNum == maxNum){
			String errMSg = "序列更新失败,请检查数据库序列表配置：" + seqCode;
			LOGGER.error(errMSg);
			throw new SequenceAspectException(errMSg);
		}
		//拼接序列
		int currentLength = String.valueOf(newSeq).length();//当前序列长度
		int minLength = sysSequence.getMinLength();//最小长度
		StringBuffer bf = new StringBuffer();
		if(StringUtils.isNotBlank(sysSequence.getFixStart())){//固定头
			bf.append(sysSequence.getFixStart());
		}
		int zeroLength = minLength - currentLength;
		while(zeroLength > 0){//补0
			bf.append("0");
			zeroLength--;
		}
		bf.append(newSeq);
		if(StringUtils.isNotBlank(sysSequence.getFixEnd())){//固定尾
			bf.append(sysSequence.getFixEnd());
		}
		return bf.toString().trim();
	}
	
	/**
	 * 获取序列(根据类名转为数据库名)
	 * @param dbEntity
	 * @return
	 */
	public String getSequence(Class dbEntity) {
		String classPath = dbEntity.getName();
		String className = classPath.substring(classPath.lastIndexOf(".") + 1, classPath.length());
		String seqCode = FormatUtils.classnameToDbname(className);//类名转数据库表名
		return getSequence(seqCode);
	}
}
