package com.bid.common.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 文件处理工具
 * @author sugar 2016-8-24 22:46:33
 *
 */
public class FileUtils {
	
	/**
	 * 写文件
	 * @param content
	 * @param saveFilePath
	 */
	public static void writeFile(String content, String saveFilePath) {
		try {
			FileOutputStream out = new FileOutputStream(saveFilePath); // 输出文件路径
			out.write(content.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读文件
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath) {
		String content = "";
		try {
			FileInputStream in = new FileInputStream(filePath); // 读取文件路径
			byte bs[] = new byte[in.available()];
			in.read(bs);
			content = new String(bs);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}


}
