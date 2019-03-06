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
import com.intflag.springboot.entity.app.AppTest;
import com.intflag.springboot.entity.app.AppTestExample;
import com.intflag.springboot.mapper.app.AppTestMapper;
import com.intflag.springboot.service.app.AppTestService;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2018-08-29 10:34:09
 * @Description 测试业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class AppTestServiceImpl implements AppTestService {

	@Autowired
	private AppTestMapper appTestMapper;

	public StatusResult add(AppTest appTest) throws Exception {
		// 设置信息
		appTest.setAppId(UUIDUtils.getCode());
		appTestMapper.insert(appTest);
		// 正常返回
		return StatusResult.ok(StatusResult.ADD_SUCCESS);
	}

	public StatusResult update(AppTest appTest) throws Exception {
		appTestMapper.updateByPrimaryKeySelective(appTest);
		// 正常返回
		return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
	}

	public StatusResult findById(String id) throws Exception {
		AppTest appTest = appTestMapper.selectByPrimaryKey(id);
		if (appTest != null) {
			return StatusResult.ok(appTest);
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
		AppTestExample example = new AppTestExample();
		example.or().andAppNameLike("%" + keyWords + "%");
		List<AppTest> list = appTestMapper.selectByExample(example);
		// 取出分页信息
		PageInfo<AppTest> pageInfo = new PageInfo<>(list);
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
					// 根据主键删除
					appTestMapper.deleteByPrimaryKey(id);
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