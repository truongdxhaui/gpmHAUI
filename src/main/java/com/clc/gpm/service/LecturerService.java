package com.clc.gpm.service;

import com.clc.gpm.vo.LecturerVO;

public interface LecturerService {

    public Boolean activeProject(Integer projectId);

    public Boolean disableProject(Integer projectId);

    public String getLecturerId();

    LecturerVO getRegisterFormDetail(String teamId);

    LecturerVO getRegisterFormDetail2(String teamId);

    public Boolean approveRegisterForm(boolean flag, Integer formId);

    public String getNextTeamId();
}
