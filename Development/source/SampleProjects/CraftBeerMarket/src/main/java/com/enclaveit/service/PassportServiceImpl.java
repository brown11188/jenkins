package com.enclaveit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.enclaveit.common.HibernateUtils;
import com.enclaveit.dao.BeerDAO;
import com.enclaveit.dao.PassportDAO;
import com.enclaveit.dao.UserDAO;
import com.enclaveit.model.Passport;

public class PassportServiceImpl implements PassportService {

    @Autowired
    private PassportDAO passportDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BeerDAO beerDAO;

    @Override
    @Transactional
    public void addPassport(String username, int beer_id) {
        Passport passport = new Passport();
        passport.setUsers(this.getUserDAO().findByUserName(username, HibernateUtils.openSession()));
        passport.setBeer(this.getBeerDAO().getBeerById(beer_id));
        this.getPassportDAO().addPassport(passport);
    }

    public PassportDAO getPassportDAO() {
        return passportDAO;
    }

    public void setPassportDAO(PassportDAO passportDAO) {
        this.passportDAO = passportDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public BeerDAO getBeerDAO() {
        return beerDAO;
    }

    public void setBeerDAO(BeerDAO beerDAO) {
        this.beerDAO = beerDAO;
    }


}
