package com.enclaveit.brea.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static final String LOGOUT_SUCCESFUL_MESSAGE = "You've been logged out successfully.";
    private static final String INVALID_USER_ACCOUNT = "Invalid username and password!";

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView showLoginPage(@RequestParam(value="error", required=false) String error,
            @RequestParam(value="logout", required=false) String logout,
            HttpServletRequest request) {

        ModelAndView view = new ModelAndView();

        if (error != null) {
            view.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {
            view.addObject("msg", getErrorMessage(request, LOGOUT_SUCCESFUL_MESSAGE));
        }
        view.setViewName("login");
        return view;
    }

    // for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }
        model.setViewName("403");
        return model;

    }

    // customize the error message
    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = INVALID_USER_ACCOUNT;
        if (exception instanceof BadCredentialsException || exception instanceof AuthenticationServiceException) {
            error = INVALID_USER_ACCOUNT;
        } else if (key.equals(LOGOUT_SUCCESFUL_MESSAGE)) {
            error = LOGOUT_SUCCESFUL_MESSAGE;
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        }

        return error;
    }
}
