package com.clc.gpm.service;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.dao.mapper.GraduationthesisMapper;
import com.clc.gpm.dao.mapper.RegistrationFormMapper;
import com.clc.gpm.dao.mapper.UserMapper;
import com.clc.gpm.entity.BaseEntity;
import com.clc.gpm.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommonService {

    @Autowired
    private RegistrationFormMapper registrationFormMapper;

    @Autowired
    private GraduationthesisMapper graduationthesisMapper;


    private static String userLogin = "USER_LOGIN";

    public static void map(Object source, Object destination) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.map(source, destination);
    }

    public static void copyCreateInfo(BaseEntity baseEntity) {
        baseEntity.setCreateUser(userLogin);
        baseEntity.setCreateTime(LocalDateTime.now());
        baseEntity.setUpdateUser(userLogin);
        baseEntity.setUpdateTime(LocalDateTime.now());
        baseEntity.setDeleteFlg(CommonConstants.DB_AVAILABLE);
    }

    @Autowired
    private UserMapper userMapper;

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


    public Boolean checkExitsRegistForm() {
        Integer currentUserId = getCurrentUserId();

        //Check tinh trang dang ki
        if (registrationFormMapper.checkExitsByUserId(currentUserId) > 0) {
            return true;
        }
        return false;
    }

    public Boolean checkExitsGP() {
        Integer currentUserId = getCurrentUserId();

        //Check tinh trang dang ki
        if (graduationthesisMapper.checkExitsByUserId(currentUserId) > 0) {
            return true;
        }
        return false;
    }




        Integer solved(int[] input){
            int sum = 0;
            int min = getMinNum(input);
            for(int i = 0; i < input.length; i++){
                if (input[i] != min)
                    sum += input[i];
            }
            return sum;
        }


        Integer getMinNum(int[] input){
            if(input.length < 1)
                return 0;
            else {
                int min = input[0];
                for(int i = 0; i < input.length; i++){
                    if (input[i] < min)
                        min = input[i];
                }
                return min;
            }
        }


























}


