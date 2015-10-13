package com.enclaveit.brea.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enclaveit.brea.common.Status;
import com.enclaveit.brea.entities.Roles;
import com.enclaveit.brea.entities.Users;
import com.enclaveit.brea.services.UserService;

@RestController
public class JSONController {

    @Autowired
    private UserService services;

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONController.class);

    @RequestMapping(value = "/rest/createuser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Status addUser(@RequestBody Users user) {
        try {
            if (services.createUser(user, user.getUsersInfo())) {
                return new Status(1, "User added Successfully!");
            }
            LOGGER.info("Username " + user.getUsername() + " have exists!");
            return new Status(2, "Username have exists!");
        } catch (HibernateException e) {
            LOGGER.error("Couldn't create user: ", e);
            return new Status(0, e.toString());
        }
    }

    @RequestMapping(value = "/rest/alluser", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Users> listUser() {

        return services.getAllUsers();
    }

    @RequestMapping(value = "/rest/userbyid/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Users getUserById(@PathVariable("id") int id) {

        return services.findByUserId(id);
    }

    @RequestMapping(value = "/rest/deleteuser/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Status deleteUserById(@PathVariable("id") int id) {
        try {
            services.deleteUser(id);
            return new Status(1, "User deleted Successfully!");
        } catch (HibernateException e) {
            LOGGER.error("Couldn't delete user with id " + id + ": ", e);
            return new Status(0, e.toString());
        }
    }

    @RequestMapping(value = "/rest/allrole", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Roles> listRole() {

        return services.getAllRoles();
    }

    @RequestMapping(value = "/rest/userbyname/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    public Users findByUserName(@PathVariable("username") String username) {

        return services.findByUserName(username);

    }

    @RequestMapping(value = "/rest/updatuinfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Status updateUserInfo(@RequestBody Users user) {

        if (services.updateUserInfo(user)) {
            LOGGER.info("Updated successfully user's info of user with user name: " + user.getUsername());

            return new Status(1, "User info updated successfully!");
        }

        return new Status(0, "Couldn't update user!");
    }

    @RequestMapping(value = "/rest/changepass", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Status changePassword(@RequestBody String password) {
        try {
            if (services.changePassword(password)) {
                return new Status(1, "Password changed successfully!");
            }
            LOGGER.info("The current password is incorrect!");
            return new Status(2, "The current password is incorrect!");
        } catch (HibernateException e) {
            LOGGER.error("Couldn't change user's password: ", e);
            return new Status(0, e.toString());
        }
    }
}
