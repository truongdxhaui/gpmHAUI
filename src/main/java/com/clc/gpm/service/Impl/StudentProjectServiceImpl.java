package com.clc.gpm.service.Impl;

import com.clc.gpm.dao.mapper.ProjectMapper;
import com.clc.gpm.dao.mapper.RegistrationFormMapper;
import com.clc.gpm.dao.mapper.StudentMapper;
import com.clc.gpm.dao.mapper.UserMapper;
import com.clc.gpm.dto.RegisterFormDTO;
import com.clc.gpm.dto.UserDTO;
import com.clc.gpm.service.CommonService;
import com.clc.gpm.service.StudentProjectService;
import com.clc.gpm.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentProjectServiceImpl implements StudentProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RegistrationFormMapper registrationFormMapper;

    @Autowired
    CommonService commonService;

    @Override
    public StudentVO getRegisterFormByProjectId(int projectId) {
        StudentVO studentVO = new StudentVO();
        Integer currentUserId = commonService.getCurrentUserId();

        RegisterFormDTO registerFormDTO = registrationFormMapper.getStudentByUserId(currentUserId, projectId);

        if(registerFormDTO != null && registerFormDTO.getUserId() != null){
            UserDTO userDTO = userMapper.getLecturerInfoForRegistform(registerFormDTO.getUserId());
            registerFormDTO.setLecturerInfo(userDTO);
        }

        studentVO.setRegisterFormDTO(registerFormDTO);

        return studentVO;
    }
}
