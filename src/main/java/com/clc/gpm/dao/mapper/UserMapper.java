package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.dto.UserDTO;
import com.clc.gpm.dto.search.SearchUserDTO;
import com.clc.gpm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUsername(String username);

    List<User> selectAllUser(SearchUserDTO searchUserDTO);

    UserDTO getLecturerInfoForRegistform(@Param("userId") String userId);

    UserDTO getLoginedInfor(String currentUsername);
}
