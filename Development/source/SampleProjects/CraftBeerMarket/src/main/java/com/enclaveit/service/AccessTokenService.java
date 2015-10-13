package com.enclaveit.service;


public interface AccessTokenService {

    public boolean checkAccessToken(String username, String type, String token);
    
}
