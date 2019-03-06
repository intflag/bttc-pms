package com.intflag.springboot.service.admin;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.admin.SysTemplateparams;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月27日 下午7:23:12
 * @Description 代码生成模板业务层接口
 * @version V1.0
 */
public interface SysTemplateParamsService {

	/**
	 * 分页
	 * 
	 * @param pageBean
	 * @return
	 */
	PageBean pageQuery(PageBean pageBean) throws Exception;

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	StatusResult delete(String ids) throws Exception;

	/**
	 * 获取数据库所有表
	 * 
	 * @return
	 * @throws Exception
	 */
	StatusResult selectDataBaseTables() throws Exception;

	/**
	 * 根据表名查找字段
	 * 
	 * @param tableName
	 * @return
	 */
	StatusResult selectFieldByTableName(String tableName) throws Exception;

	/**
	 * 增加
	 * @param sysTemplateparams
	 * @return
	 */
	StatusResult add(SysTemplateparams sysTemplateparams) throws Exception;

}
