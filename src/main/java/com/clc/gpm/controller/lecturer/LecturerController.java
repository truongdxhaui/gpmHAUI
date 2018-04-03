package com.clc.gpm.controller.lecturer;

import com.clc.gpm.controller.PaginationController;
import com.clc.gpm.form.search.SearchProjectForm;
import com.clc.gpm.service.CommonService;
import com.clc.gpm.service.UserService;
import com.clc.gpm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Lecturer controller.
 */
@Controller
@RequestMapping("/lecturer")
public class LecturerController extends PaginationController {

    @Autowired
    UserService userService;

    @Autowired
    CommonService commonService;

    @GetMapping("")
    public ModelAndView initIndexView(){
        ModelAndView modelAndView = new ModelAndView("sites/lecturer/index");

        return modelAndView;
    }

    @GetMapping("list-project")
    public ModelAndView initListProjectView(SearchProjectForm searchProjectForm){
        ModelAndView modelAndView = new ModelAndView("sites/lecturer/lecturer-list-all-project");
        UserVO userVO = userService.getListProjectByLeaderId(searchProjectForm);

        modelAndView.addObject("userVO", userVO);
        int totalRow = 0;
        if (!CollectionUtils.isEmpty(userVO.getLstProject())) {
            totalRow = userService.countAllProject(searchProjectForm);
            modelAndView.addObject("totalRowCount", totalRow);
            setPaginationData(modelAndView, searchProjectForm.getPage(), totalRow);
        }else {
            setPaginationData(modelAndView, 0, totalRow);
        }


        modelAndView.addObject("searchProjectForm", searchProjectForm);
        return modelAndView;
    }


    @GetMapping("list-enable-project")
    public ModelAndView initListEnableProjectView(SearchProjectForm searchProjectForm){
        ModelAndView modelAndView = new ModelAndView("sites/lecturer/lecturer-list-all-project");
        UserVO userVO = userService.getListProjectByLeaderId(searchProjectForm);

        modelAndView.addObject("userVO", userVO);
        int totalRow = 0;
        if (!CollectionUtils.isEmpty(userVO.getLstProject())) {
            totalRow = userService.countAllProject(searchProjectForm);
            modelAndView.addObject("totalRowCount", totalRow);
            setPaginationData(modelAndView, searchProjectForm.getPage(), totalRow);
        }else {
            setPaginationData(modelAndView, 0, totalRow);
        }


        modelAndView.addObject("searchProjectForm", searchProjectForm);
        return modelAndView;
    }


    @GetMapping("activeProject")
    @ResponseBody
    public String activeProject(String projectId){

        return "OK";
    }
}
