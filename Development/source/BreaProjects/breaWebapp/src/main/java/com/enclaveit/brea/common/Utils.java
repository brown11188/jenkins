package com.enclaveit.brea.common;

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

import com.enclaveit.brea.dao.UserDaoImpl;
import com.enclaveit.brea.entities.Users;

public final class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private Utils() {
    }

    /**
     * Encrypt password.
     *
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
     *
     * @param username
     * @param session
     * @return true if user is exists or false if not.
     */
    @SuppressWarnings("unchecked")
    public static boolean isExists(String username) {
        List<Users> listUser = new ArrayList<Users>();
        Session session = HibernateUtils.openSession();
        try {
            listUser = session.createQuery("from Users where username=?").setParameter(0, username).list();
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
     *
     * @param username
     * @param password
     * @param session
     * @return true if the password is matches
     */
    @SuppressWarnings("unchecked")
    public static boolean verifyPassword(String username, String password, Session session) {
        List<Users> listUser = new ArrayList<Users>();
        listUser = session.createQuery("from Users where username=?").setParameter(0, username).list();

        if (listUser.isEmpty()) {
            return false;
        } else {
            BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
            return bCrypt.matches(password, listUser.get(0).getPassword());
        }
    }

    /**
     * Verify the current logged in user is admin or not
     *
     * @return true if current user have admin role
     */
    @SuppressWarnings("unchecked")
    public static boolean isAdmin() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if ("ROLE_ADMIN".equals(authority.getAuthority())) {

                return true;
            }
        }

        return false;
    }
}
