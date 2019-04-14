package com.intflag.springboot.service.app;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.PmsPaper;

import javax.servlet.http.HttpSession;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-03-29 16:06:08
 * @Description 论文提交业务层接口
 * @version V1.0
 */
public interface PmsPaperService {

	/**
	 * 添加资源权限
	 * 
	 * @param pmsPaper
	 * @return
	 */
	StatusResult add(PmsPaper pmsPaper, HttpSession session) throws Exception;

	/**
	 * 修改资源权限
	 * 
	 * @param pmsPaper
	 * @return
	 */
	StatusResult update(PmsPaper pmsPaper, HttpSession session) throws Exception;

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
	PageBean pageQuery(PageBean pageBean,HttpSession session) throws Exception;

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	StatusResult delete(String ids);

	/**
	 * 根据计划ID打包文档
	 * @param id
	 * @return
	 * @throws Exception
	 */
    StatusResult packDoc(String id) throws Exception;
}