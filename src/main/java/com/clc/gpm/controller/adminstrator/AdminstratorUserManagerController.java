package com.clc.gpm.controller.adminstrator;


import com.clc.gpm.dto.search.SearchUserDTO;
import com.clc.gpm.service.AdminUserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/user-manager")
public class AdminstratorUserManagerController {

    @Autowired
    private AdminUserManagerService adminUserManagerService;

    //Quan ly tai khoan nguoi dung

    @PostMapping("user")
    public ModelAndView initListUserManag(SearchUserDTO searchUserDTO) {
        ModelAndView modelAndView = new ModelAndView("sites/admin/admin-list-user");

       /* if (searchUserDTO == null)
            searchUserDTO = new SearchUserDTO();

        UserVO userVO = adminUserManagerService.getAllUser(searchUserDTO);

        modelAndView.addObject("searchUserDTO", searchUserDTO);
        modelAndView.addObject("userVO", userVO);*/
        return modelAndView;
    }
}
