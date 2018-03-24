package com.clc.gpm.common;

import com.clc.gpm.dao.mapper.UserMapper;
import com.clc.gpm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MockData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(userMapper.findByUsername("admin") == null){
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setDeleteFlg("0");

            userMapper.insert(user);
        }
        if(userMapper.findByUsername("gv") == null){
            User user = new User();
            user.setUsername("gv");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setDeleteFlg("0");

            userMapper.insert(user);
        }
    }
}
