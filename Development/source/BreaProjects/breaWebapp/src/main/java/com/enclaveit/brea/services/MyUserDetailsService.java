package com.enclaveit.brea.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.enclaveit.brea.dao.UserDao;
import com.enclaveit.brea.entities.Roles;
import com.enclaveit.brea.entities.Users;

public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Session session = getSessionFactory().openSession();
        User userDetails = null;
        try {
            Users user = userDao.findByUserName(username, session);
            List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
            userDetails = buildUserForAuthentication(user, authorities);
        } catch (HibernateException e) {
            LOGGER.error("Hibernate Exception: ", e);
        } finally {
            session.close();
        }

        return userDetails;
    }

    // Convert from user entity to spring security userdetails
    private User buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {

        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Roles userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        setAuths.add(new SimpleGrantedAuthority(userRoles.getRole()));
        return new ArrayList<GrantedAuthority>(setAuths);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}