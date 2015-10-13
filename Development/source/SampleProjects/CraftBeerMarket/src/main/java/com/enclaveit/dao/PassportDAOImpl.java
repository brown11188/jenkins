package com.enclaveit.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.enclaveit.model.Passport;

public class PassportDAOImpl implements PassportDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(PassportDAOImpl.class);

    @Override
    public void addPassport(Passport passport) {
        Session session = this.getSessionFactory().getCurrentSession();
        session.save(passport);
        session.flush();
        logger.info("Passport saved successfully, Passport details=" + passport);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
