package com.clc.gpm.service;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.dao.mapper.GraduationthesisMapper;
import com.clc.gpm.dao.mapper.LecturerMapper;
import com.clc.gpm.dao.mapper.RegistrationFormMapper;
import com.clc.gpm.dao.mapper.UserMapper;
import com.clc.gpm.dto.UserDTO;
import com.clc.gpm.entity.BaseEntity;
import com.clc.gpm.entity.Lecturer;
import com.clc.gpm.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * The type Common service.
 */
@Service
public class CommonService {

    @Autowired
    private RegistrationFormMapper registrationFormMapper;

    @Autowired
    private GraduationthesisMapper graduationthesisMapper;

    @Autowired
    private LecturerMapper lecturerMapper;

    private static String userLogin = "USER_LOGIN";

    /**
     * Map.
     *
     * @param source      the source
     * @param destination the destination
     */
    public static void map(Object source, Object destination) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.map(source, destination);
    }

    /**
     * Copy create info.
     *
     * @param baseEntity the base entity
     */
    public static void copyCreateInfo(BaseEntity baseEntity) {
        baseEntity.setCreateUser(userLogin);
        baseEntity.setCreateTime(LocalDateTime.now());
        baseEntity.setUpdateUser(userLogin);
        baseEntity.setUpdateTime(LocalDateTime.now());
        baseEntity.setDeleteFlg(CommonConstants.DB_AVAILABLE);
    }

    @Autowired
    private UserMapper userMapper;

    /**
     * Gets current user id.
     *
     * @return the current user id
     */
    public Integer getCurrentUserId() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String id = null;
        if (authentication != null)
            if (authentication.getPrincipal() instanceof UserDetails)
                id = ((UserDetails) authentication.getPrincipal()).getUsername();
            else if (authentication.getPrincipal() instanceof String)
                id = (String) authentication.getPrincipal();
        try {
            User user = userMapper.findByUsername(id);
            return user != null ? user.getId() : -1; //anonymoususer
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Gets current username.
     *
     * @return the current username
     */
    public String getCurrentUsername() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String id = null;
        if (authentication != null)
            if (authentication.getPrincipal() instanceof UserDetails)
                id = ((UserDetails) authentication.getPrincipal()).getUsername();
            else if (authentication.getPrincipal() instanceof String)
                id = (String) authentication.getPrincipal();
        try {
            return id;
        } catch (NumberFormatException e) {
            return "";
        }
    }


    /**
     * Check exits regist form boolean.
     *
     * @return the boolean
     */
    public Boolean checkExitsRegistForm() {
        Integer currentUserId = getCurrentUserId();

        //Check tinh trang dang ki
        if (registrationFormMapper.checkExitsByUserId(currentUserId) > 0) {
            return true;
        }
        return false;
    }

    /**
     * Check exits gp boolean.
     *
     * @return the boolean
     */
    public Boolean checkExitsGP() {
        Integer currentUserId = getCurrentUserId();

        //Check tinh trang dang ki
        if (graduationthesisMapper.checkExitsByUserId(currentUserId) > 0) {
            return true;
        }
        return false;
    }


    /**
     * Gets lecturer id.
     *
     * @return the lecturer id
     */
//Get current LecturerID
    public String getLecturerId() {
        Lecturer lecturer = new Lecturer();
        lecturer.setUserId(getCurrentUserId());

        Lecturer lecturer1 = (Lecturer) lecturerMapper.selectWithExample(lecturer);

        if (lecturer1 != null)
            return lecturer.getLecturerId();
        return "";
    }


    /**
     * Logined infor user dto.
     *
     * @return the user dto
     */
    public UserDTO loginedInfor() {
        String currentUsername = getCurrentUsername();
        if (currentUsername == null) {
            return new UserDTO();
        }
        return userMapper.getLoginedInfor(currentUsername);
    }


}


