package com.clc.gpm.controller.user;

import com.clc.gpm.controller.PaginationController;
import com.clc.gpm.form.search.SearchLecturerForm;
import com.clc.gpm.form.search.SearchProjectForm;
import com.clc.gpm.service.UserService;
import com.clc.gpm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController extends PaginationController {

    @Autowired
    UserService userService;

    @GetMapping("/list-lecturer")
    public ModelAndView initLecturerList() {
        ModelAndView modelAndView = new ModelAndView("sites/user/user_lecturer_list");
        UserVO userVO = userService.getAllFaculty();
        modelAndView.addObject("userVO", userVO);
        modelAndView.addObject("searchLecturerForm", new SearchLecturerForm());
        return modelAndView;
    }

    @GetMapping("/get-lecturer")
    public ModelAndView getListLecturer(SearchLecturerForm searchLecturerForm) {
        ModelAndView modelAndView = new ModelAndView("sites/common/list-lecturer");

        UserVO userVO = userService.getListLecturer(searchLecturerForm);
        int totalRow = 0;
        if (!CollectionUtils.isEmpty(userVO.getLstLecturer())) {
            totalRow = userService.countAllLecturer(searchLecturerForm);
            modelAndView.addObject("totalRowCount", totalRow);
            setPaginationData(modelAndView, searchLecturerForm.getPage(), totalRow);
        }else {
            setPaginationData(modelAndView, 0, totalRow);
        }
        modelAndView.addObject("userVO", userVO);
        modelAndView.addObject("searchLecturerForm", searchLecturerForm);
        return modelAndView;
    }


    @GetMapping("list-project")
    public ModelAndView initProjectList() {
        ModelAndView modelAndView = new ModelAndView("sites/user/user_project_list");

        UserVO userVO = userService.getAllFacultyAndLevel();

        modelAndView.addObject("userVO", userVO);
        modelAndView.addObject("searchProjectForm", new SearchProjectForm());
        return modelAndView;
    }

    @GetMapping("/get-project")
    public ModelAndView getListProject(SearchProjectForm searchProjectForm) {
        ModelAndView modelAndView = new ModelAndView("sites/common/list-project");

        //Check xem da dang ki do an chua
        if(userService.checkExitsRegisterByUserId()){
            modelAndView.addObject("registed", true);
        }

        UserVO userVO = userService.getListProjectByFacultyIdAndLevelId(searchProjectForm);

        modelAndView.addObject("userVO", userVO);
        int totalRow = 0;
        if (!CollectionUtils.isEmpty(userVO.getLstProject())) {
            totalRow = userService.countAllProjectEnable(searchProjectForm);
            modelAndView.addObject("totalRowCount", totalRow);
            setPaginationData(modelAndView, searchProjectForm.getPage(), totalRow);
        }else {
            setPaginationData(modelAndView, 0, totalRow);
        }
        return modelAndView;
    }

    @GetMapping("/list-lecturer/list-project")
    public ModelAndView initLecturerProjectList(SearchProjectForm searchProjectForm) {
        ModelAndView modelAndView = new ModelAndView("sites/user/user_lecturer_project_list");

        UserVO userVO = userService.getListProjectEnableByLeaderId(searchProjectForm);

        modelAndView.addObject("userVO", userVO);
        int totalRow = 0;
        if (!CollectionUtils.isEmpty(userVO.getLstProject())) {
            totalRow = userService.countAllProjectEnable(searchProjectForm);
            modelAndView.addObject("totalRowCount", totalRow);
            setPaginationData(modelAndView, searchProjectForm.getPage(), totalRow);
        }else {
            setPaginationData(modelAndView, 0, totalRow);
        }

        modelAndView.addObject("searchProjectForm", searchProjectForm);
        return modelAndView;
    }


}
