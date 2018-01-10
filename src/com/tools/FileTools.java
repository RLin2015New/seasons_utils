package com.tools;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @projectName qipai_niuyouquan_common
 * @fileName FileTools.java
 * @description
 * @author lifl
 * @time 2017下午2:46:31
 *
 */

public class FileTools {

	public static boolean checkPath(String path) {
		File f = new File(path);
		return f.exists();
	}

	public static String getNowPath(Class<?> c) {
		File f = new File(c.getResource("/").getPath());
		System.out.println("文件路径位:" + f);
		return f.getPath();
	}

	public static void writeListToFile(List<Object> objs, String path)
			throws IOException {
		FileWriter writer = new FileWriter(path);
		for (Object obj : objs) {
			writer.write(obj.toString());
		}
		writer.close();
	}

	public static boolean checkJarPath(String path) {
		InputStream is = FileTools.class.getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String s = "";
		try {
			while ((s = br.readLine()) != null) {
				System.out.println(s);
				// return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JarLogTools.jarLog.error("checkJarPath error", e);
		}
		if (s != null && s.length() > 0) {
			return true;
		}
		return false;
	}

	public static List<String> readLineToArray(String path, String encoding)
			throws IOException {
		// String content = "";
		List<String> array = new ArrayList<String>();
		File file = new File(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), encoding));
		String line = null;
		while ((line = reader.readLine()) != null) {
			// content += line + "\n";
			array.add(line);
		}
		reader.close();
		// return content;
		return array;
	}

	public static List<String> readLineToArray(String path) {
		if (!checkPath(path)) {
			return null;
		}
		List<String> array = new ArrayList<String>();
		FileReader reader;
		try {
			reader = new FileReader(path);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				array.add(str);
				// sb.append(str + "/n");
				System.out.println(str);
				// System.out.println(new String(str.getBytes(), "GBK"));
			}
			br.close();
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


			JarLogTools.jarLog.error("readLineToArray error1", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JarLogTools.jarLog.error("readLineToArray error2", e);
		}
		return array;
	}

	public static void reduceImg(String imgsrc, String imgdist, int widthdist,
			int heightdist, Float rate) {
		try {
			File srcfile = new File(imgsrc);
			// 检查文件是否存在
			if (!srcfile.exists()) {
				return;
			}
			// 如果rate不为空说明是按比例压缩
			if (rate != null && rate > 0) {
				// 获取文件高度和宽度
				int[] results = getImgWidth(srcfile);
				if (results == null || results[0] == 0 || results[1] == 0) {
					return;
				} else {
					widthdist = (int) (results[0] * rate);
					heightdist = (int) (results[1] * rate);
				}
			}
			// 开始读取文件并进行压缩
			Image src = javax.imageio.ImageIO.read(srcfile);
			BufferedImage tag = new BufferedImage((int) widthdist,
					(int) heightdist, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(
					src.getScaledInstance(widthdist, heightdist,
							Image.SCALE_SMOOTH), 0, 0, null);

			FileOutputStream out = new FileOutputStream(imgdist);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static int[] getImgWidth(File file) {
		InputStream is = null;
		BufferedImage src = null;
		int result[] = { 0, 0 };
		try {
			is = new FileInputStream(file);
			src = javax.imageio.ImageIO.read(is);
			result[0] = src.getWidth(null); // 得到源图宽
			result[1] = src.getHeight(null); // 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			JarLogTools.jarLog.error("getImgWidth error", e);
		}
		return result;
	}

}
