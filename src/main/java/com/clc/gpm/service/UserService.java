package com.clc.gpm.service;

import com.clc.gpm.vo.UserVO;

public interface UserService {

    public UserVO getAllFaculty();

    public UserVO getListLecturer(String facultyId);

    public UserVO getAllFacultyAndLevel();

    public UserVO getListProjectByFacultyIdAndLevelId(String facultyId, String levelId);

    public UserVO getListProjectByLeaderId(String lecturerId);
}
