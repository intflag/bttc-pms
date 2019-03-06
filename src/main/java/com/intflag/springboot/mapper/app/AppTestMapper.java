package com.intflag.springboot.mapper.app;

import com.intflag.springboot.entity.app.AppTest;
import com.intflag.springboot.entity.app.AppTestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppTestMapper {
    int countByExample(AppTestExample example);

    int deleteByExample(AppTestExample example);

    int deleteByPrimaryKey(String appId);

    int insert(AppTest record);

    int insertSelective(AppTest record);

    List<AppTest> selectByExample(AppTestExample example);

    AppTest selectByPrimaryKey(String appId);

    int updateByExampleSelective(@Param("record") AppTest record, @Param("example") AppTestExample example);

    int updateByExample(@Param("record") AppTest record, @Param("example") AppTestExample example);

    int updateByPrimaryKeySelective(AppTest record);

    int updateByPrimaryKey(AppTest record);
}