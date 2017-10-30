package com.hsu.netchat.dao;

import com.hsu.netchat.bean.DevlpTeam;
import com.hsu.netchat.bean.DevlpTeamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DevlpTeamMapper {
    long countByExample(DevlpTeamExample example);

    int deleteByExample(DevlpTeamExample example);

    int deleteByPrimaryKey(String team);

    int insert(DevlpTeam record);

    int insertSelective(DevlpTeam record);

    List<DevlpTeam> selectByExample(DevlpTeamExample example);

    DevlpTeam selectByPrimaryKey(String team);

    int updateByExampleSelective(@Param("record") DevlpTeam record, @Param("example") DevlpTeamExample example);

    int updateByExample(@Param("record") DevlpTeam record, @Param("example") DevlpTeamExample example);

    int updateByPrimaryKeySelective(DevlpTeam record);

    int updateByPrimaryKey(DevlpTeam record);
}