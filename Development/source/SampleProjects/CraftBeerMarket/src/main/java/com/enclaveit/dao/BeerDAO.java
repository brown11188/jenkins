package com.enclaveit.dao;

import java.util.List;

import com.enclaveit.model.Beer;

public interface BeerDAO {

    List<Beer> getBeerByCatelogy(int catelogy);

    List<Beer> getAllBeerLst();
    
    List<Beer> getAllBeerLstNotArc();
    
    public Beer getBeerById(int id);
    
    public void addBeer(Beer beer);

    public void updateBeer(Beer beer);
    
    public void updateBeerArchived(int id, int category_id, Boolean archived);

    public void removeBeer(int id);
    
    List<Beer> getBeerLstHaveNotTried(String username);
}