package com.clc.gpm.service.Impl;

import com.clc.gpm.dao.mapper.GraduationthesisMapper;
import com.clc.gpm.dao.mapper.LecturerMapper;
import com.clc.gpm.dao.mapper.ProjectMapper;
import com.clc.gpm.dao.mapper.RegistrationFormMapper;
import com.clc.gpm.entity.Graduationthesis;
import com.clc.gpm.entity.Project;
import com.clc.gpm.entity.RegistrationForm;
import com.clc.gpm.service.BaseService;
import com.clc.gpm.service.CommonService;
import com.clc.gpm.service.LecturerService;
import com.clc.gpm.vo.LecturerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LecturerServiceImpl extends BaseService implements LecturerService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private LecturerMapper lecturerMapper;

    @Autowired
    private RegistrationFormMapper registrationFormMapper;

    @Autowired
    private GraduationthesisMapper graduationthesisMapper;

    @Override
    public Boolean activeProject(Integer projectId) {
        try {
            Project project = projectMapper.selectByPK(new Project(projectId));
            project.setStatus(1);
            projectMapper.updateNotNullByPK(project);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean disableProject(Integer projectId) {
        try {
            Project project = projectMapper.selectByPK(new Project(projectId));
            project.setStatus(0);
            projectMapper.updateNotNullByPK(project);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getLecturerId() {
        Integer currentUserId = commonService.getCurrentUserId();
        String lecturerId = lecturerMapper.getLecturerIdByUserId(currentUserId);
        return lecturerId;
    }

    @Override
    public LecturerVO getRegisterFormDetail(String teamId) {
        LecturerVO lecturerVO = new LecturerVO();
        lecturerVO.setRegisterFormDTO(registrationFormMapper.getRequestRegisterFormDetailNotApprove(teamId));
        return lecturerVO;
    }

    @Override
    public LecturerVO getRegisterFormDetail2(String teamId) {
        LecturerVO lecturerVO = new LecturerVO();
        lecturerVO.setRegisterFormDTO(registrationFormMapper.getRequestRegisterFormDetail(teamId));
        return lecturerVO;
    }

    @Override
    @Transactional
    public Boolean approveRegisterForm(boolean flag, Integer formId) {

        try {
            RegistrationForm registrationForm = registrationFormMapper.findByTeamId(String.valueOf(formId));
            copyUpdateInfoEntity(registrationForm);
            if (flag) {
                if (registrationForm != null) {
                    RegistrationForm newRegis = registrationFormMapper.selectByPK(registrationForm);

                    Graduationthesis graduationthesis = new Graduationthesis();
                    copyCreateInfoEntity(graduationthesis);
                    graduationthesis.setRegistrationFormId(registrationForm.getId());
                    graduationthesis.setStatus(1);

                    if (graduationthesisMapper.insert(graduationthesis) > 0) {
                        newRegis.setStatus(1);
                        registrationFormMapper.updateNotNullByPK(newRegis);
                    }
                    return true;
                }
                return false;
            } else {
                if (registrationForm != null) {
                    RegistrationForm newRegis = registrationFormMapper.selectByPK(registrationForm);
                    newRegis.setStatus(-1);
                    registrationFormMapper.updateNotNullByPK(newRegis);
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public String getNextTeamId() {
        String lecturerId = getLecturerId();

        return registrationFormMapper.getNextTeamId(lecturerId);
    }


}
