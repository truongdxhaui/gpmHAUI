package com.clc.gpm.service.Impl;

import com.clc.gpm.dao.mapper.*;
import com.clc.gpm.dto.FacultyDTO;
import com.clc.gpm.dto.LecturerDTO;
import com.clc.gpm.dto.ProjectDTO;
import com.clc.gpm.dto.search.SearchFacultyDTO;
import com.clc.gpm.entity.Level;
import com.clc.gpm.entity.Role;
import com.clc.gpm.entity.User;
import com.clc.gpm.service.UserService;
import com.clc.gpm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type User details service.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService{

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
    public UserVO getListLecturer(String facultyId) {
        UserVO userVO = new UserVO();
        List<LecturerDTO> lstLecturer = lecturerMapper.getListLecturerByFacultyId(facultyId);
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
    public UserVO getListProjectByFacultyIdAndLevelId(String facultyId, String levelId) {
        UserVO userVO = new UserVO();

        List<ProjectDTO> lstProject = projectMapper.getAllProjectByFaculty(facultyId, levelId);
        userVO.setLstProject(lstProject);

        return userVO;
    }

    @Override
    public UserVO getListProjectByLeaderId(String lecturerId) {
        UserVO userVO = new UserVO();
        userVO.setLstProject(projectMapper.getListProjectByLecturerId(lecturerId));

        return userVO;
    }


}
