package com.enclaveit.brea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONController.class);
    
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        if (isRememberMeAuthenticated()) {
            LOGGER.info("This is login by REMEMBER ME");
        } else {
            LOGGER.info("This is login by Username/password");
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("admin");
        return model;
    }
        /**
         * Check if user is login by remember me cookie, refer
         * org.springframework.security.authentication.AuthenticationTrustResolverImpl
         */
        private boolean isRememberMeAuthenticated() {

            Authentication authentication = 
                        SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return false;
            }

            return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
        }
}
