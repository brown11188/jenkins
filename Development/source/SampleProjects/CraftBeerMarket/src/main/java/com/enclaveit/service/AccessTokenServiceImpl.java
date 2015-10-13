package com.enclaveit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.enclaveit.dao.AccessTokenDAO;

public class AccessTokenServiceImpl implements AccessTokenService{
    
    @Autowired
    private AccessTokenDAO accessTokenDAO;

    @Override
    @Transactional
    public boolean checkAccessToken(String username, String type, String token) {
        return !this.getAccessTokenDAO().getByUserAndType(username, type, token).isEmpty();
    }

    public AccessTokenDAO getAccessTokenDAO() {
        return accessTokenDAO;
    }

    public void setAccessTokenDAO(AccessTokenDAO accessTokenDAO) {
        this.accessTokenDAO = accessTokenDAO;
    }

}
