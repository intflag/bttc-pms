package com.intflag.springboot.service.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.app.PmsGroup;
import com.intflag.springboot.entity.app.PmsGroupExample;
import com.intflag.springboot.mapper.app.PmsGroupMapper;
import com.intflag.springboot.service.app.PmsGroupService;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-03-18 23:48:29
 * @Description 机构业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class PmsGroupServiceImpl implements PmsGroupService {

	@Autowired
	private PmsGroupMapper pmsGroupMapper;

	public StatusResult add(PmsGroup pmsGroup) throws Exception {
		// 设置信息
		pmsGroup.setGroupId(UUIDUtils.getCode());
		pmsGroupMapper.insert(pmsGroup);
		// 正常返回
		return StatusResult.ok(StatusResult.ADD_SUCCESS);
	}

	public StatusResult update(PmsGroup pmsGroup) throws Exception {
		pmsGroupMapper.updateByPrimaryKeySelective(pmsGroup);
		// 正常返回
		return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
	}

	public StatusResult findById(String id) throws Exception {
		PmsGroup pmsGroup = pmsGroupMapper.selectByPrimaryKey(id);
		if (pmsGroup != null) {
			return StatusResult.ok(pmsGroup);
		}
		return StatusResult.none(StatusResult.FIND_NONE);
	}

	public PageBean pageQuery(PageBean pageBean) throws Exception {
		String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
		int pageNum = pageBean.getCurrPage();// 当前页
		int pageSize = pageBean.getPageSize();// 每页显示条数
		// 查询当前页数据
		PageHelper.startPage(pageNum, pageSize);// 设置分页信息
		// 执行查询
		PmsGroupExample example = new PmsGroupExample();
		example.or().andGroupNameLike("%" + keyWords + "%");
		List<PmsGroup> list = pmsGroupMapper.selectByExample(example);
		// 取出分页信息
		PageInfo<PmsGroup> pageInfo = new PageInfo<>(list);
		pageBean.setList(list);
		pageBean.setTotalCount(pageInfo.getTotal());// 设置总记录数
		pageBean.setTotalPage(pageInfo.getPages());// 设置总页数
		// 返回结果集
		return PageBean.ok(pageBean);
	}

	public StatusResult delete(String ids) {
		if (ids != null) {
			String[] objIds = ids.split(",");
			if (objIds != null && objIds.length > 0) {
				for (String id : objIds) {
                    PmsGroupExample example = new PmsGroupExample();
                    example.or().andPidEqualTo(id);
                    List<PmsGroup> pmsGroups = pmsGroupMapper.selectByExample(example);
                    if (pmsGroups != null && pmsGroups.size() > 0) {{
                        // 异常返回
                        return StatusResult.error("删除失败，请先删除上级组织部门");
                    }}
                    // 根据主键删除
					pmsGroupMapper.deleteByPrimaryKey(id);
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
	public StatusResult findAll() throws Exception {
		// 执行查询
		PmsGroupExample example = new PmsGroupExample();
        List<PmsGroup> pmsGroups = pmsGroupMapper.selectByExample(example);
        if (pmsGroups != null) {
			return StatusResult.ok(pmsGroups);
		}
		return StatusResult.none(StatusResult.FIND_NONE);
	}
}