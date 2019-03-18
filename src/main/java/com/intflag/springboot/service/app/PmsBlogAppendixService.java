package com.intflag.springboot.service.app;

import com.intflag.springboot.common.entity.StatusResult;
import com.intflag.springboot.entity.app.PmsBlogAppendix;

/**
 * @author liugx  QQ:1598749808
 * @version V1.0
 * @date 2019-03-13 21:16
 * @Description 博客附件业务层接口
 */
public interface PmsBlogAppendixService {

    StatusResult findAll() throws Exception;

    StatusResult add(PmsBlogAppendix pmsBlogAppendix) throws Exception;
    StatusResult delete(PmsBlogAppendix pmsBlogAppendix) throws Exception;
    StatusResult findByBlogId(PmsBlogAppendix pmsBlogAppendix) throws Exception;
}
