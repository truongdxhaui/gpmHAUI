package com.clc.gpm.service.Impl;

import com.clc.gpm.dao.mapper.FacultyMapper;
import com.clc.gpm.dao.mapper.LevelMapper;
import com.clc.gpm.dao.mapper.ProjectMapper;
import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.ProjectDTO;
import com.clc.gpm.dto.search.SearchFacultyDTO;
import com.clc.gpm.entity.Level;
import com.clc.gpm.service.StudentService;
import com.clc.gpm.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private LevelMapper levelMapper;

    @Override
    public StudentVO getAllFacultyAndLevel() {
        StudentVO studentVO = new StudentVO();
        List<FacultyDTO> lstFaculty = facultyMapper.getAllFaculty(new SearchFacultyDTO());
        List<Level> lstLevelDTO = levelMapper.getAllLevel();

        studentVO.setLstFaculty(lstFaculty);
        studentVO.setLstLevel(lstLevelDTO);
        return studentVO;
    }

    @Override
    public StudentVO getListProjectByFacultyIdAndLevelId(String facultyId, String levelId) {
        StudentVO studentVO = new StudentVO();

        List<ProjectDTO> lstProject = projectMapper.getAllProjectByFaculty(facultyId, levelId);
        studentVO.setLstProject(lstProject);
        return studentVO;
    }
}
