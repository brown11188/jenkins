/**
 *
 */
package com.enclaveit.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.enclaveit.common.HibernateUtils;
import com.enclaveit.dao.UserDAO;
import com.enclaveit.model.UserRoles;
import com.enclaveit.model.Users;

/**
 * @author varick
 *
 */
public class UserLoginService implements UserDetailsService{

    @Autowired
    private UserDAO userDao;

    @Autowired
    private SessionFactory sessionFactory;

    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
     */
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Session session = HibernateUtils.openSession();
        User userDetails = null;
        try {
            Users user = userDao.findByUserName(username, session);
            List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoleses());
            userDetails = buildUserForAuthentication(user, authorities);
        } catch (HibernateException e) {
            throw e;
        }

        return userDetails;
    }

    // Convert from user entity to spring security userdetails
    private User buildUserForAuthentication(Users user,
        List<GrantedAuthority> authorities) {

        return new User(user.getUsername(),
                user.getPassword(),
                user.getEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRoles> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (UserRoles userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<GrantedAuthority>(setAuths);
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}