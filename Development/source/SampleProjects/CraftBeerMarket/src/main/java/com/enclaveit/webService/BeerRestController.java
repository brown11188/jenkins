package com.enclaveit.webService;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;//BeerCatelogy

import com.enclaveit.controller.BaseCPController;
import com.enclaveit.model.Beer;
import com.enclaveit.service.BeerService;

@RestController
public class BeerRestController extends BaseCPController{

    @Autowired
    private BeerService beerService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/api/v1/beers/listByCatelogy/{catelogy}", method = RequestMethod.GET)
    public Map<String, Object> getBeerByCatelogy(@PathVariable int catelogy) {

        List<Beer> beerLst = beerService.listBeerByCatelogy(catelogy);

        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(beerLst);

        return resultMap(true, jsonArray);
    }


    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/api/v1/beers/list", method = RequestMethod.GET)
    public Map<String, Object> getAllBeerLst() {

        List<Beer> beerLst = beerService.listBeer();

        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(beerLst);

        return resultMap(true, jsonArray);
    }
    
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/api/v1/beers/listHaveNotTried/{username}", method = RequestMethod.GET)
    public Map<String, Object> getBeerLstHaveNotTried(@PathVariable String username) {
        List<Beer> beerLst = beerService.getBeerLstHaveNotTried(username);

        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(beerLst);

        return resultMap(true, jsonArray);
    }

}
