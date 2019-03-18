package com.intflag.springboot.service.admin.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.MD5Utils;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.admin.SysResource;
import com.intflag.springboot.entity.admin.SysRoleUserExample;
import com.intflag.springboot.entity.admin.SysRoleUserKey;
import com.intflag.springboot.entity.admin.SysUser;
import com.intflag.springboot.entity.admin.SysUserExample;
import com.intflag.springboot.entity.admin.SysUserExample.Criteria;
import com.intflag.springboot.mapper.admin.SysResourceMapper;
import com.intflag.springboot.mapper.admin.SysRoleUserMapper;
import com.intflag.springboot.mapper.admin.SysUserMapper;
import com.intflag.springboot.service.admin.SysUserService;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月4日 上午11:37:12
 * @Description 用户业务层接口
 * @version V1.0
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Value("${TenDir.constant.adminUsername}")
	private String ADMIN_USERNAME;

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;

	@Autowired
	private SysResourceMapper sysResourceMapper;

	@Override
	public StatusResult findById(String userId) throws Exception {
		SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
		if (sysUser != null) {
			return StatusResult.ok(sysUser);
		}
		return StatusResult.none(StatusResult.FIND_NONE);
	}

	@Override
	public StatusResult findList() throws Exception {
		// 查询所有
		SysUserExample example = new SysUserExample();
		List<SysUser> list = sysUserMapper.selectByExample(example);
		// 正常返回
		return StatusResult.ok(list);
	}

	@Override
	public StatusResult add(SysUser sysUser) throws Exception {
		// 设置用户信息
		sysUser.setUserId(UUIDUtils.getCode());
		sysUser.setCdate(new Date());
		sysUser.setMdate(new Date());
		sysUser.setPassword(MD5Utils.md5(sysUser.getPassword()));
		// 向用户表表中插入
		sysUserMapper.insert(sysUser);
		// 向角色用户表中插入数据
		String[] roleIds = sysUser.getRoleId();
		if (roleIds != null) {
			for (String roleId : roleIds) {
				SysRoleUserKey roleUserKey = new SysRoleUserKey();
				roleUserKey.setRoleId(roleId);
				roleUserKey.setUserId(sysUser.getUserId());
				sysRoleUserMapper.insert(roleUserKey);
			}
		}
		// 正常返回
		return StatusResult.ok(StatusResult.ADD_SUCCESS);
	}

	@Override
	public StatusResult update(SysUser sysUser) throws Exception {
		// 设置属性
		sysUser.setMdate(new Date());
		sysUser.setPassword(MD5Utils.md5(sysUser.getPassword()));
		// 根据主键更新
		sysUserMapper.updateByPrimaryKeySelective(sysUser);
		// 删除已授权的角色
		SysRoleUserExample example = new SysRoleUserExample();
		example.createCriteria().andUserIdEqualTo(sysUser.getUserId());
		sysRoleUserMapper.deleteByExample(example);
		// 重新添加授权角色
		// 向角色用户表中插入数据
		String[] roleIds = sysUser.getRoleId();
		if (roleIds != null) {
			for (String roleId : roleIds) {
				SysRoleUserKey roleUserKey = new SysRoleUserKey();
				roleUserKey.setRoleId(roleId);
				roleUserKey.setUserId(sysUser.getUserId());
				sysRoleUserMapper.insert(roleUserKey);
			}
		}
		// 正常返回
		return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
	}

	@Override
	public StatusResult delete(SysUser sysUser) throws Exception {
		// 根据主键删除
		sysUserMapper.deleteByPrimaryKey(sysUser.getUserId());
		// 删除对应的角色授权
		SysRoleUserExample example = new SysRoleUserExample();
		example.createCriteria().andUserIdEqualTo(sysUser.getUserId());
		sysRoleUserMapper.deleteByExample(example);
		// 正常返回
		return StatusResult.ok(StatusResult.DELETE_SUCCESS);
	}

	@Override
	public SysUser login(SysUser sysUser) throws Exception {
		SysUserExample example = new SysUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(sysUser.getUsername()).andPasswordEqualTo(MD5Utils.md5(sysUser.getPassword()));
		List<SysUser> list = sysUserMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public StatusResult findMenu(String userId, String username, String adminUsername) throws Exception {
		List<SysResource> list = null;
		if (adminUsername.equals(username)) {
			list = sysResourceMapper.selectMenuByType("0");
		} else {
			list = sysResourceMapper.selectByUserId(userId);
			if (list != null && list.size() > 0) {
				List<SysResource> menu = new ArrayList<>();
				for (SysResource sysResource : list) {
					if ("0".equals(sysResource.getType())) {
						List<SysResource> children = findChildren(sysResource.getResourceId(), list);
						sysResource.setSysResources(children);
						menu.add(sysResource);
					}
				}
				list = menu;
			}
		}
		if (null != list) {
			return StatusResult.ok(list);
		}
		return StatusResult.none(StatusResult.FIND_NONE);
	}

	private List<SysResource> findChildren(String id, List<SysResource> list) {
		List<SysResource> children = new ArrayList<>();
		for (SysResource sysResource : list) {
			if (!"0".equals(sysResource.getType()) && !"2".equals(sysResource.getType())
					&& sysResource.getParentId().equals(id)) {
				sysResource.setSysResources(findChildren(sysResource.getResourceId(), list));
				children.add(sysResource);
			}
		}
		return children;
	}

	@Override
	public StatusResult checkUsernameIsExist(String username) throws Exception {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<SysUser> list = sysUserMapper.selectByExample(example);
		if (list != null && list.size() == 0) {
			return StatusResult.ok();
		} else if (list != null && list.size() > 0) {
			return StatusResult.exist(StatusResult.FIND_EXIST);
		} else {
			return StatusResult.none(StatusResult.FIND_NONE);
		}
	}

	@Override
	public PageBean pageQuery(PageBean pageBean, SysUser loginUser) throws Exception {
		String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
		int pageNum = pageBean.getCurrPage();// 当前页
		int pageSize = pageBean.getPageSize();// 每页显示条数
		// 查询当前页数据
		PageHelper.startPage(pageNum, pageSize);// 设置分页信息
		// 执行查询
		SysUserExample example = new SysUserExample();
		Criteria criteria = example.createCriteria();
		// 设置条件
		if (!loginUser.getUsername().equals(ADMIN_USERNAME)) {
			criteria.andUsernameNotEqualTo(ADMIN_USERNAME);
		}
		criteria.andUsernameLike("%" + keyWords + "%");
		example.setOrderByClause("mdate desc");
		List<SysUser> list = sysUserMapper.selectByExample(example);
		// 取出分页信息
		PageInfo<SysUser> pageInfo = new PageInfo<>(list);
		pageBean.setList(list);
		pageBean.setTotalCount(pageInfo.getTotal());// 设置总记录数
		pageBean.setTotalPage(pageInfo.getPages());// 设置总页数
		// 返回结果集
		return PageBean.ok(pageBean);
	}

	@Override
	public StatusResult delete(String ids, SysUser loginUser) throws Exception {
		String loginUserId = loginUser.getUserId();
		if (ids != null) {
			String[] userIds = ids.split(",");
			if (userIds != null && userIds.length > 0) {
				for (String userId : userIds) {
					if (!loginUserId.equals(userId)) {
						// 根据主键删除
						sysUserMapper.deleteByPrimaryKey(userId);
						// 删除对应的角色授权
						SysRoleUserExample example = new SysRoleUserExample();
						example.createCriteria().andUserIdEqualTo(userId);
						sysRoleUserMapper.deleteByExample(example);
					} else {
						// 异常返回
						return StatusResult.error(StatusResult.DELETE_FAIL_SUICIDE);
					}
				}
				// 正常返回
				return StatusResult.ok(StatusResult.DELETE_SUCCESS);
			} else {
				// 异常返回
				return StatusResult.error(StatusResult.DELETE_FAIL);
			}
		} else {
			// 异常返回
			return StatusResult.error(StatusResult.DELETE_FAIL);
		}

	}

}