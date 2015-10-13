package com.enclaveit.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.enclaveit.model.AccessToken;

public class AccessTokenDAOImpl implements AccessTokenDAO{

    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    @Override
    public List<AccessToken> getByUserAndType(String username, String type, String token) {
        return (List<AccessToken>) getSessionFactory()
                .getCurrentSession()
                .createQuery("from AccessToken where users.username=? and tokenType =? and token=?")
                .setParameter(0, username)
                .setParameter(1, type)
                .setParameter(2, token).list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
