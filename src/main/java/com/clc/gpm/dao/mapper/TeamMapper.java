package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.dao.common.provider.BaseProvider;
import com.clc.gpm.entity.Team;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeamMapper extends BaseMapper<Team> {

    Integer checkTeamIdByUserName(@Param("userId") Integer userId);

    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ID")
    public Integer insert(Team team);

    Integer getTeamId(Integer userId);

}
