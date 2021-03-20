package com.bid.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.cglib.beans.BeanMap.Generator;
/**
 * Mybatis Bean/Mapper 生成工具
 */
public class GenerateUtils {
	
	//Mapper文件生成
	public static void createMapper() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException{
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		ConfigurationParser cp = new ConfigurationParser(warnings);
		InputStream resourceAsStream = Generator.class.getResourceAsStream("/generatorConfig.xml");
		Configuration config = cp.parseConfiguration(resourceAsStream);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		System.out.println("success");
	}
	
	public static void main(String[] args) throws Exception {
		createMapper();
	}
}
