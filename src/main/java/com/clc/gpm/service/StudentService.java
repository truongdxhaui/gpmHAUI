package com.clc.gpm.service;

import com.clc.gpm.form.RegisterGPForm;
import com.clc.gpm.vo.StudentVO;

public interface StudentService {


    public StudentVO getAllFacultyAndLevel();

    public StudentVO getListProjectByFacultyIdAndLevelId(String facultyId, String levelId);

    public boolean submitRegisterGPForm(RegisterGPForm registerFormDTO);

}
