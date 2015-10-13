/**
 *
 */
package com.enclaveit.service;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.enclaveit.common.HibernateUtils;
import com.enclaveit.common.Utils;
import com.enclaveit.dao.UserDAO;
import com.enclaveit.dto.model.AdminUserRegisterDTO;
import com.enclaveit.dto.model.CustomerRegisterDTO;
import com.enclaveit.model.UserInfo;
import com.enclaveit.model.UserRoles;
import com.enclaveit.model.Users;

/**
 * @author varick
 *
 */
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDao;

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    /* (non-Javadoc)
     * @see com.enclaveit.service.UserService#addUser(com.enclaveit.model.Users)
     */
    @Override
    public boolean addUser(AdminUserRegisterDTO user) {
        Session session = HibernateUtils.openSession();
        Users users = new Users();
        Set<UserRoles> role = new HashSet<UserRoles>();
        UserRoles userRole = new UserRoles();
        userRole.setRole("ROLE_ADMIN");
        userRole.setUsers(users);
        role.add(userRole);
        users.setUsername(user.getUsername());
        users.setPassword(Utils.encryptPass(user.getPassword()));
        users.setEnabled(true);

        if (userDao.addUser(users, session)) {

            return userDao.addUserRole(role, session);
        }

        return false;
    }

    /* (non-Javadoc)
     * @see com.enclaveit.service.UserService#customerRegister(com.enclaveit.dto.model.CustomerRegisterDTO)
     */
    @Override
    public boolean customerRegister(CustomerRegisterDTO customer) {
        Session session = HibernateUtils.getSession();
        Users users = new Users();
        UserInfo info = new UserInfo();
        info.setUsername(customer.getUsername());
        info.setFirstName(customer.getFirstname());
        info.setLastName(customer.getLastname());
        info.setEmail(customer.getEmail());
        info.setAddress(customer.getAddress());
        Set<UserRoles> role = new HashSet<UserRoles>();
        UserRoles userRole = new UserRoles();
        userRole.setRole("ROLE_USER");
        userRole.setUsers(users);
        role.add(userRole);
        users.setUsername(customer.getUsername());
        users.setUserInfo(info);
        users.setUserRoleses(role);
        users.setPassword(Utils.encryptPass(customer.getPassword()));
        users.setEnabled(true);

        return userDao.customerRegister(users, session);
    }

}
