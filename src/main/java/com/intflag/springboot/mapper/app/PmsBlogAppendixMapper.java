package com.intflag.springboot.mapper.app;

import com.intflag.springboot.entity.app.PmsBlogAppendix;

import java.util.List;

/**
 * @author liugx  QQ:1598749808
 * @version V1.0
 * @date 2019-03-13 21:18
 * @Description
 */
public interface PmsBlogAppendixMapper {

    List<PmsBlogAppendix> findAll();

    int insert(PmsBlogAppendix pmsBlogAppendix);
    int delete(PmsBlogAppendix pmsBlogAppendix);
    List<PmsBlogAppendix> findByBlogId(PmsBlogAppendix pmsBlogAppendix);
}
