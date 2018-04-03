package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.dto.RegisterFormDTO;
import com.clc.gpm.entity.RegistrationForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegistrationFormMapper extends BaseMapper<RegistrationForm> {

    RegisterFormDTO getStudentByUserId(@Param("userId") Integer userId, @Param("projectId") Integer projectId);

    Integer checkExitsByUserId(@Param("userId") Integer userId);

}
