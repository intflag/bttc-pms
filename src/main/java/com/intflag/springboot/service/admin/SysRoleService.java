package com.intflag.springboot.service.admin;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.admin.SysRole;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月21日21:56:15
 * @Description 系统角色业务层接口
 * @version V1.0
 */
public interface SysRoleService {

	/**
	 * 查询列表
	 * 
	 * @return
	 * @throws Exception
	 */
	StatusResult findList() throws Exception;

	/**
	 * 根据用户ID查询角色ID
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	StatusResult findRoleIdByUserId(String userId) throws Exception;

	/**
	 * 根据角色名称查询
	 * 
	 * @param rolename
	 * @return
	 * @throws Exception
	 */
	StatusResult findByName(String rolename) throws Exception;

	/**
	 * 分页查询角色
	 * 
	 * @param pageBean
	 * @return
	 */

	/**
	 * 添加角色
	 * 
	 * @param sysRole
	 * @return
	 */
	StatusResult add(SysRole sysRole) throws Exception;

	/**
	 * 修改角色
	 * 
	 * @param sysRole
	 * @return
	 */
	StatusResult update(SysRole sysRole) throws Exception;

	/**
	 * 根据ID查询
	 * 
	 * @param roleId
	 * @return
	 */
	StatusResult findById(String roleId) throws Exception;

	/**
	 * 删除角色
	 * 
	 * @param ids
	 * @return
	 */
	StatusResult delete(String ids) throws Exception;

	/**
	 * 根据角色ID查询资源ID
	 * 
	 * @param roleId
	 * @return
	 */
	StatusResult findResourceIdByRoleId(String roleId) throws Exception;

	/**
	 * 查询角色名称是否重复
	 * 
	 * @param rolename
	 * @return
	 */
	StatusResult checkRolenameIsExist(String rolename) throws Exception;

	/**
	 * 分页
	 * 
	 * @param pageBean
	 * @return
	 */
	PageBean pageQuery(PageBean pageBean) throws Exception;

	/**
	 * 获取所有菜单
	 * 
	 * @return
	 */
	StatusResult findResourceList() throws Exception;

}
