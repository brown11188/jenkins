package com.enclaveit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.enclaveit.dao.BeerDAO;
import com.enclaveit.dao.UserDAO;
import com.enclaveit.model.Beer;

public class BeerServiceImpl implements BeerService {

    @Autowired
    private BeerDAO beerDao;
    
    @Autowired
    private UserDAO userDAO;
    
    @Override
    @Transactional
    public List<Beer> listBeer() {
        return getBeerDao().getAllBeerLst();
    }
    
    @Override
    @Transactional
    public List<Beer> listBeerByCatelogy(int catelogy) {
        return getBeerDao().getBeerByCatelogy(catelogy);
    }
    
    @Override
    @Transactional
    public List<Beer> listBeerNotArc() {
        return getBeerDao().getAllBeerLstNotArc();
    }
    
    @Override
    @Transactional
    public void addBeer(Beer beer) {
        this.beerDao.addBeer(beer);
    }

    @Override
    @Transactional
    public void updateBeer(Beer beer) {
       this.beerDao.updateBeer(beer);
    }
    
    @Override
    @Transactional
    public void updateBeerArchived(int id, int category_id, Boolean archived) {
     this.beerDao.updateBeerArchived(id, category_id, archived);   
    }
    
    @Override
    @Transactional
    public void removeBeer(int id) {
        this.beerDao.removeBeer(id);
    }
    @Override
    @Transactional
    public Beer getBeerById(int id) {
        return this.beerDao.getBeerById(id);
    }
    /**
     * @return the beerDao
     */
    public BeerDAO getBeerDao() {
        return beerDao;
    }

    /**
     * @param beerDao the beerDao to set
     */
    public void setBeerDao(BeerDAO beerDao) {
        this.beerDao = beerDao;
    }

    @Override
    public List<Beer> getBeerLstHaveNotTried(String username) {
        
        return this.beerDao.getBeerLstHaveNotTried(username);
    }
}
