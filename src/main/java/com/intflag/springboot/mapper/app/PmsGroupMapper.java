package com.intflag.springboot.mapper.app;

import com.intflag.springboot.entity.app.PmsGroup;
import com.intflag.springboot.entity.app.PmsGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsGroupMapper {
    int countByExample(PmsGroupExample example);

    int deleteByExample(PmsGroupExample example);

    int deleteByPrimaryKey(String groupId);

    int insert(PmsGroup record);

    int insertSelective(PmsGroup record);

    List<PmsGroup> selectByExample(PmsGroupExample example);

    PmsGroup selectByPrimaryKey(String groupId);

    int updateByExampleSelective(@Param("record") PmsGroup record, @Param("example") PmsGroupExample example);

    int updateByExample(@Param("record") PmsGroup record, @Param("example") PmsGroupExample example);

    int updateByPrimaryKeySelective(PmsGroup record);

    int updateByPrimaryKey(PmsGroup record);
}