package com.clc.gpm.vo;

import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.LecturerDTO;
import com.clc.gpm.dto.ProjectDTO;
import com.clc.gpm.entity.Level;
import com.clc.gpm.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserVO {

    private List<User> lstUser;

    private List<FacultyDTO> lstFaculty;

    private List<LecturerDTO> lstLecturer;

    private List<Level> lstLevel;

    private List<ProjectDTO> lstProject;
}
