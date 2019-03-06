package com.intflag.springboot.common.util;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月28日 下午3:27:52
 * @Description 十方字符串操作工具类
 * @version V1.0
 */
public class TenDirStringUtils {
	/**
	 * 驼峰式命名，首字母小写，,例如 ad_code 转成 adCode
	 * 
	 * @param str
	 * @return
	 */
	public static String string2HumpAndLowercase(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append(str.toLowerCase());

		int count = sb.indexOf("_");
		while (count != 0) {
			int num = sb.indexOf("_", count);
			count = num + 1;
			if (num != -1) {
				char ss = sb.charAt(count);
				char ia = (char) (ss - 32);
				sb.replace(count, count + 1, ia + "");
			}
		}
		return sb.toString().replaceAll("_", "");
	}

	/**
	 * 返回首字母大写的字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String fristStrToUpperCase(String str) {
		String resultStr = str.substring(0, 1).toUpperCase() + str.substring(1);
		return resultStr;
	}

	/**
	 * 驼峰式命名，首字母大写，,例如 ad_code 转成 AdCode
	 * 
	 * @param str
	 * @return
	 */
	public static String string2HumpAndUppercase(String str) {
		return fristStrToUpperCase(string2HumpAndLowercase(str));
	}
}
