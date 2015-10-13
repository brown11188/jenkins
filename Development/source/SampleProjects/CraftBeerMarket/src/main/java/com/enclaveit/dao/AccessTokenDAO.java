package com.enclaveit.dao;

import java.util.List;

import com.enclaveit.model.AccessToken;

public interface AccessTokenDAO {

    public List<AccessToken> getByUserAndType(String username, String type, String token);
}
