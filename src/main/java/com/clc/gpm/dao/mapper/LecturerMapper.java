package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.dto.LecturerDTO;
import com.clc.gpm.dto.search.SearchLecturerDTO;
import com.clc.gpm.dto.search.SearchProjectDTO;
import com.clc.gpm.entity.Lecturer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LecturerMapper extends BaseMapper<Lecturer> {

    List<LecturerDTO> getAllLecturerSummary(SearchLecturerDTO searchLecturerDTO);

    List<LecturerDTO> getListLecturerByFacultyId(SearchProjectDTO searchProjectDTO);

    int countAllLecturerByFacultyId(SearchLecturerDTO searchLecturerDTO);

}
