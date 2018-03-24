package com.clc.gpm.controller.user;

import com.clc.gpm.service.UserService;
import com.clc.gpm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list-lecturer")
    public ModelAndView initLecturerList() {
        ModelAndView modelAndView = new ModelAndView("sites/user/user_lecturer_list");
        UserVO userVO = userService.getAllFaculty();
        modelAndView.addObject("userVO", userVO);

        return modelAndView;
    }

    @GetMapping("/get-lecturer")
    public ModelAndView getListLecturer(@RequestParam("facultyId") String facultyId) {
        ModelAndView modelAndView = new ModelAndView("sites/common/list-lecturer");

        UserVO userVO = userService.getListLecturer(facultyId);

        modelAndView.addObject("userVO", userVO);
        return modelAndView;
    }


    @GetMapping("list-project")
    public ModelAndView initProjectList() {
        ModelAndView modelAndView = new ModelAndView("sites/user/user_project_list");

        UserVO userVO = userService.getAllFacultyAndLevel();

        modelAndView.addObject("userVO", userVO);
        return modelAndView;
    }

    @GetMapping("/get-project")
    public ModelAndView getListProject(@RequestParam("facultyId") String facultyId, @RequestParam("levelId") String levelId) {
        ModelAndView modelAndView = new ModelAndView("sites/common/list-project");

        UserVO userVO = userService.getListProjectByFacultyIdAndLevelId(facultyId, levelId);

        modelAndView.addObject("userVO", userVO);
        return modelAndView;
    }

    @GetMapping("/list-lecturer/list-project")
    public ModelAndView initLecturerProjectList(@RequestParam("lecturerId") String lecturerId){
        ModelAndView modelAndView = new ModelAndView("sites/user/user_lecturer_project_list");

        UserVO userVO = userService.getListProjectByLeaderId(lecturerId);
        modelAndView.addObject("userVO", userVO);
        return modelAndView;
    }


}
