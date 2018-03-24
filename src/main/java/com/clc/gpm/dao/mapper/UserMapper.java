package com.clc.gpm.dao.mapper;

import com.clc.gpm.dao.common.BaseMapper;
import com.clc.gpm.dto.search.SearchUserDTO;
import com.clc.gpm.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    public User findByUsername(String username);

    public List<User> selectAllUser(SearchUserDTO searchUserDTO);
}
