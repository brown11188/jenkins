package com.enclaveit.brea.dao;

import java.util.List;

import org.hibernate.Session;

import com.enclaveit.brea.entities.Roles;
import com.enclaveit.brea.entities.Users;
import com.enclaveit.brea.entities.UsersInfo;

public interface UserDao {

    public boolean createUser(Users user, UsersInfo userInfo, Session session);
    public Users findByUserName(String username, Session session);
    public Users findByUserId(int id, Session session);
    public List<Users> getAllUser(Session session);
    public List<Roles> getAllRole(Session session);
    public boolean deleteUser(Object user, Session session);
    public boolean updateUserInfo(Users user, Session session);
    public boolean changePassword(String password, Session session);
}
