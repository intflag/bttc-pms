package com.intflag.springboot.service.admin;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.admin.SysUser;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月4日 上午11:35:30
 * @Description 用户业务层接口
 * @version V1.0
 */
public interface SysUserService {

	/**
	 * 根据ID查询
	 * 
	 * @param userId
	 * @return
	 */
	StatusResult findById(String userId) throws Exception;

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	StatusResult findList() throws Exception;

	/**
	 * 添加用户
	 * 
	 * @param sysUser
	 * @return
	 */
	StatusResult add(SysUser sysUser) throws Exception;

	/**
	 * 修改用户
	 * 
	 * @param sysUser
	 * @return
	 */
	StatusResult update(SysUser sysUser) throws Exception;

	/**
	 * 删除用户
	 * 
	 * @param sysUser
	 * @return
	 */
	StatusResult delete(SysUser sysUser) throws Exception;

	/**
	 * 登录
	 * 
	 * @param sysUser
	 * @return
	 */
	SysUser login(SysUser sysUser) throws Exception;

	/**
	 * 根据用户ID查询菜单
	 * 
	 * @param userId
	 * @param aDMIN_USERNAME
	 * @return
	 */
	StatusResult findMenu(String userId, String username, String adminUsername) throws Exception;

	/**
	 * 查询用户名是否重复
	 * 
	 * @param username
	 * @return
	 */
	StatusResult checkUsernameIsExist(String username) throws Exception;

	/**
	 * 分页
	 * 
	 * @param pageBean
	 * @return
	 */
	PageBean pageQuery(PageBean pageBean, SysUser loginUser) throws Exception;

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	StatusResult delete(String ids, SysUser loginUser) throws Exception;

}
