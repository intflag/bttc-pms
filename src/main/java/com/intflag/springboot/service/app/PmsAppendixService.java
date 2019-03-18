package com.intflag.springboot.service.app;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.PmsAppendix;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-02-24 16:16:49
 * @Description 附件管理业务层接口
 * @version V1.0
 */
public interface PmsAppendixService {

	/**
	 * 添加资源权限
	 * 
	 * @param pmsAppendix
	 * @return
	 */
	StatusResult add(PmsAppendix pmsAppendix) throws Exception;

	/**
	 * 修改资源权限
	 * 
	 * @param pmsAppendix
	 * @return
	 */
	StatusResult update(PmsAppendix pmsAppendix) throws Exception;

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

    PageBean blogAndPmsAppendixs(PageBean pageBean);
}