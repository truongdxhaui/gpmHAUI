package com.clc.gpm.service;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.entity.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommonService {

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
}


