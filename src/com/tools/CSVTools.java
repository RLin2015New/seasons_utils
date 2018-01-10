package com.tools;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * @projectName qipai_niuyouquan_login
 * @fileName ConfigLoader.java
 * @description
 * @author lifl
 * @time 2017下午4:21:23
 *
 */

public class CSVTools {

	/**
	 * 读取csv文件
	 * 
	 * @param filePath
	 *            csv目录下的子文件夹目录名/csv的文件名
	 * @param includeHeader
	 *            list是否包含第一行
	 * @return List<String[]> String[]的每个值依次为csv文件每一行从左到右的单元格的值
	 */
	public static List<String[]> readCSV(String filePath, boolean includeHeader) {
		List<String[]> list = new ArrayList<String[]>();
		if (!isRead(filePath)) {
			return list;
		}
		CsvReader reader = null;
		try {
			reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
			/** csv的第一行 * */
			reader.readHeaders();
			String[] headers = reader.getHeaders();
			if (includeHeader) {
				// 读取UTF-8格式有bug 需去掉第一个字符的空格
				headers[0] = headers[0].substring(1);
				list.add(headers);
			}
			/** 从第二行开始读 * */
			while (reader.readRecord()) {
				String[] values = reader.getValues();
				if (values.length != 0 && !StringUtils.isBlank(values[0])) {
					list.add(values);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			JarLogTools.jarLog.error("csv error", e);
		} finally {
			/** 关闭reader * */
			if (reader != null) {
				reader.close();
			}
		}
		return list;
	}

	private static boolean isRead(String filename) {
		File file = new File(filename);
		if (!file.exists()) {
			JarLogTools.error("文件不存在!");
			return false;
		}

		return true;
	}

}
