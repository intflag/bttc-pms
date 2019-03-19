package com.intflag.springboot.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月30日 上午10:06:15
 * @Description 文件操作工具类
 * @version V1.0
 */
public class TenDirFileUtils {

	/**
	 * MultipartFile转为File类型
	 * 
	 * @return
	 */
	public static File MultipartFile2File(MultipartFile mFile) {
		File f = null;
		try {
			f = File.createTempFile("tmp", null);
			mFile.transferTo(f);
			f.deleteOnExit();
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @param path
	 * @return String
	 * @description 将文件转base64字符串
	 * @date 2018年3月20日
	 * @author changyl File转成编码成BASE64
	 */

	public static String fileToBase64(String path) {
		String base64 = null;
		InputStream in = null;
		try {
			File file = new File(path);
			in = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			in.read(bytes);
			base64 = Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return base64;
	}

	// BASE64解码成File文件
	public static void base64ToFile(String destPath, String base64, String fileName) {
		File file = null;
		// 创建文件目录
		String filePath = destPath;
		File dir = new File(filePath);
		if (!dir.exists() && !dir.isDirectory()) {
			dir.mkdirs();
		}
		BufferedOutputStream bos = null;
		java.io.FileOutputStream fos = null;
		try {
			byte[] bytes = Base64.getDecoder().decode(base64);
			file = new File(filePath + "/" + fileName);
			fos = new java.io.FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
