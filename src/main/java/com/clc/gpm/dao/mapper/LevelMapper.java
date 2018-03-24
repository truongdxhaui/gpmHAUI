package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.entity.Level;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LevelMapper extends BaseMapper<Level> {

    public List<Level> getAllLevel();

}
