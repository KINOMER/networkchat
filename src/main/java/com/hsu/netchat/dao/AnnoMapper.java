package com.hsu.netchat.dao;

import com.hsu.netchat.bean.Anno;
import com.hsu.netchat.bean.AnnoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnoMapper {
    long countByExample(AnnoExample example);

    int deleteByExample(AnnoExample example);

    int deleteByPrimaryKey(String anno);

    int insert(Anno record);

    int insertSelective(Anno record);

    List<Anno> selectByExample(AnnoExample example);

    int updateByExampleSelective(@Param("record") Anno record, @Param("example") AnnoExample example);

    int updateByExample(@Param("record") Anno record, @Param("example") AnnoExample example);
}