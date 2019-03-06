package com.intflag.springboot.service.admin.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.admin.SysResource;
import com.intflag.springboot.entity.admin.SysRole;
import com.intflag.springboot.entity.admin.SysRoleExample;
import com.intflag.springboot.entity.admin.SysRoleExample.Criteria;
import com.intflag.springboot.entity.admin.SysRoleResExample;
import com.intflag.springboot.entity.admin.SysRoleResKey;
import com.intflag.springboot.entity.admin.SysRoleUserExample;
import com.intflag.springboot.entity.admin.SysRoleUserKey;
import com.intflag.springboot.mapper.admin.SysResourceMapper;
import com.intflag.springboot.mapper.admin.SysRoleMapper;
import com.intflag.springboot.mapper.admin.SysRoleResMapper;
import com.intflag.springboot.mapper.admin.SysRoleUserMapper;
import com.intflag.springboot.service.admin.SysRoleService;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月5日 下午7:47:11
 * @Description 系统角色业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysRoleUserMapper sysRoleUserMapper;

	@Autowired
	private SysRoleResMapper sysRoleResMapper;

	@Autowired
	private SysResourceMapper sysResourceMapper;

	@Override
	public StatusResult findList() throws Exception {
		// 查询所有
		SysRoleExample example = new SysRoleExample();
		List<SysRole> list = sysRoleMapper.selectByExample(example);
		// 正常返回
		return StatusResult.ok(list);
	}

	@Override
	public StatusResult findRoleIdByUserId(String userId) throws Exception {
		SysRoleUserExample example = new SysRoleUserExample();
		example.or().andUserIdEqualTo(userId);
		List<SysRoleUserKey> list = sysRoleUserMapper.selectByExample(example);
		// 正常返回
		return StatusResult.ok(list);
	}

	@Override
	public StatusResult findByName(String rolename) throws Exception {
		SysRoleExample example = new SysRoleExample();
		example.createCriteria().andRolenameEqualTo(rolename);
		List<SysRole> list = sysRoleMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			// 正常返回
			return StatusResult.ok(list.get(0));
		}
		return StatusResult.none(StatusResult.FIND_NONE);
	}

	@Override
	public StatusResult add(SysRole sysRole) throws Exception {
		// 设置角色信息
		sysRole.setRoleId(UUIDUtils.getCode());
		sysRole.setCdate(new Date());
		sysRole.setMdate(new Date());
		sysRoleMapper.insert(sysRole);
		// 添加权限
		String resIds = sysRole.getResourceIds();
		String[] resourceIds = resIds.split(",");
		if (resourceIds != null) {
			for (String resourceId : resourceIds) {
				SysRoleResKey roleResKey = new SysRoleResKey();
				roleResKey.setResourceId(resourceId);
				roleResKey.setRoleId(sysRole.getRoleId());
				sysRoleResMapper.insert(roleResKey);
			}
		}
		// 正常返回
		return StatusResult.ok(StatusResult.ADD_SUCCESS);
	}

	@Override
	public StatusResult update(SysRole sysRole) throws Exception {
		// 设置属性
		sysRole.setMdate(new Date());
		sysRoleMapper.updateByPrimaryKeySelective(sysRole);
		// 先根据角色ID删除角色权限表中的数据
		SysRoleResExample example = new SysRoleResExample();
		example.createCriteria().andRoleIdEqualTo(sysRole.getRoleId());
		sysRoleResMapper.deleteByExample(example);
		// 添加权限
		String resIds = sysRole.getResourceIds();
		String[] resourceIds = resIds.split(",");
		if (resourceIds != null) {
			for (String resourceId : resourceIds) {
				SysRoleResKey roleResKey = new SysRoleResKey();
				roleResKey.setResourceId(resourceId);
				roleResKey.setRoleId(sysRole.getRoleId());
				sysRoleResMapper.insert(roleResKey);
			}
		}
		// 正常返回
		return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
	}

	@Override
	public StatusResult findById(String roleId) throws Exception {
		SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
		if (sysRole != null) {
			return StatusResult.ok(sysRole);
		}
		return StatusResult.none(StatusResult.FIND_NONE);
	}

	@Override
	public StatusResult delete(String ids) throws Exception {
		if (ids != null) {
			String[] roleIds = ids.split(",");
			if (roleIds != null && roleIds.length > 0) {
				for (String roleId : roleIds) {
					// 先判断有无用户与此角色关联
					SysRoleUserExample sysRoleUserExample = new SysRoleUserExample();
					sysRoleUserExample.createCriteria().andRoleIdEqualTo(roleId);
					List<SysRoleUserKey> list = sysRoleUserMapper.selectByExample(sysRoleUserExample);
					if (null == list || list.size() == 0) {
						// 根据主键删除
						sysRoleMapper.deleteByPrimaryKey(roleId);
						// 删除对应的菜单授权
						SysRoleResExample example = new SysRoleResExample();
						example.createCriteria().andRoleIdEqualTo(roleId);
						sysRoleResMapper.deleteByExample(example);
					} else {
						// 异常返回
						return StatusResult.error(StatusResult.DELETE_FAIL_FK);
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

	@Override
	public StatusResult findResourceIdByRoleId(String roleId) throws Exception {
		SysRoleResExample example = new SysRoleResExample();
		example.createCriteria().andRoleIdEqualTo(roleId);
		List<SysRoleResKey> list = sysRoleResMapper.selectByExample(example);
		if (null != list) {
			// 正常返回
			return StatusResult.ok(list);
		}
		return StatusResult.none(StatusResult.FIND_NONE);
	}

	@Override
	public StatusResult checkRolenameIsExist(String rolename) throws Exception {
		SysRoleExample example = new SysRoleExample();
		example.createCriteria().andRolenameEqualTo(rolename);
		List<SysRole> list = sysRoleMapper.selectByExample(example);
		if (list != null && list.size() == 0) {
			return StatusResult.ok();
		} else if (list != null && list.size() > 0) {
			return StatusResult.exist(StatusResult.FIND_EXIST);
		} else {
			return StatusResult.none(StatusResult.FIND_NONE);
		}
	}

	@Override
	public PageBean pageQuery(PageBean pageBean) throws Exception {
		String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
		int pageNum = pageBean.getCurrPage();// 当前页
		int pageSize = pageBean.getPageSize();// 每页显示条数
		// 查询当前页数据
		PageHelper.startPage(pageNum, pageSize);// 设置分页信息
		// 执行查询
		SysRoleExample example = new SysRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andRolenameLike("%" + keyWords + "%");
		example.setOrderByClause("mdate desc");
		List<SysRole> list = sysRoleMapper.selectByExample(example);
		// 取出分页信息
		PageInfo<SysRole> pageInfo = new PageInfo<>(list);
		pageBean.setList(list);
		pageBean.setTotalCount(pageInfo.getTotal());// 设置总记录数
		pageBean.setTotalPage(pageInfo.getPages());// 设置总页数
		// 返回结果集
		return PageBean.ok(pageBean);
	}

	@Override
	public StatusResult findResourceList() throws Exception {
		List<SysResource> list = sysResourceMapper.selectByType("0");
		return StatusResult.ok(list);
	}

}
