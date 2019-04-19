package com.intflag.springboot.service.app.impl;

import java.util.Date;
import java.util.List;

import com.intflag.springboot.entity.app.PmsBlogAppendix;
import com.intflag.springboot.mapper.app.PmsAppendixMapper;
import com.intflag.springboot.mapper.app.PmsBlogAppendixMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.intflag.springboot.common.entity.PageBean;
import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.common.util.UUIDUtils;
import com.intflag.springboot.entity.app.PmsBlog;
import com.intflag.springboot.entity.app.PmsBlogExample;
import com.intflag.springboot.mapper.app.PmsBlogMapper;
import com.intflag.springboot.service.app.PmsBlogService;

/**
 * @author 刘国鑫QQ1598749808
 * @version V1.0
 * @date 2019-02-24 15:01:23
 * @Description 公告管理业务层接口实现
 */
@Service
@Transactional
@CacheConfig(cacheNames = "pmsBlog")
public class PmsBlogServiceImpl implements PmsBlogService {

    @Autowired
    private PmsBlogMapper pmsBlogMapper;

    @Autowired
    private PmsBlogAppendixMapper pmsBlogAppendixMapper;

    @Autowired
    private PmsAppendixMapper pmsAppendixMapper;

    public StatusResult add(PmsBlog pmsBlog) throws Exception {
        // 设置信息
        pmsBlog.setBlogId(UUIDUtils.getCode());
        //设置人员信息
        pmsBlogMapper.insert(pmsBlog);
        // 正常返回
        return StatusResult.ok(StatusResult.ADD_SUCCESS);
    }

    public StatusResult update(PmsBlog pmsBlog,String[] appendixIds) throws Exception {
        pmsBlog.setMdate(new Date());
        pmsBlogMapper.updateByPrimaryKeySelective(pmsBlog);
        // 正常返回
        if (appendixIds != null && appendixIds.length > 0) {
            for (String appendixId : appendixIds) {
                PmsBlogAppendix pmsBlogAppendix = new PmsBlogAppendix();
                pmsBlogAppendix.setBlogId(pmsBlog.getBlogId());
                pmsBlogAppendix.setAppendixId(appendixId);
                pmsBlogAppendixMapper.delete(pmsBlogAppendix);
                pmsBlogAppendixMapper.insert(pmsBlogAppendix);
            }
        }
        return StatusResult.ok(StatusResult.UPDATE_SUCCESS);
    }

    @Cacheable(cacheNames = {"pmsBlog"})
    public StatusResult findById(String id) throws Exception {
        PmsBlog pmsBlog = pmsBlogMapper.selectByPrimaryKey(id);
        if (pmsBlog != null) {
            pmsBlog.setReadCount(pmsBlog.getReadCount() == null ? 1 : pmsBlog.getReadCount()+1);
            pmsBlogMapper.updateByPrimaryKeySelective(pmsBlog);
            return StatusResult.ok(pmsBlog);
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
        PmsBlogExample example = new PmsBlogExample();
        example.or().andBlogTitleLike("%" + keyWords + "%");
        example.setOrderByClause("mdate desc");
        List<PmsBlog> list = pmsBlogMapper.selectByExample(example);
        // 取出分页信息
        PageInfo<PmsBlog> pageInfo = new PageInfo<>(list);
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
                    //从中间表获得所有和博客管理的附件id
                    PmsBlogAppendix pmsBlogAppendix = new PmsBlogAppendix();
                    pmsBlogAppendix.setBlogId(id);
                    List<PmsBlogAppendix> blogAppendixList = pmsBlogAppendixMapper.findByBlogId(pmsBlogAppendix);

                    blogAppendixList.forEach(ba->{
                        //删除这些附件，同时删除附件管理表对应的记录
                        pmsAppendixMapper.deleteByPrimaryKey(ba.getAppendixId());
                        pmsBlogAppendixMapper.delete(ba);
                    });

                    pmsBlogMapper.deleteByPrimaryKey(id);
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
    public StatusResult add(PmsBlog pmsBlog, String[] appendixIds) {
        // 设置信息
        String blogId = UUIDUtils.getCode();
        pmsBlog.setBlogId(blogId);
        //设置人员信息
        pmsBlogMapper.insert(pmsBlog);
        if (appendixIds != null && appendixIds.length > 0) {
            for (String appendixId : appendixIds) {
                PmsBlogAppendix pmsBlogAppendix = new PmsBlogAppendix();
                pmsBlogAppendix.setBlogId(blogId);
                pmsBlogAppendix.setAppendixId(appendixId);
                pmsBlogAppendixMapper.insert(pmsBlogAppendix);
            }
        }
        // 正常返回
        return StatusResult.ok(StatusResult.ADD_SUCCESS);
    }
}