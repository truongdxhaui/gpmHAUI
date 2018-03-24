package com.clc.gpm.service;

import com.clc.gpm.dto.search.SearchUserDTO;
import com.clc.gpm.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * The interface Admin user manager service.
 */

@Service
public interface AdminUserManagerService {
    /**
     * Gets all user.
     *
     * @param searchUserDTO the search user dto
     * @return the all user
     */
    public UserVO getAllUser(SearchUserDTO searchUserDTO);

    /**
     * Count all user int.
     *
     * @return the int
     */
    public int countAllUser();
}
