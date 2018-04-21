package com.clc.gpm.service;

import com.clc.gpm.form.search.SearchLecturerForm;
import com.clc.gpm.form.search.SearchProjectForm;
import com.clc.gpm.form.search.SearchRegisterForm;
import com.clc.gpm.vo.UserVO;

public interface UserService {

    UserVO getAllFaculty();

    UserVO getListLecturer(SearchLecturerForm searchLecturerForm);

    UserVO getAllFacultyAndLevel();

     UserVO getListProjectByFacultyIdAndLevelId(SearchProjectForm searchProjectForm);

    UserVO getListProjectByLeaderId(SearchProjectForm searchProjectForm);

    UserVO getListProjectEnableByLeaderId(SearchProjectForm searchProjectForm);

    Integer countAllProject(SearchProjectForm searchProjectForm);

    Integer countAllProjectEnable(SearchProjectForm searchProjectForm);

    Integer countAllLecturer(SearchLecturerForm searchLecturerForm);

    Boolean checkExitsRegisterByUserId();

    UserVO getRequestRegisterForm(SearchRegisterForm searchProjectForm);

    int countAllListRegisterForm(SearchRegisterForm searchProjectForm);

    UserVO getListGP(SearchRegisterForm searchRegisterForm);

    int countAllGP(SearchRegisterForm searchProjectForm);
}
