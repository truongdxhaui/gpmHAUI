package com.clc.gpm.service;

import com.clc.gpm.form.search.SearchLecturerForm;
import com.clc.gpm.form.search.SearchProjectForm;
import com.clc.gpm.vo.UserVO;

public interface UserService {

    public UserVO getAllFaculty();

    public UserVO getListLecturer(SearchLecturerForm searchLecturerForm);

    public UserVO getAllFacultyAndLevel();

    public UserVO getListProjectByFacultyIdAndLevelId(SearchProjectForm searchProjectForm);

    public UserVO getListProjectByLeaderId(SearchProjectForm searchProjectForm);

    public UserVO getListProjectEnableByLeaderId(SearchProjectForm searchProjectForm);

    public Integer countAllProject(SearchProjectForm searchProjectForm);

    public Integer countAllProjectEnable(SearchProjectForm searchProjectForm);

    public Integer countAllLecturer(SearchLecturerForm searchLecturerForm);

    public Boolean checkExitsRegisterByUserId();




}
