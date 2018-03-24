package com.clc.gpm.service;

import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.search.SearchFacultyDTO;
import com.clc.gpm.vo.LevelVO;

import java.util.List;

public interface AdminService{

    public List<FacultyDTO> getAllListFaculty(SearchFacultyDTO facultyDTO);

    public int countAllFaculty();

    public LevelVO getAllLevel();

    public Integer addLevel(String obj);
}
