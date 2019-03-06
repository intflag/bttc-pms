package com.intflag.springboot.service.admin;

import java.util.List;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.admin.SysResource;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月5日 上午12:59:56
 * @Description 资源菜单业务层接口
 * @version V1.0
 */
public interface SysResourceService {

	/**
	 * 查找所有资源
	 * 
	 * @return
	 */
	List<SysResource> findAll() throws Exception;

	/**
	 * 根据用户查询权限
	 * 
	 * @param userId
	 * @return
	 */
	List<SysResource> findByUserId(String userId) throws Exception;

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	StatusResult findList() throws Exception;

	/**
	 * 根据资源权限名称查询
	 * 
	 * @param rolename
	 * @return
	 */
	StatusResult findByName(String rolename) throws Exception;

	/**
	 * 添加资源权限
	 * 
	 * @param sysResource
	 * @return
	 */
	StatusResult add(SysResource sysResource) throws Exception;

	/**
	 * 修改资源权限
	 * 
	 * @param sysResource
	 * @return
	 */
	StatusResult update(SysResource sysResource) throws Exception;

	/**
	 * 根据ID查询
	 * 
	 * @param roleId
	 * @return
	 */
	StatusResult findById(String roleId) throws Exception;

	/**
	 * 删除资源权限
	 * 
	 * @param sysResource
	 * @return
	 */
	StatusResult delete(SysResource sysResource) throws Exception;

	/**
	 * 根据类型查询
	 * 
	 * @param type
	 * @return
	 */
	StatusResult findByType(String type) throws Exception;

	/**
	 * 根据父级菜单查询
	 * 
	 * @param parentId
	 * @return
	 */
	StatusResult findByParentId(String parentId) throws Exception;

	/**
	 * 查询资源名称是否重复
	 * 
	 * @param resname
	 * @return
	 * @throws Exception
	 */
	StatusResult checkResnameIsExist(String resname) throws Exception;

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
