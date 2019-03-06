package com.intflag.springboot.mapper.app;

import com.intflag.springboot.entity.app.PmsAppendix;
import com.intflag.springboot.entity.app.PmsAppendixExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsAppendixMapper {
    int countByExample(PmsAppendixExample example);

    int deleteByExample(PmsAppendixExample example);

    int deleteByPrimaryKey(String appendixId);

    int insert(PmsAppendix record);

    int insertSelective(PmsAppendix record);

    List<PmsAppendix> selectByExample(PmsAppendixExample example);

    PmsAppendix selectByPrimaryKey(String appendixId);

    int updateByExampleSelective(@Param("record") PmsAppendix record, @Param("example") PmsAppendixExample example);

    int updateByExample(@Param("record") PmsAppendix record, @Param("example") PmsAppendixExample example);

    int updateByPrimaryKeySelective(PmsAppendix record);

    int updateByPrimaryKey(PmsAppendix record);
}