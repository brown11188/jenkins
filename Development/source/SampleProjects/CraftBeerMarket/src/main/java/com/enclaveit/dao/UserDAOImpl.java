/**
 *
 */
package com.enclaveit.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.enclaveit.common.HibernateUtils;
import com.enclaveit.common.Utils;
import com.enclaveit.model.UserRoles;
import com.enclaveit.model.Users;

/**
 * @author varick
 *
 */
public class UserDAOImpl implements UserDAO{

    /* (non-Javadoc)
     * @see com.enclaveit.dao.UserDAO#findByUserName(java.lang.String, org.hibernate.Session)
     */
    @Override
    public Users findByUserName(String username, Session session) {
        Users user = new Users();

        if (session != null && session.isOpen()) {
            user = (Users) session.createQuery("from Users where username=?")
                    .setParameter(0, username).uniqueResult();
        }

        return user;
    }

    /* (non-Javadoc)
     * @see com.enclaveit.dao.UserDAO#addUser(com.enclaveit.model.Users, org.hibernate.Session)
     */
    @Override
    public boolean addUser(Users user, Session session) {

        if (user != null) {
            session.save(user);
            session.flush();

            return true;
        }

        return false;
    }

    /* (non-Javadoc)
     * @see com.enclaveit.dao.UserDAO#addUserRole(com.enclaveit.model.UserRoles, org.hibernate.Session)
     */
    @Override
    public boolean addUserRole(Set<UserRoles> userRole, Session session) {

        if (userRole.size() > 0 && HibernateUtils.isValidSession(session)) {
            for (UserRoles userRoles : userRole) {
                session.save(userRoles);
                session.flush();
            }

            return true;
        }

        return false;
    }

    /* (non-Javadoc)
     * @see com.enclaveit.dao.UserDAO#listUser(org.hibernate.Session)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Users> listUser(Session session) {
        List<Users> listUser = new ArrayList<Users>();

        if (HibernateUtils.isValidSession(session)) {
            listUser = session.createQuery("from Users").list();
        } else {
            try {
                session = HibernateUtils.openSession();
                listUser = session.createQuery("from Users").list();
            } catch (HibernateException e) {
                throw e;
            } finally {
                session.close();
            }
        }

        return listUser;
    }

    /* (non-Javadoc)
     * @see com.enclaveit.dao.UserDAO#customerRegister(com.enclaveit.model.Users, org.hibernate.Session)
     */
    @Override
    public boolean customerRegister(Users user, Session session) {

        if (user != null && !Utils.isExists(user.getUsername())
                && !Utils.isExistsEmail(user.getUserInfo().getEmail())) {
            session.save(user);
            session.flush();

            return true;
        }

        return false;
    }
}
