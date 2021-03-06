package com.intflag.springboot.service.admin;

import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.admin.SysUser;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 刘国鑫 QQ:1598749808
 * @version V1.0
 * @date 2018年8月4日 上午11:35:30
 * @Description 用户业务层接口
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
     * @param username
     * @param adminUsername
     * @return
     * @throws Exception
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

    /**
     * 添加导入用户
     *
     * @param sysUser
     * @param session
     * @return
     * @throws Exception
     */
    StatusResult addImportUser(SysUser sysUser, HttpSession session) throws Exception;

    /**
     * 导入用户界面分页
     *
     * @param pageBean
     * @param groupId
     * @param loginUser
     * @return
     * @throws Exception
     */
    PageBean pageQuery(PageBean pageBean, String groupId, SysUser loginUser) throws Exception;

    /**
     * 导入用户界面添加用户
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    StatusResult addUser(SysUser sysUser) throws Exception;

    /**
     * 导入界面更新用户
     *
     * @param sysUser
     * @return
     */
    StatusResult updateUser(SysUser sysUser) throws Exception;

    /**
     * 根据组织ID查找用户
     *
     * @param groupId
     * @return
     */
    StatusResult findByGroup(String groupId) throws Exception;
}
