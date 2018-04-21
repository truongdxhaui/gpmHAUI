package com.clc.gpm.service.Impl;

import com.clc.gpm.dao.mapper.*;
import com.clc.gpm.dto.RegisterFormDTO;
import com.clc.gpm.dto.UserDTO;
import com.clc.gpm.entity.Team;
import com.clc.gpm.entity.Team_User;
import com.clc.gpm.service.BaseService;
import com.clc.gpm.service.CommonService;
import com.clc.gpm.service.StudentProjectService;
import com.clc.gpm.service.StudentService;
import com.clc.gpm.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentProjectServiceImpl extends BaseService implements StudentProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private TeamUserMapper teamUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RegistrationFormMapper registrationFormMapper;

    @Autowired
    CommonService commonService;

    @Autowired
    StudentService studentService;

    @Autowired
    private TeamMapper teamMapper;


    @Override
    public StudentVO getRegisterFormByProjectId(int projectId) {
        StudentVO studentVO = new StudentVO();
        Integer currentUserId = commonService.getCurrentUserId();

        RegisterFormDTO registerFormDTO = registrationFormMapper.getStudentByUserId(currentUserId, projectId);

        if (registerFormDTO != null && registerFormDTO.getUserId() != null) {
            UserDTO userDTO = userMapper.getLecturerInfoForRegistform(registerFormDTO.getLecturerId());
            registerFormDTO.setLecturerInfo(userDTO);
        }

        studentVO.setRegisterFormDTO(registerFormDTO);

        return studentVO;
    }

    @Override
    public StudentVO getRegisterForm() {
        StudentVO studentVO = new StudentVO();
        Integer currentUserId = commonService.getCurrentUserId();

        Integer teamId = getTeamId();

        RegisterFormDTO registerFormDTO = registrationFormMapper.getRegistrationForm(currentUserId, teamId);

        if (registerFormDTO != null && registerFormDTO.getUserId() != null) {
            UserDTO userDTO = userMapper.getLecturerInfoForRegistform(registerFormDTO.getUserId());
            registerFormDTO.setLecturerInfo(userDTO);
        }

        studentVO.setRegisterFormDTO(registerFormDTO);

        return studentVO;
    }

    //Lay ma team cua sinh vien. Nei chua co thi tao moi
    private Integer getTeamId() {
        Integer currentUserId = commonService.getCurrentUserId();

        int teamIdCheck = teamMapper.checkTeamIdByUserName(currentUserId);

        if (teamIdCheck <= 0) {
            //Tu dong tao team moi va lay ID
            return autoGenerateTeamId(currentUserId);
        } else {
            return teamMapper.getTeamId(currentUserId);
        }
    }

    private Integer autoGenerateTeamId(int userId) {
        Team team = new Team();
        copyCreateInfoEntity(team);
        Integer result = teamMapper.insert(team);
        if (result > 0) {
            teamUserMapper.insert(new Team_User(team.getId(),userId));
            return team.getId();
        }
        return -1;
    }
}
