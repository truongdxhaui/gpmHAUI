package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.dto.ProjectDTO;
import com.clc.gpm.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    List<ProjectDTO> getAllProjectByFaculty(@Param("facultyId") String facultyId, @Param("level") String level);

    List<ProjectDTO> getListProjectByLecturerId(@Param("lecturerId") String lecturerId);

}
