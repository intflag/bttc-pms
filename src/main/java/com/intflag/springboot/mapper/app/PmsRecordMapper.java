package com.intflag.springboot.mapper.app;

import com.intflag.springboot.entity.app.PmsRecord;
import com.intflag.springboot.entity.app.PmsRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsRecordMapper {
    int countByExample(PmsRecordExample example);

    int deleteByExample(PmsRecordExample example);

    int deleteByPrimaryKey(String recordId);

    int insert(PmsRecord record);

    int insertSelective(PmsRecord record);

    List<PmsRecord> selectByExample(PmsRecordExample example);

    PmsRecord selectByPrimaryKey(String recordId);

    int updateByExampleSelective(@Param("record") PmsRecord record, @Param("example") PmsRecordExample example);

    int updateByExample(@Param("record") PmsRecord record, @Param("example") PmsRecordExample example);

    int updateByPrimaryKeySelective(PmsRecord record);

    int updateByPrimaryKey(PmsRecord record);
}