package com.enclaveit.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.enclaveit.model.Customers;
import com.enclaveit.model.UserInfo;
import com.enclaveit.model.Users;


public final class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    private Utils() {
    }

    /**
     * Encrypt password.
     * @param password
     * @return encrypt password (BCrypt)
     * @author Jack
     */
    public static String encryptPass(String password) {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        return bCrypt.encode(password);
    }

    /**
     * Check if user with username is exists
     * @param username
     * @param session
     * @return true if user is exists or false if not.
     */
    @SuppressWarnings("unchecked")
    public static boolean isExists(String username) {
        List<Users> listUser = new ArrayList<Users>();
        Session session = HibernateUtils.openSession();
        try {
            listUser = session.createQuery("from Users where username=?")
                    .setParameter(0, username).list();
        } catch (HibernateException e) {
            LOGGER.error("There is no user with username " + username);
            throw e;
        } finally {
            session.close();
        }

        if (listUser.isEmpty()) {
            return false;
        }

        return true;
    }

    /**
     * Verify the given user's password with the password stored in database
     * @param username
     * @param password
     * @param session
     * @return true if the password is matches
     */
    @SuppressWarnings("unchecked")
    public static boolean verifyPassword(String username, String password, Session session) {
        List<Users> listUser = new ArrayList<Users>();
        listUser = session.createQuery("from Users where username=?")
                .setParameter(0, username).list();

        if (listUser.isEmpty()) {
            return false;
        } else {
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            return bCrypt.matches(password, listUser.get(0).getPassword());
        }
    }

    /**
     * Verify the current logged in user is admin or not
     * @return true if current user have admin role
     */
    @SuppressWarnings("unchecked")
    public static boolean isAdmin() {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {

                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public static boolean isExistsCustomer(String customerName) {
        List<Customers> listCustomer = new ArrayList<Customers>();
        Session session = HibernateUtils.openSession();
        try {
            listCustomer = session.createQuery("from Customers where username=?")
                    .setParameter(0, customerName)
                    .list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

        if (listCustomer.isEmpty()) {

            return false;
        }

        return true;
    }

    public static boolean isValidPassword(String password, String matchPassword) {

        return password.trim().equals(matchPassword.trim()) && password.trim().length() >= 6;
    }

    @SuppressWarnings("unchecked")
    public static boolean isExistsEmail(String email) {
        List<UserInfo> list = new ArrayList<UserInfo>();
        Session session = HibernateUtils.openSession();
        try {
            list = session.createQuery("from UserInfo where email=?")
                    .setParameter(0, email).list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            session.close();
        }

        if (list.isEmpty()) {

            return false;
        }

        return true;
    }
}
