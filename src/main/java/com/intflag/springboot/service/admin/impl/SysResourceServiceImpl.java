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
import com.intflag.springboot.entity.admin.SysResourceExample;
import com.intflag.springboot.entity.admin.SysRoleResExample;
import com.intflag.springboot.entity.admin.SysRoleResKey;
import com.intflag.springboot.mapper.admin.SysResourceMapper;
import com.intflag.springboot.mapper.admin.SysRoleResMapper;
import com.intflag.springboot.service.admin.SysResourceService;

/**
 * @author 刘国鑫 QQ:1598749808
 * @date 2018年8月5日 上午1:00:36
 * @Description 资源菜单业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class SysResourceServiceImpl implements SysResourceService {

	@Autowired
	private SysResourceMapper sysResourceMapper;

	@Autowired
	private SysRoleResMapper sysRoleResMapper;

	@Override
	public List<SysResource> findAll() throws Exception {
		SysResourceExample example = new SysResourceExample();
		List<SysResource> list = sysResourceMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<SysResource> findByUserId(String userId) throws Exception {
		List<SysResource> list = sysResourceMapper.selectByUserId(userId);
		return list;
	}

	@Override
	public StatusResult findList() throws Exception {
		// 查询所有
		SysResourceExample example = new SysResourceExample();
		List<SysResource> list = sysResourceMapper.selectByExample(example);
		// 正常返回
		return StatusResult.ok(list);
	}

	@Override
	public StatusResult findByName(String resname) throws Exception {
		SysResourceExample example = new SysResourceExample();
		example.createCriteria().andResnameEqualTo(resname);
		List<SysResource> list = sysResourceMapper.selectByExample(example);
		if (null != list && list.size() > 0) {
			// 正常返回
			return StatusResult.ok(list.get(0));
		}
		return StatusResult.none(StatusResult.FIND_NONE);
	}

	@Override
	public StatusResult add(SysResource sysResource) throws Exception {
		// 设置资源菜单信息
		sysResource.setResourceId(UUIDUtils.getCode());
		sysResource.setCdate(new Date());
		sysResource.setMdate(new Date());
		sysResourceMapper.insert(sysResource);
		// 正常返回
		return StatusResult.ok(StatusResult.ADD_SUCCESS);
	}

	@Override
	public StatusResult update(SysResource sysResource) throws Exception {
		sysResource.setMdate(new Date());
		sysResourceMapper.updateByPrimaryKey(sysResource);
		// 正常返回
		return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
	}

	@Override
	public StatusResult findById(String resourceId) throws Exception {
		SysResource sysResource = sysResourceMapper.selectByPrimaryKey(resourceId);
		if (sysResource != null) {
			return StatusResult.ok(sysResource);
		}
		return StatusResult.none(StatusResult.FIND_NONE);
	}

	@Override
	public StatusResult delete(SysResource sysResource) throws Exception {
		// 先判断有无角色与此关联
		SysRoleResExample example = new SysRoleResExample();
		example.createCriteria().andResourceIdEqualTo(sysResource.getResourceId());
		List<SysRoleResKey> list = sysRoleResMapper.selectByExample(example);
		// 判断有无子菜单
		// 查询所有
		SysResourceExample sonExample = new SysResourceExample();
		sonExample.createCriteria().andParentIdEqualTo(sysResource.getResourceId());
		List<SysResource> sonList = sysResourceMapper.selectByExample(sonExample);
		if (sonList.size() == 0 && list.size() == 0) {
			// 根据主键删除
			sysResourceMapper.deleteByPrimaryKey(sysResource.getResourceId());
			// 正常返回
			return StatusResult.ok(StatusResult.DELETE_SUCCESS);
		}
		// 异常返回
		return StatusResult.error(StatusResult.DELETE_FAIL_FK);
	}

	@Override
	public StatusResult findByType(String type) throws Exception {
		// 查询所有
		List<SysResource> list = sysResourceMapper.selectByType(type);
		// 正常返回
		return StatusResult.ok(list);
	}

	@Override
	public StatusResult findByParentId(String parentId) throws Exception {
		// 查询所有
		SysResourceExample example = new SysResourceExample();
		example.createCriteria().andParentIdEqualTo(parentId);
		List<SysResource> list = sysResourceMapper.selectByExample(example);
		// 正常返回
		return StatusResult.ok(list);
	}

	@Override
	public StatusResult checkResnameIsExist(String resname) throws Exception {
		SysResourceExample example = new SysResourceExample();
		example.createCriteria().andResnameEqualTo(resname);
		List<SysResource> list = sysResourceMapper.selectByExample(example);
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
		SysResourceExample example = new SysResourceExample();
		example.or().andResnameLike("%" + keyWords + "%");
		example.setOrderByClause("mdate desc");
		List<SysResource> list = sysResourceMapper.selectByExample(example);
		// 取出分页信息
		PageInfo<SysResource> pageInfo = new PageInfo<>(list);
		pageBean.setList(list);
		pageBean.setTotalCount(pageInfo.getTotal());// 设置总记录数
		pageBean.setTotalPage(pageInfo.getPages());// 设置总页数
		// 返回结果集
		return PageBean.ok(pageBean);
	}

	@Override
	public StatusResult delete(String ids) {
		if (ids != null) {
			String[] resourceIds = ids.split(",");
			if (resourceIds != null && resourceIds.length > 0) {
				for (String resourceId : resourceIds) {
					// 先判断有无角色与此关联
					SysRoleResExample example = new SysRoleResExample();
					example.createCriteria().andResourceIdEqualTo(resourceId);
					List<SysRoleResKey> list = sysRoleResMapper.selectByExample(example);
					// 判断有无子菜单
					// 查询所有
					SysResourceExample sonExample = new SysResourceExample();
					sonExample.createCriteria().andParentIdEqualTo(resourceId);
					List<SysResource> sonList = sysResourceMapper.selectByExample(sonExample);
					if (sonList.size() == 0 && list.size() == 0) {
						// 根据主键删除
						sysResourceMapper.deleteByPrimaryKey(resourceId);
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

}
