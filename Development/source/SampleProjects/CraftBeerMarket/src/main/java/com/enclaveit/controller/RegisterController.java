/**
 *
 */
package com.enclaveit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.enclaveit.dto.model.CustomerRegisterDTO;
import com.enclaveit.service.UserService;

/**
 * @author varick
 *
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService service;

    @RequestMapping(value="/customer/registration", method = RequestMethod.GET)
    public ModelAndView showRegistrationForm() {
        ModelAndView model = new ModelAndView("register", "command", new CustomerRegisterDTO());

        return model;
    }

    @RequestMapping(value="/customer/registration/action", method = RequestMethod.POST)
    public String customerRegister(
            @ModelAttribute("customer") CustomerRegisterDTO customer, ModelMap model) {
        if (service.customerRegister(customer)) {
            model.addAttribute("desc", "Created succesfully user ");
            model.addAttribute("username", customer.getUsername());
        } else {
            model.addAttribute("desc", "Couldn't creat user ");
            model.addAttribute("username", customer.getUsername());
        }

        return "redirect:/login";
    }
}
