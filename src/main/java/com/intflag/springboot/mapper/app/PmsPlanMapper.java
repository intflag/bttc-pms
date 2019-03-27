package com.intflag.springboot.mapper.app;

import com.intflag.springboot.entity.app.PmsPlan;
import com.intflag.springboot.entity.app.PmsPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsPlanMapper {
    int countByExample(PmsPlanExample example);

    int deleteByExample(PmsPlanExample example);

    int deleteByPrimaryKey(String planId);

    int insert(PmsPlan record);

    int insertSelective(PmsPlan record);

    List<PmsPlan> selectByExample(PmsPlanExample example);

    PmsPlan selectByPrimaryKey(String planId);

    int updateByExampleSelective(@Param("record") PmsPlan record, @Param("example") PmsPlanExample example);

    int updateByExample(@Param("record") PmsPlan record, @Param("example") PmsPlanExample example);

    int updateByPrimaryKeySelective(PmsPlan record);

    int updateByPrimaryKey(PmsPlan record);
}