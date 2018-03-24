package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.search.SearchFacultyDTO;
import com.clc.gpm.entity.Faculty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FacultyMapper extends BaseMapper<Faculty> {

    public List<FacultyDTO> getAllFaculty(SearchFacultyDTO facultyDTO);

    public int countAllFaculty();

    public int deleteFaculty(@Param("id") Integer id);
}
