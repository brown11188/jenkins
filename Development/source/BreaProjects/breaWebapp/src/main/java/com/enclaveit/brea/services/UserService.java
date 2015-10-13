package com.enclaveit.brea.services;

import java.util.List;

import com.enclaveit.brea.entities.Roles;
import com.enclaveit.brea.entities.Users;
import com.enclaveit.brea.entities.UsersInfo;

public interface UserService {

    public boolean createUser(Users user, UsersInfo userInfo);
    public Users findByUserName(String username);
    public Users findByUserId(int id);
    public List<Users> getAllUsers();
    public List<Roles> getAllRoles();
    public boolean deleteUser(int id);
    public boolean updateUserInfo(Users user);
    public boolean changePassword(String password);

}
