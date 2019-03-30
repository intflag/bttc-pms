package com.intflag.springboot.service.app;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.${classNameUppercase};

/**
 * @author ${author}
 * @date ${date}
 * @Description ${functioncomment}业务层接口
 * @version V1.0
 */
public interface ${classNameUppercase}Service {

	/**
	 * 添加资源权限
	 * 
	 * @param ${classNameLowercase}
	 * @return
	 */
	StatusResult add(${classNameUppercase} ${classNameLowercase}) throws Exception;

	/**
	 * 修改资源权限
	 * 
	 * @param ${classNameLowercase}
	 * @return
	 */
	StatusResult update(${classNameUppercase} ${classNameLowercase}) throws Exception;

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
	StatusResult delete(String ids) throws Exception;

}