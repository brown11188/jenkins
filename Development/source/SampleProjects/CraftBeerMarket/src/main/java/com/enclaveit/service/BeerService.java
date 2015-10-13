package com.enclaveit.service;

import java.util.List;

import com.enclaveit.model.Beer;

public interface BeerService {

    public List<Beer> listBeer();
    
    public List<Beer> listBeerNotArc();
    
    public List<Beer> listBeerByCatelogy(int catelogy);
    
    public Beer getBeerById(int id);
    
    public void addBeer(Beer beer);

    public void updateBeer(Beer beer);
    
    public void updateBeerArchived(int id, int category_id, Boolean archived);
    
    public void removeBeer(int id);
    
    List<Beer> getBeerLstHaveNotTried(String username);
    
}
