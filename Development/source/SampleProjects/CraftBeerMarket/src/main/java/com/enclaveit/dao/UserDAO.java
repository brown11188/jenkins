/**
 *
 */
package com.enclaveit.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import com.enclaveit.model.UserRoles;
import com.enclaveit.model.Users;

/**
 * @author varick
 *
 */
public interface UserDAO {

    public Users findByUserName(String username, Session session);
    public List<Users> listUser(Session session);
    public boolean addUser(Users user, Session session);
    public boolean addUserRole(Set<UserRoles> userRole, Session session);
    public boolean customerRegister(Users user, Session session);
}
