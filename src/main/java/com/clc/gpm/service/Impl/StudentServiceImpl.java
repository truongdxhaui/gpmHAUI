package com.clc.gpm.service.Impl;

import com.clc.gpm.dao.mapper.*;
import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.search.SearchFacultyDTO;
import com.clc.gpm.entity.Level;
import com.clc.gpm.entity.RegistrationForm;
import com.clc.gpm.entity.Team;
import com.clc.gpm.entity.Team_User;
import com.clc.gpm.form.RegisterGPForm;
import com.clc.gpm.service.BaseService;
import com.clc.gpm.service.CommonService;
import com.clc.gpm.service.StudentService;
import com.clc.gpm.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends BaseService implements StudentService {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamUserMapper teamUserMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private LevelMapper levelMapper;

    @Autowired
    private RegistrationFormMapper registrationFormMapper;

    @Autowired
    private CommonService commonService;
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

      /*  List<ProjectDTO> lstProject = projectMapper.getAllProjectByFaculty(facultyId, levelId);
        studentVO.setLstProject(lstProject);*/
        return studentVO;
    }

    @Override
    public boolean submitRegisterGPForm(RegisterGPForm registerFormDTO) {
        Integer teamId = getTeamId();

        RegistrationForm registrationForm = new RegistrationForm();
        CommonService.map(registerFormDTO, registrationForm);
        registrationForm.setTeamId(teamId);
        int result = registrationFormMapper.insert(registrationForm);
        if (result == 0) {
            rollBack("F0001");
            return false;
        }
        return true;
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
