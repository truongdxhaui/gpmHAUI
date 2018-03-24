package com.clc.gpm.vo;

import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.LecturerDTO;
import com.clc.gpm.dto.ProjectDTO;
import com.clc.gpm.entity.Level;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class StudentVO {

    private List<ProjectDTO> lstProject;

    private List<FacultyDTO> lstFaculty;

    private List<LecturerDTO> lstLecturer;

    private List<Level> lstLevel;

}
