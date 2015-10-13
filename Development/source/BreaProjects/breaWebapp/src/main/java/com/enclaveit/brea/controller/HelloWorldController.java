package com.enclaveit.brea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

    @RequestMapping("/hellobootstrap")
    public ModelAndView sampleBootstrap() {
        /**
         * this comment just for testing Jenkin
         *
         * @author Huck
         */
        return new ModelAndView("hellobootstrap");
    }

    @RequestMapping("/hellotheme")
    public ModelAndView sampleTheme() {
        return new ModelAndView("hellotheme");
    }
}
