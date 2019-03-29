package com.intflag.springboot.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author 刘国鑫  QQ:1598749808
* @date 2018年8月22日 上午11:16:41
* @Description 时间工具
* @version V1.0
*/
public class DateUtils {

	/**
	 * 根据时间转换为字符串类型
	 * @param date
	 * @return
	 */
	public static String date2String(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
		return "";
	}
	/**
	 * 根据时间转换为字符串类型
	 * @param date
	 * @return
	 */
	public static String date2SimpleString(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}
		return "";
	}
}
