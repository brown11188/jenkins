/**
 *
 */
package com.enclaveit.brea.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author varick
 *
 */
public final class HibernateUtils {

    @Autowired
    private static SessionFactory sessionFactory;

    public HibernateUtils() {
        super();
    }

    public void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }

    public static Session openSession() {
        return getSessionFactory().openSession();
    }

    public static Session getSession() {
        return getSessionFactory().getCurrentSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtils.sessionFactory = sessionFactory;
    }
}
