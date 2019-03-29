package com.intflag.springboot.service.app;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.PmsRecord;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-03-29 15:58:59
 * @Description 指导记录业务层接口
 * @version V1.0
 */
public interface PmsRecordService {

	/**
	 * 添加资源权限
	 * 
	 * @param pmsRecord
	 * @return
	 */
	StatusResult add(PmsRecord pmsRecord) throws Exception;

	/**
	 * 修改资源权限
	 * 
	 * @param pmsRecord
	 * @return
	 */
	StatusResult update(PmsRecord pmsRecord) throws Exception;

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