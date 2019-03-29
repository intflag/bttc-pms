package com.intflag.springboot.mapper.app;

import com.intflag.springboot.entity.app.PmsPaper;
import com.intflag.springboot.entity.app.PmsPaperExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsPaperMapper {
    int countByExample(PmsPaperExample example);

    int deleteByExample(PmsPaperExample example);

    int deleteByPrimaryKey(String paperId);

    int insert(PmsPaper record);

    int insertSelective(PmsPaper record);

    List<PmsPaper> selectByExample(PmsPaperExample example);

    PmsPaper selectByPrimaryKey(String paperId);

    int updateByExampleSelective(@Param("record") PmsPaper record, @Param("example") PmsPaperExample example);

    int updateByExample(@Param("record") PmsPaper record, @Param("example") PmsPaperExample example);

    int updateByPrimaryKeySelective(PmsPaper record);

    int updateByPrimaryKey(PmsPaper record);
}