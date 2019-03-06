package com.intflag.springboot.test;
/** 
* @author 刘国鑫  QQ:1598749808
* @date 2018年8月28日 上午11:20:57
* @Description 测试模板
* @version V1.0
*/

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.intflag.springboot.common.util.DateUtils;
import com.intflag.springboot.common.util.GeneratorSqlmapHelper;
import com.intflag.springboot.common.util.TemplateHelp;

public class TemplateTest {

	@Test
	public void fun1() throws Exception {
		Map<String, Object> data = new HashMap<>();
		data.put("functioncomment", "测试");
		data.put("author", "刘国鑫");
		data.put("date", DateUtils.date2String(new Date()));

		TemplateHelp.creatTemplate(data, "test.ftl", "D:/test/template/test.html");
	}
	@Test
	public void fun2() throws Exception {
		Map<String, Object> data = new HashMap<>();
		//文件头相关信息
		data.put("functioncomment", "测试");
		data.put("author", "刘国鑫");
		data.put("date", DateUtils.date2String(new Date()));
		//数据库信息
		data.put("dataBase", "dev-bttc-pms");
		data.put("username", "root");
		data.put("password", "admin123");
		//相关数据库表
		data.put("tablename", "sys_log");
		//工作空间路径
		data.put("filepath", "D:/test/template");
		//实体生成路径
		data.put("entityFilePath", "D:/test/template");
		//接口生成路径
		data.put("mapperFilePath", "D:/test/template");
		//映射文件生成路径
		data.put("xmlFilePath", "D:/test/template/xml");
		//生成逆向工程配置文件
		TemplateHelp.creatTemplate(data, "GeneratorConfig.ftl", "D:/test/template/generatorConfig.xml");
		//生成POJO实体、DAO接口、Mybatis映射文件
		GeneratorSqlmapHelper.generator("D:/test/template/generatorConfig.xml");
		
	}
	
	@Test
	public void fun3() throws Exception {
		Map<String, Object> data = new HashMap<>();
		//文件头相关信息
		data.put("functioncomment", "测试");
		data.put("author", "刘国鑫");
		data.put("date", DateUtils.date2String(new Date()));
		//相关数据库表
		data.put("tablename", "sys_resource");
		//工作空间路径
		data.put("filepath", "D:/test/template");
		//模块名称大小写
		data.put("classNameUppercase", "SysResource");
		data.put("classNameLowercase", "sysResource");
		
		TemplateHelp.creatTemplate(data, "controller.ftl", "D:/test/template/SysResourceController.java");
	}
	@Test
	public void fun4() throws Exception {
		Map<String, Object> data = new HashMap<>();
		//文件头相关信息
		data.put("functioncomment", "测试");
		data.put("author", "刘国鑫");
		data.put("date", DateUtils.date2String(new Date()));
		//相关数据库表
		data.put("tablename", "sys_resource");
		//工作空间路径
		data.put("filepath", "D:/test/template");
		//模块名称大小写
		data.put("classNameUppercase", "SysResource");
		data.put("classNameLowercase", "sysResource");
		data.put("pkcolumn", "Resname");
		
		TemplateHelp.creatTemplate(data, "Service.ftl", "D:/test/template/SysResourceService.java");
		TemplateHelp.creatTemplate(data, "ServiceImpl.ftl", "D:/test/template/SysResourceServiceImpl.java");
	}
	@Test
	public void fun5() throws Exception {
		String filePath = "D:\\010 - Working Space - 工作台\\031 - Business\\bttc-pms\\src\\main\\resources\\generatorConfig.xml";
		GeneratorSqlmapHelper.generator(filePath);
	}
}
