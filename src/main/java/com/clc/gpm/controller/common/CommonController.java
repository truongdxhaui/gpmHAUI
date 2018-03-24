package com.clc.gpm.controller.common;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Common controller.
 */
@Controller
@RequestMapping("")
public class CommonController {

    /**
     * Login string.
     *
     * @return the string
     */
    @GetMapping("/login")
    public String login() {
        return "sites/common/login";
    }

    /**
     * Logout string.
     *
     * @param request  the request
     * @param response the response
     * @return the string
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/")
    public ModelAndView initIndexView(){
        ModelAndView modelAndView = new ModelAndView("sites/user/index");

        return modelAndView;
    }

    /**
     * Access denied page string.
     *
     * @return the string
     */
    public String accessDeniedPage(){
        return "";
    }

}
