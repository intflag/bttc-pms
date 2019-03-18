package com.intflag.springboot.service.app.impl;

import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.PmsBlogAppendix;
import com.intflag.springboot.mapper.app.PmsBlogAppendixMapper;
import com.intflag.springboot.service.app.PmsBlogAppendixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liugx  QQ:1598749808
 * @version V1.0
 * @date 2019-03-13 21:17
 * @Description 博客附件业务层接口实现
 */
@Service
@Transactional
public class PmsBlogAppendixServiceImpl implements PmsBlogAppendixService {

    @Autowired
    private PmsBlogAppendixMapper pmsBlogAppendixMapper;

    @Override
    public StatusResult findAll() throws Exception {
        List<PmsBlogAppendix> blogAppendices = pmsBlogAppendixMapper.findAll();
        if (blogAppendices != null) {
            return StatusResult.ok(blogAppendices);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }

    @Override
    public StatusResult add(PmsBlogAppendix pmsBlogAppendix) throws Exception {
        // 设置信息

        int i = pmsBlogAppendixMapper.insert(pmsBlogAppendix);
        // 正常返回
        return StatusResult.ok(i);
    }

    @Override
    public StatusResult delete(PmsBlogAppendix pmsBlogAppendix) throws Exception {
        int i = pmsBlogAppendixMapper.delete(pmsBlogAppendix);
        if (i > 0) {
            return StatusResult.ok(i, StatusResult.DELETE_SUCCESS);
        }
        // 异常返回
        return StatusResult.error(StatusResult.DELETE_FAIL);
    }

    @Override
    public StatusResult findByBlogId(PmsBlogAppendix pmsBlogAppendix) throws Exception {
        List<PmsBlogAppendix> byBlogId = pmsBlogAppendixMapper.findByBlogId(pmsBlogAppendix);

        if (byBlogId != null) {
            return StatusResult.ok(byBlogId);
        }
        return StatusResult.none(StatusResult.FIND_NONE);
    }
}
