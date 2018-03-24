package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface RolesMapper extends BaseMapper<Role> {

    public Set<Role> getAllRoleByUserId(@Param("userId") int userId);

    
}
