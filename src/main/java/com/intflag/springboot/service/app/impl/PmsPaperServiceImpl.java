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
import com.intflag.springboot.entity.app.PmsPaper;
import com.intflag.springboot.entity.app.PmsPaperExample;
import com.intflag.springboot.mapper.app.PmsPaperMapper;
import com.intflag.springboot.service.app.PmsPaperService;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-03-29 16:06:08
 * @Description 论文提交业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class PmsPaperServiceImpl implements PmsPaperService {

	@Autowired
	private PmsPaperMapper pmsPaperMapper;

	@Override
	public StatusResult add(PmsPaper pmsPaper) throws Exception {
		// 设置信息
		pmsPaper.setPaperId(UUIDUtils.getCode());
		pmsPaperMapper.insert(pmsPaper);
		// 正常返回
		return StatusResult.ok(StatusResult.ADD_SUCCESS);
	}

	@Override
	public StatusResult update(PmsPaper pmsPaper) throws Exception {
		pmsPaperMapper.updateByPrimaryKeySelective(pmsPaper);
		// 正常返回
		return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
	}

	@Override
	public StatusResult findById(String id) throws Exception {
		PmsPaper pmsPaper = pmsPaperMapper.selectByPrimaryKey(id);
		if (pmsPaper != null) {
			return StatusResult.ok(pmsPaper);
		}
		return StatusResult.none(StatusResult.FIND_NONE);
	}

	@Override
	public PageBean pageQuery(PageBean pageBean) throws Exception {
		String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
		int pageNum = pageBean.getCurrPage();// 当前页
		int pageSize = pageBean.getPageSize();// 每页显示条数
		// 查询当前页数据
		PageHelper.startPage(pageNum, pageSize);// 设置分页信息
		// 执行查询
		PmsPaperExample example = new PmsPaperExample();
		example.or().andPaperNameLike("%" + keyWords + "%");
		List<PmsPaper> list = pmsPaperMapper.selectByExample(example);
		// 取出分页信息
		PageInfo<PmsPaper> pageInfo = new PageInfo<>(list);
		pageBean.setList(list);
		pageBean.setTotalCount(pageInfo.getTotal());// 设置总记录数
		pageBean.setTotalPage(pageInfo.getPages());// 设置总页数
		// 返回结果集
		return PageBean.ok(pageBean);
	}

	@Override
	public StatusResult delete(String ids) {
		if (ids != null) {
			String[] objIds = ids.split(",");
			if (objIds != null && objIds.length > 0) {
				for (String id : objIds) {
					// 根据主键删除
					pmsPaperMapper.deleteByPrimaryKey(id);
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