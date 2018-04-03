package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.dto.ProjectDTO;
import com.clc.gpm.dto.search.SearchProjectDTO;
import com.clc.gpm.entity.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    List<ProjectDTO> getAllProjectByFaculty(SearchProjectDTO projectDTO);

    List<ProjectDTO> getListProjectByLecturerId(SearchProjectDTO searchProjectDTO);

    List<ProjectDTO> getListProjectEnableByLecturerId(SearchProjectDTO searchProjectDTO);

    int countAllProjectByFacultyAndLevel(SearchProjectDTO projectDTO);

    int countAllProjectEnableByFacultyAndLevel(SearchProjectDTO projectDTO);


}
