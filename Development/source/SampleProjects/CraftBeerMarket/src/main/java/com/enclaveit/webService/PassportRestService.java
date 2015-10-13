package com.enclaveit.webService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.enclaveit.controller.BaseCPController;
import com.enclaveit.service.AccessTokenService;
import com.enclaveit.service.PassportService;

@RestController
public class PassportRestService extends BaseCPController{

    @Autowired
    PassportService passportService;
    
    @Autowired
    AccessTokenService accessTokenService;
    
    @RequestMapping(value = "/api/v1/passports/add/{username}/{beer_id}/{token}", method = RequestMethod.GET)
    public Map<String, Object> addPassport(@PathVariable String username, @PathVariable int beer_id, @PathVariable String token) throws Exception {
        if(accessTokenService.checkAccessToken(username, "APP", token)) {
            passportService.addPassport(username, beer_id);
        } else {
            throw new Exception("Invalid AccessToken");
        }
        
        
        return resultMap(true, "");
    }
}
