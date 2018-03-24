package com.clc.gpm.controller.student;

import com.clc.gpm.service.StudentService;
import com.clc.gpm.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

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
        return modelAndView;
    }

}
