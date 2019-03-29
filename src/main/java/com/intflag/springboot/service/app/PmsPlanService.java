package com.intflag.springboot.service.app;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.admin.SysUser;
import com.intflag.springboot.entity.app.PmsPlan;

import javax.servlet.http.HttpSession;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-03-27 21:31:50
 * @Description 论文计划业务层接口
 * @version V1.0
 */
public interface PmsPlanService {

	/**
	 * 添加资源权限
	 * @param pmsPlan
	 * @param session
	 * @return
	 * @throws Exception
	 */
	StatusResult add(PmsPlan pmsPlan, HttpSession session) throws Exception;

	/**
	 * 修改资源权限
	 * 
	 * @param pmsPlan
	 * @return
	 */
	StatusResult update(PmsPlan pmsPlan) throws Exception;

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
	 * @param groupId
     * @param loginUser
     * @return
	 */
	PageBean pageQuery(PageBean pageBean, String groupId, SysUser loginUser) throws Exception;

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	StatusResult delete(String ids)throws Exception;

	/**
	 * 根据登录用户查找计划
	 * @param session
	 * @return
	 */
    StatusResult findByUser(HttpSession session)throws Exception;
}