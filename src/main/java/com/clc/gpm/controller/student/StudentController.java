package com.clc.gpm.controller.student;

import com.clc.gpm.common.CommonConstants;
import com.clc.gpm.common.MessageConstants;
import com.clc.gpm.controller.AppController;
import com.clc.gpm.form.RegisterGPForm;
import com.clc.gpm.form.search.SearchProjectForm;
import com.clc.gpm.service.StudentProjectService;
import com.clc.gpm.service.StudentService;
import com.clc.gpm.vo.MessageVO;
import com.clc.gpm.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("student")
public class StudentController extends AppController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentProjectService studentProjectService;

    @GetMapping("")
    public ModelAndView initIndexView() {
        ModelAndView modelAndView = new ModelAndView("sites/student/index");

        return modelAndView;
    }


    @GetMapping("register-gp")
    public ModelAndView initRegisterGPView() {
        ModelAndView modelAndView = new ModelAndView("sites/student/student-register-gp");

        StudentVO studentVO = studentService.getAllFacultyAndLevel();

        modelAndView.addObject("studentVO", studentVO);
        modelAndView.addObject("searchProjectForm", new SearchProjectForm());
        return modelAndView;
    }

    @GetMapping("register-team")
    public ModelAndView initRegisterTeamGP() {
        ModelAndView modelAndView = new ModelAndView();

        return modelAndView;
    }

    @GetMapping("register-gp/register-form")
    public ModelAndView initRegisterFormGP(@RequestParam("projectId") Integer projectId) {
        ModelAndView modelAndView = new ModelAndView("sites/student/student-register-form");

        StudentVO studentVO = studentProjectService.getRegisterFormByProjectId(projectId);

        modelAndView.addObject("registerForm", studentVO.getRegisterFormDTO());
        modelAndView.addObject("registerGpForm", new RegisterGPForm());

        return modelAndView;
    }

    @PostMapping("register-gp/register-form")
    public ModelAndView registerFormGP(RegisterGPForm registerGPForm, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("sites/student/student-register-form-success");

        validate(registerGPForm, bindingResult);
        if(bindingResult.hasErrors()){
            modelAndView.addObject(CommonConstants.MESSAGE, new MessageVO(CommonConstants.MessageType.ERROR.toString(), MessageConstants.MSG_6));
        }

        boolean insertResult = studentService.submitRegisterGPForm(registerGPForm);
        modelAndView = initRegisterFormGP(registerGPForm.getProjectId());
        modelAndView.addObject(CommonConstants.MESSAGE, new MessageVO(CommonConstants.MessageType.INFO.toString(), "S0001"));
        return modelAndView;
    }


}
