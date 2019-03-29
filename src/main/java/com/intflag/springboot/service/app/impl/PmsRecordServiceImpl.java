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
import com.intflag.springboot.entity.app.PmsRecord;
import com.intflag.springboot.entity.app.PmsRecordExample;
import com.intflag.springboot.mapper.app.PmsRecordMapper;
import com.intflag.springboot.service.app.PmsRecordService;

/**
 * @author 刘国鑫QQ1598749808
 * @date 2019-03-29 15:58:59
 * @Description 指导记录业务层接口实现
 * @version V1.0
 */
@Service
@Transactional
public class PmsRecordServiceImpl implements PmsRecordService {

	@Autowired
	private PmsRecordMapper pmsRecordMapper;

	@Override
	public StatusResult add(PmsRecord pmsRecord) throws Exception {
		// 设置信息
		pmsRecord.setRecordId(UUIDUtils.getCode());
		pmsRecordMapper.insert(pmsRecord);
		// 正常返回
		return StatusResult.ok(StatusResult.ADD_SUCCESS);
	}

	@Override
	public StatusResult update(PmsRecord pmsRecord) throws Exception {
		pmsRecordMapper.updateByPrimaryKeySelective(pmsRecord);
		// 正常返回
		return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
	}

	@Override
	public StatusResult findById(String id) throws Exception {
		PmsRecord pmsRecord = pmsRecordMapper.selectByPrimaryKey(id);
		if (pmsRecord != null) {
			return StatusResult.ok(pmsRecord);
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
		PmsRecordExample example = new PmsRecordExample();
		example.or().andPlanNameLike("%" + keyWords + "%");
		List<PmsRecord> list = pmsRecordMapper.selectByExample(example);
		// 取出分页信息
		PageInfo<PmsRecord> pageInfo = new PageInfo<>(list);
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
					pmsRecordMapper.deleteByPrimaryKey(id);
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