/**
 *
 */
package com.enclaveit.webService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enclaveit.model.Customers;
import com.enclaveit.service.CustomerService;

/**
 * @author varick
 *
 */
@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/rest/alluser",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public List<Customers> listUser() {

        return customerService.listCustomer();
    }
}
