package com.intflag.springboot.service.app.impl;

import java.util.ArrayList;
import java.util.List;

import com.intflag.springboot.entity.app.PmsBlogAppendix;
import com.intflag.springboot.mapper.app.PmsBlogAppendixMapper;
import com.intflag.springboot.service.app.PmsBlogAppendixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.app.PmsAppendix;
import com.intflag.springboot.entity.app.PmsAppendixExample;
import com.intflag.springboot.mapper.app.PmsAppendixMapper;
import com.intflag.springboot.service.app.PmsAppendixService;

/**
 * @author 刘国鑫QQ1598749808
 * @version V1.0
 * @date 2019-02-24 16:16:49
 * @Description 附件管理业务层接口实现
 */
@Service
@Transactional
public class PmsAppendixServiceImpl implements PmsAppendixService {

    @Autowired
    private PmsAppendixMapper pmsAppendixMapper;

    public StatusResult add(PmsAppendix pmsAppendix) throws Exception {
        // 设置信息

        pmsAppendixMapper.insert(pmsAppendix);
        // 正常返回
        return StatusResult.ok(StatusResult.ADD_SUCCESS);
    }

    public StatusResult update(PmsAppendix pmsAppendix) throws Exception {
        pmsAppendixMapper.updateByPrimaryKeySelective(pmsAppendix);
        // 正常返回
        return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
    }

    public StatusResult findById(String id) throws Exception {
        PmsAppendix pmsAppendix = pmsAppendixMapper.selectByPrimaryKey(id);
        if (pmsAppendix != null) {
            return StatusResult.ok(pmsAppendix);
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
        PmsAppendixExample example = new PmsAppendixExample();
        example.or().andAppendixNameLike("%" + keyWords + "%");
        List<PmsAppendix> list = pmsAppendixMapper.selectByExample(example);
        // 取出分页信息
        PageInfo<PmsAppendix> pageInfo = new PageInfo<>(list);
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
                    pmsAppendixMapper.deleteByPrimaryKey(id);
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

    @Autowired
    private PmsBlogAppendixMapper pmsBlogAppendixMapper;

    @Override
    public PageBean blogAndPmsAppendixs(PageBean pageBean) {
        String keyWords = pageBean.getKeyWords() == null ? "" : pageBean.getKeyWords();// 关键字
        PmsBlogAppendix pmsBlogAppendix = new PmsBlogAppendix();
        pmsBlogAppendix.setBlogId(keyWords);

        List<PmsAppendix> pmsAppendices = new ArrayList<>();
        List<PmsBlogAppendix> byBlogId = pmsBlogAppendixMapper.findByBlogId(pmsBlogAppendix);
        for (PmsBlogAppendix blogAppendix : byBlogId) {
            PmsAppendix pmsAppendix = pmsAppendixMapper.selectByPrimaryKey(blogAppendix.getAppendixId());
            if (pmsAppendix != null) {
                pmsAppendices.add(pmsAppendix);
            }
        }
        // 取出分页信息
        PageInfo<PmsAppendix> pageInfo = new PageInfo<>(pmsAppendices);
        pageBean.setList(pmsAppendices);
        pageBean.setTotalCount(pageInfo.getTotal());// 设置总记录数
        pageBean.setTotalPage(pageInfo.getPages());// 设置总页数
        // 返回结果集
        return PageBean.ok(pageBean);
    }
}