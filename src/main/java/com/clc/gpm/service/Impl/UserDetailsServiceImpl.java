package com.clc.gpm.service.Impl;

import com.clc.gpm.dao.mapper.*;
import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.LecturerDTO;
import com.clc.gpm.dto.ProjectDTO;
import com.clc.gpm.dto.RegisterFormDTO;
import com.clc.gpm.dto.search.SearchFacultyDTO;
import com.clc.gpm.dto.search.SearchLecturerDTO;
import com.clc.gpm.dto.search.SearchProjectDTO;
import com.clc.gpm.dto.search.SearchRegisterFormDTO;
import com.clc.gpm.entity.Lecturer;
import com.clc.gpm.entity.Level;
import com.clc.gpm.entity.Role;
import com.clc.gpm.entity.User;
import com.clc.gpm.form.search.SearchLecturerForm;
import com.clc.gpm.form.search.SearchProjectForm;
import com.clc.gpm.form.search.SearchRegisterForm;
import com.clc.gpm.service.CommonService;
import com.clc.gpm.service.LecturerService;
import com.clc.gpm.service.UserService;
import com.clc.gpm.utils.PageUtil;
import com.clc.gpm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type User details service.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService{

    /**
     * default sort
     */
    private static final String DEFAULT_SORT_PROJECT = "p.ID";

    /**
     * LST_COLUMN_ID
     */
    private static final String[] HEADER_SORT = {DEFAULT_SORT_PROJECT, "p.NAME", "p.DESCRIPTION", "p.LECTURER_ID", "level.LEVEL_NAME"};

    private static final String DEFAULT_SORT_LIST_LECTURER = "l.LECTURER_ID";

    private static final String[] HEADER_SORT_LIST_LECTURER = {DEFAULT_SORT_LIST_LECTURER, "u.FIRSTNAME", "u.LASTNAME", "u.DESCRIPTION", "projectNum"};

    private static final String DEFAULT_SORT_LIST_PROJECT_BY_LEADERID = "p.ID";

    private static final String[] HEADER_SORT_LIST_PROJECT_BY_LEADERID = {DEFAULT_SORT_LIST_PROJECT_BY_LEADERID, "p.NAME", "p.DESCRIPTION", "p.LECTURER_ID", "level.LEVEL_NAME"};

    private static final String DEFAULT_SORT_REGISTERFORM = "teamId";

    private static final String[] HEADER_SORT_LIST_REGISTERFORM = {DEFAULT_SORT_REGISTERFORM, "projectName", "createTime"};

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RolesMapper roleMapper;

    @Autowired
    private FacultyMapper facultyMapper;

    @Autowired
    private LecturerMapper lecturerMapper;

    @Autowired
    private LevelMapper levelMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private RegistrationFormMapper registrationFormMapper;

    @Autowired
    private LecturerService lecturerService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        Set<Role> roles = roleMapper.getAllRoleByUserId(user.getId());

        for (Role role:
             roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new  org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public UserVO getAllFaculty() {
        UserVO userVO = new UserVO();
        userVO.setLstFaculty(facultyMapper.getAllFaculty(new SearchFacultyDTO()));

        return userVO;
    }

    @Override
    public UserVO getListLecturer(SearchLecturerForm searchLecturerForm) {
        UserVO userVO = new UserVO();
        SearchProjectDTO searchProjectDTO = new SearchProjectDTO();
        CommonService.map(searchLecturerForm, searchProjectDTO);
        PageUtil.initSearchDTO(searchProjectDTO, HEADER_SORT_LIST_LECTURER, DEFAULT_SORT_LIST_LECTURER);
        List<LecturerDTO> lstLecturer = lecturerMapper.getListLecturerByFacultyId(searchProjectDTO);
        userVO.setLstLecturer(lstLecturer);
        return userVO;
    }

    @Override
    public UserVO getAllFacultyAndLevel() {
        UserVO userVO = new UserVO();
        List<FacultyDTO> lstFaculty = facultyMapper.getAllFaculty(new SearchFacultyDTO());
        List<Level> lstLevelDTO = levelMapper.getAllLevel();

        userVO.setLstFaculty(lstFaculty);
        userVO.setLstLevel(lstLevelDTO);
        return userVO;
    }

    @Override
    public UserVO getListProjectByFacultyIdAndLevelId(SearchProjectForm searchProjectForm) {
        UserVO userVO = new UserVO();

        SearchProjectDTO searchProjectDTO = new SearchProjectDTO();
        CommonService.map(searchProjectForm, searchProjectDTO);
        PageUtil.initSearchDTO(searchProjectDTO, HEADER_SORT, DEFAULT_SORT_PROJECT);
        List<ProjectDTO> lstProject = projectMapper.getAllProjectByFaculty(searchProjectDTO);
        userVO.setLstProject(lstProject);

        return userVO;
    }

    @Override
    public UserVO getListProjectByLeaderId(SearchProjectForm searchProjectForm) {
        if (searchProjectForm.getLecturerId() == null) {
            Integer userId = commonService.getCurrentUserId();
            Lecturer lecturer = new Lecturer();
            lecturer.setUserId(userId);
            List<Lecturer> newLecturer = lecturerMapper.selectWithExample(lecturer);
            if (!CollectionUtils.isEmpty(newLecturer)) {
                searchProjectForm.setLecturerId(newLecturer.get(0).getLecturerId().toString());
            }
        }
        SearchProjectDTO searchProjectDTO = new SearchProjectDTO();
        CommonService.map(searchProjectForm, searchProjectDTO);
        PageUtil.initSearchDTO(searchProjectDTO, HEADER_SORT_LIST_PROJECT_BY_LEADERID, DEFAULT_SORT_LIST_PROJECT_BY_LEADERID);
        UserVO userVO = new UserVO();
        userVO.setLstProject(projectMapper.getListProjectByLecturerId(searchProjectDTO));

        return userVO;
    }

    @Override
    public UserVO getListProjectEnableByLeaderId(SearchProjectForm searchProjectForm) {
        if (searchProjectForm.getLecturerId() == null) {
            Integer userId = commonService.getCurrentUserId();
            Lecturer lecturer = new Lecturer();
            lecturer.setUserId(userId);
            List<Lecturer> newLecturer = lecturerMapper.selectWithExample(lecturer);
            if (!CollectionUtils.isEmpty(newLecturer)) {
                searchProjectForm.setLecturerId(newLecturer.get(0).getLecturerId().toString());
            }
        }
        SearchProjectDTO searchProjectDTO = new SearchProjectDTO();
        CommonService.map(searchProjectForm, searchProjectDTO);
        PageUtil.initSearchDTO(searchProjectDTO, HEADER_SORT_LIST_PROJECT_BY_LEADERID, DEFAULT_SORT_LIST_PROJECT_BY_LEADERID);
        UserVO userVO = new UserVO();
        userVO.setLstProject(projectMapper.getListProjectEnableByLecturerId(searchProjectDTO));

        return userVO;
    }

    @Override
    public Integer countAllProject(SearchProjectForm searchProjectForm) {
        SearchProjectDTO searchProjectDTO = new SearchProjectDTO();
        CommonService.map(searchProjectForm, searchProjectDTO);
        int result = projectMapper.countAllProjectByFacultyAndLevel(searchProjectDTO);
        return result;
    }

    @Override
    public Integer countAllProjectEnable(SearchProjectForm searchProjectForm) {
        SearchProjectDTO searchProjectDTO = new SearchProjectDTO();
        CommonService.map(searchProjectForm, searchProjectDTO);
        int result = projectMapper.countAllProjectEnableByFacultyAndLevel(searchProjectDTO);
        return result;
    }

    @Override
    public Integer countAllLecturer(SearchLecturerForm searchLecturerForm) {
        SearchLecturerDTO searchLecturerDTO = new SearchLecturerDTO();
        CommonService.map(searchLecturerForm, searchLecturerDTO);
        return lecturerMapper.countAllLecturerByFacultyId(searchLecturerDTO);
    }

    @Override
    public Boolean checkExitsRegisterByUserId() {
        if (commonService.checkExitsGP()) {
            return true;
        }
        if (commonService.checkExitsRegistForm()) {
            return true;
        }
        return false;
    }

    @Override
    public UserVO getRequestRegisterForm(SearchRegisterForm searchRegisterForm) {
        UserVO userVO = new UserVO();
        searchRegisterForm.setLecturerId(lecturerService.getLecturerId());
        SearchRegisterFormDTO searchRegisterFormDTO = new SearchRegisterFormDTO();
        CommonService.map(searchRegisterForm, searchRegisterFormDTO);
        PageUtil.initSearchDTO(searchRegisterFormDTO, HEADER_SORT_LIST_REGISTERFORM, DEFAULT_SORT_REGISTERFORM);
        List<RegisterFormDTO> lstRegisterFormDTO = registrationFormMapper.getAllRegistForm(searchRegisterFormDTO);
        userVO.setLstRegisterFormDTO(lstRegisterFormDTO);

        return userVO;
    }

    @Override
    public int countAllListRegisterForm(SearchRegisterForm searchProjectForm) {
        searchProjectForm.setLecturerId(lecturerService.getLecturerId());
        return registrationFormMapper.countRequestRegistForm(searchProjectForm);
    }

    @Override
    public UserVO getListGP(SearchRegisterForm searchRegisterForm) {
        UserVO userVO = new UserVO();
        searchRegisterForm.setLecturerId(lecturerService.getLecturerId());
        SearchRegisterFormDTO searchRegisterFormDTO = new SearchRegisterFormDTO();
        CommonService.map(searchRegisterForm, searchRegisterFormDTO);
        PageUtil.initSearchDTO(searchRegisterFormDTO, HEADER_SORT_LIST_REGISTERFORM, DEFAULT_SORT_REGISTERFORM);
        List<RegisterFormDTO> lstRegisterFormDTO = registrationFormMapper.getAllGP(searchRegisterFormDTO);
        userVO.setLstRegisterFormDTO(lstRegisterFormDTO);

        return userVO;
    }

    @Override
    public int countAllGP(SearchRegisterForm searchProjectForm) {
        searchProjectForm.setLecturerId(lecturerService.getLecturerId());
        return registrationFormMapper.countGP(searchProjectForm);
    }

}
