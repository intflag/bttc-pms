package com.intflag.springboot.mapper.app;

import com.intflag.springboot.entity.app.PmsBlog;
import com.intflag.springboot.entity.app.PmsBlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsBlogMapper {
    int countByExample(PmsBlogExample example);

    int deleteByExample(PmsBlogExample example);

    int deleteByPrimaryKey(String blogId);

    int insert(PmsBlog record);

    int insertSelective(PmsBlog record);

    List<PmsBlog> selectByExample(PmsBlogExample example);

    PmsBlog selectByPrimaryKey(String blogId);

    int updateByExampleSelective(@Param("record") PmsBlog record, @Param("example") PmsBlogExample example);

    int updateByExample(@Param("record") PmsBlog record, @Param("example") PmsBlogExample example);

    int updateByPrimaryKeySelective(PmsBlog record);

    int updateByPrimaryKey(PmsBlog record);
}