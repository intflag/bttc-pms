package com.intflag.springboot.service.app;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.AppTest;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2018-08-29 10:34:09
 * @Description 测试业务层接口
 * @version V1.0
 */
public interface AppTestService {

	/**
	 * 添加资源权限
	 * 
	 * @param appTest
	 * @return
	 */
	StatusResult add(AppTest appTest) throws Exception;

	/**
	 * 修改资源权限
	 * 
	 * @param appTest
	 * @return
	 */
	StatusResult update(AppTest appTest) throws Exception;

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

}