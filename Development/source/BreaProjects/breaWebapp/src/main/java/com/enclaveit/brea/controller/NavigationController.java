package com.enclaveit.brea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NavigationController {

    @RequestMapping("/home")
    public ModelAndView welcome() {
        return new ModelAndView("home");
    }

    @RequestMapping("/report")
    public ModelAndView report() {
        return new ModelAndView("report");
    }
}
