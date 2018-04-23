package com.clc.gpm.controller.lecturer;

import com.clc.gpm.controller.PaginationController;
import com.clc.gpm.form.search.SearchProjectForm;
import com.clc.gpm.form.search.SearchRegisterForm;
import com.clc.gpm.service.CommonService;
import com.clc.gpm.service.LecturerService;
import com.clc.gpm.service.StudentProjectService;
import com.clc.gpm.service.UserService;
import com.clc.gpm.vo.LecturerVO;
import com.clc.gpm.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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

    @Autowired
    LecturerService lecturerService;

    @Autowired
    StudentProjectService studentProjectService;

    @GetMapping("")
    public ModelAndView initIndexView(HttpSession session){
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
        ModelAndView modelAndView = new ModelAndView("sites/lecturer/lecturer-list-enable-project");
        UserVO userVO = userService.getListProjectEnableByLeaderId(searchProjectForm);

        modelAndView.addObject("userVO", userVO);
        int totalRow = 0;
        if (!CollectionUtils.isEmpty(userVO.getLstProject())) {
            totalRow = userService.countAllProjectEnable(searchProjectForm);
            modelAndView.addObject("totalRowCount", totalRow);
            setPaginationData(modelAndView, searchProjectForm.getPage(), totalRow);
        } else {
            setPaginationData(modelAndView, 0, totalRow);
        }

        modelAndView.addObject("searchProjectForm", searchProjectForm);
        return modelAndView;
    }

    @GetMapping("list-registrator-form")
    public ModelAndView initRegisterForm(SearchRegisterForm searchProjectForm) {
        ModelAndView modelAndView = new ModelAndView("sites/lecturer/lecturer-list-register-form");
        UserVO userVO = userService.getRequestRegisterForm(searchProjectForm);

        modelAndView.addObject("userVO", userVO);
        int totalRow = 0;
        if (!CollectionUtils.isEmpty(userVO.getLstRegisterFormDTO())) {
            totalRow = userService.countAllListRegisterForm(searchProjectForm);
            modelAndView.addObject("totalRowCount", totalRow);
            setPaginationData(modelAndView, searchProjectForm.getPage(), totalRow);
        } else {
            setPaginationData(modelAndView, 0, totalRow);
        }

        modelAndView.addObject("searchProjectForm", searchProjectForm);
        return modelAndView;
    }

    @PostMapping("registration-form-detail")
    public ModelAndView getRegistrationForm(String teamId) {
        ModelAndView modelAndView = new ModelAndView("sites/lecturer/lecturer-registration-form-detail");
        if (teamId != null) {
            LecturerVO lecturerVO = lecturerService.getRegisterFormDetail(teamId);
            lecturerVO.getRegisterFormDTO().setTeamId(teamId);
            modelAndView.addObject("registerForm", lecturerVO.getRegisterFormDTO());
        }

        return modelAndView;
    }


    @PostMapping("approve-form-detail")
    public ModelAndView approveRegisterFormm(String teamId, boolean flag) {
        if (!lecturerService.approveRegisterForm(flag, Integer.valueOf(teamId))) {
            return getRegistrationForm(teamId);
        }
        String nextTeamId = lecturerService.getNextTeamId();
        return getRegistrationForm(nextTeamId);
    }

    @GetMapping("listGP")
    public ModelAndView getListGP(SearchRegisterForm searchProjectForm) {
        ModelAndView modelAndView = new ModelAndView("sites/lecturer/lecturer-list-gp");
        UserVO userVO = userService.getListGP(searchProjectForm);

        modelAndView.addObject("userVO", userVO);
        int totalRow = 0;
        if (!CollectionUtils.isEmpty(userVO.getLstRegisterFormDTO())) {
            totalRow = userService.countAllGP(searchProjectForm);
            modelAndView.addObject("totalRowCount", totalRow);
            setPaginationData(modelAndView, searchProjectForm.getPage(), totalRow);
        } else {
            setPaginationData(modelAndView, 0, totalRow);
        }

        modelAndView.addObject("searchProjectForm", searchProjectForm);
        return modelAndView;
    }

    @GetMapping("GPDetail")
    public ModelAndView initGPView(String teamId) {
        ModelAndView modelAndView = new ModelAndView("sites/lecturer/lecturer-gp-detail");

        if (teamId != null) {
            LecturerVO lecturerVO = lecturerService.getRegisterFormDetail(teamId);
            lecturerVO.getRegisterFormDTO().setTeamId(teamId);
            modelAndView.addObject("registerForm", lecturerVO.getRegisterFormDTO());
        }

        return modelAndView;
    }

    /*public LecturerController() {
        if (session.getAttribute("user") == null) {
            UserDTO userDTO = commonService.loginedInfor();
            session.setAttribute("user", userDTO);
        }
    }*/
}
