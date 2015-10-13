/**
 *
 */
package com.enclaveit.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.enclaveit.common.Utils;
import com.enclaveit.dto.model.AdminUserRegisterDTO;
import com.enclaveit.model.Customers;
import com.enclaveit.service.CustomerService;
import com.enclaveit.service.UserService;

/**
 * @author varick
 *
 */
@Controller
public class LoginController {

    @Autowired
    private UserService service;

    @Autowired
    private CustomerService customerService;

    private static final String LOGOUT_SUCCESFUL_MESSAGE = "You've been logged out successfully.";
    private static final String INVALID_USER_ACCOUNT = "Invalid username and password!";

    @RequestMapping(value={ "/", "/login" }, method=RequestMethod.GET)
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
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView("admin", "command", new AdminUserRegisterDTO());
        model.addObject("title", "Spring Security + Hibernate Example");
        model.addObject("message", "This page is for ROLE_ADMIN only!");

        return model;

    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") AdminUserRegisterDTO user, ModelMap model) {

        if (Utils.isValidPassword(user.getPassword(), user.getMatchPassword())
                && !Utils.isExists(user.getUsername())) {
            service.addUser(user);
            model.addAttribute("desc", "Created succesfully user ");
            model.addAttribute("username", user.getUsername());
        } else {
            model.addAttribute("desc", "Couldn't creat user ");
            model.addAttribute("username", user.getUsername());
        }

        return "result";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public ModelAndView customer() {
        ModelAndView model = new ModelAndView("customer", "command", new Customers());

        return model;
    }

    @RequestMapping(value = "/customer/add", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") Customers customer, ModelMap model) {

        if (customer.toString().length() > 0) {
            customerService.addCustomer(customer);
            model.addAttribute("desc", "Created succesfully customer ");
            model.addAttribute("username", customer.getUsername());
        } else {
            model.addAttribute("desc", "Couldn't creat customer ");
            model.addAttribute("username", customer.getUsername());
        }

        return "result";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Craft beer welcome page");
        model.setViewName("welcome");
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