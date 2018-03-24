package com.clc.gpm.service.Impl;

import com.clc.gpm.dao.mapper.CommonMapper;
import com.clc.gpm.dao.mapper.UserMapper;
import com.clc.gpm.dto.search.SearchUserDTO;
import com.clc.gpm.service.AdminUserManagerService;
import com.clc.gpm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserManagerServiceImpl implements AdminUserManagerService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CommonMapper commonMapper;

    @Override
    public UserVO getAllUser(SearchUserDTO searchUserDTO) {
        UserVO userVO = new UserVO();
        userVO.setLstUser(userMapper.selectAllUser(searchUserDTO));

        return userVO;
    }

    @Override
    public int countAllUser() {
        return 0;
    }
}
