package com.intflag.springboot.service.app;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.PmsBlog;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-02-24 15:01:23
 * @Description 公告管理业务层接口
 * @version V1.0
 */
public interface PmsBlogService {

	/**
	 * 添加资源权限
	 * 
	 * @param pmsBlog
	 * @return
	 */
	StatusResult add(PmsBlog pmsBlog) throws Exception;

	/**
	 * 修改资源权限
	 * 
	 * @param pmsBlog
	 * @return
	 */
	StatusResult update(PmsBlog pmsBlog,String[] appendixIds) throws Exception;

	/**
	 * 根据ID查询
	 * 
	 * @param id
	 * @return
	 */
	StatusResult findById(String id) throws Exception;

	/**
	 * 分页
	 * 
	 * @param pageBean
	 * @return
	 */
	PageBean pageQuery(PageBean pageBean) throws Exception;

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	StatusResult delete(String ids);

	/**
	 * 新增公告，并插入附件
	 * @param pmsBlog
	 * @param appendixIds
	 * @return
	 */
    StatusResult add(PmsBlog pmsBlog, String[] appendixIds);
}