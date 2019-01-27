package com.jeecms.cms.action.admin.assist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.springframework.util.StringUtils;

public class IndexTitleUtil {

	private static String indexTitle = "";

	private static boolean c = false;
	private static String filePath="";
	private static String sysRootPath = "";
	static {
		String classpath = IndexTitleUtil.class.getResource("/").getPath();
		classpath = classpath.substring(1, classpath.length() - 1);
		if (!classpath.substring(0, 1).equals("/") && !classpath.substring(1, 2).equals(":")) {
			classpath = "/" + classpath;
		}
		/**
		 * 系统根路径
		 */
		sysRootPath = classpath;
		if (sysRootPath != null) {
			File f = new File(sysRootPath);
			sysRootPath = f.getParentFile().getParent();
			sysRootPath = sysRootPath.replaceAll("\\\\", "/");
		}

		filePath = sysRootPath+File.separator+"/r/indextitle/title.txt";
	}

	public static void saveTitle(String title) {
		indexTitle = title;
		try {
			File p = new File(filePath).getParentFile();
			if (!p.exists()) {
				p.mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(title.getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getTitle() {
		if (!c) {

			File f = new File(filePath);
			if (f.exists()) {
				StringBuilder result = new StringBuilder();
				try {
					BufferedReader br = new BufferedReader(new FileReader(f));// 构造一个BufferedReader类来读取文件
					String s = null;
					while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
						result.append(System.lineSeparator() + s);
					}
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				c = true;
				indexTitle = result.toString();
				return indexTitle;
			} else {
				File parent = f.getParentFile();
				if (!parent.exists()) {
					parent.mkdirs();
				}
				return "";
			}

		} else {
			return indexTitle;
		}

	}
}
