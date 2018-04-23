package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.dao.common.provider.BaseProvider;
import com.clc.gpm.dto.RegisterFormDTO;
import com.clc.gpm.dto.search.SearchRegisterFormDTO;
import com.clc.gpm.entity.RegistrationForm;
import com.clc.gpm.form.search.SearchRegisterForm;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RegistrationFormMapper extends BaseMapper<RegistrationForm> {

    RegisterFormDTO getStudentByUserId(@Param("userId") Integer userId, @Param("projectId") Integer projectId);

    Integer checkExitsByUserId(@Param("userId") Integer userId);

    RegisterFormDTO getRegistrationForm(@Param("userId") Integer userId, @Param("teamId") Integer teamId);

    @InsertProvider(type = BaseProvider.class, method = "insert")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "ID")
    Integer insert(RegistrationForm registrationForm);

    List<RegisterFormDTO> getAllRegistForm(SearchRegisterFormDTO searchProjectForm);

    List<RegisterFormDTO> getAllGP(SearchRegisterFormDTO searchProjectForm);

    RegisterFormDTO getRequestRegisterFormDetailNotApprove(String teamId);

    RegisterFormDTO getRequestRegisterFormDetail(String teamId);

    int countRequestRegistForm(SearchRegisterForm searchProjectForm);

    int countGP(SearchRegisterForm searchProjectForm);

    List<Integer> getListApproveRegisterForm(String lecturerId);

    String getNextTeamId(String lecturerId);

    RegistrationForm findByTeamId(String teamId);
}
