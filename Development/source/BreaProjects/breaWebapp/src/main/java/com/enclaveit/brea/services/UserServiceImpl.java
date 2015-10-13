package com.enclaveit.brea.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.enclaveit.brea.common.HibernateUtils;
import com.enclaveit.brea.dao.UserDao;
import com.enclaveit.brea.entities.Roles;
import com.enclaveit.brea.entities.Users;
import com.enclaveit.brea.entities.UsersInfo;

public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public boolean createUser(Users user, UsersInfo userInfo) {
        Session session = HibernateUtils.getSession();
        boolean result = false;
        try {
            result = userDao.createUser(user, userInfo, session);
        } catch (HibernateException e) {
            throw e;
        }

        return result;
    }

    @Override
    @Transactional
    public Users findByUserName(String username) {
        Session session = HibernateUtils.getSession();
        Users user = new Users();
        try {
            user = userDao.findByUserName(username, session);
        } catch (HibernateException e) {
            throw e;
        }

        return user;
    }

    @Override
    @Transactional
    public Users findByUserId(int id) {
        Session session = HibernateUtils.getSession();
        Users users = new Users();
        try {
            users = userDao.findByUserId(id, session);
        } catch (HibernateException e) {
            throw e;
        }

        return users;
    }

    @Override
    @Transactional
    public List<Users> getAllUsers() {
        Session session = HibernateUtils.getSession();
        List<Users> listUser = new ArrayList<Users>();
        try {
            listUser = userDao.getAllUser(session);
        } catch (HibernateException e) {
            throw e;
        }

        return listUser;
    }

    @Override
    @Transactional
    public List<Roles> getAllRoles() {
        Session session = HibernateUtils.getSession();
        List<Roles> listRole = new ArrayList<Roles>();
        try {
            listRole = userDao.getAllRole(session);
        } catch (HibernateException e) {
            throw e;
        }

        return listRole;
    }

    @Override
    @Transactional
    public boolean deleteUser(int id) {
        Session session = HibernateUtils.getSession();
        boolean result = false;
        try {
            Object user = session.load(Users.class, id);

            LOGGER.info("Deleting user with user id: " + id);
            result = userDao.deleteUser(user, session);
        } catch (HibernateException e) {
            throw e;
        }

        return result;
    }

    @Override
    @Transactional
    public boolean updateUserInfo(Users user) {
        Session session = HibernateUtils.getSession();
        boolean result = false;
        try {
            result = userDao.updateUserInfo(user, session);
        } catch (HibernateException e) {
            throw e;
        }

        return result;
    }

    @Override
    @Transactional
    public boolean changePassword(String password) {
        Session session = HibernateUtils.getSession();
        boolean result = false;
        try {
            result = userDao.changePassword(password, session);
        } catch (HibernateException e) {
            throw e;
        }

        return result;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
