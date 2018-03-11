package com.clc.gpm.controller.adminstrator;

import com.clc.gpm.controller.AppController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Adminstrator controller.
 */
@Controller
@RequestMapping("admin")
public class AdminstratorController extends AppController{

    public ModelAndView initIndexView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sites/adminstrator/index");

        return modelAndView;
    }



}
