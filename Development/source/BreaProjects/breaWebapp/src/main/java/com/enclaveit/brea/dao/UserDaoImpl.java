package com.enclaveit.brea.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.enclaveit.brea.common.Utils;
import com.enclaveit.brea.entities.Roles;
import com.enclaveit.brea.entities.Users;
import com.enclaveit.brea.entities.UsersInfo;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public boolean createUser(Users user, UsersInfo usersInfo, Session session) {

        if (!Utils.isExists(user.getUsername())) {
            user.setPassword(Utils.encryptPass(user.getPassword()));
            usersInfo.setUsers(user);
            session.save(user);

            return true;
        }

        return false;
    }

    @Override
    public Users findByUserName(String username, Session session) {
        Users user = new Users();

        if (session != null && session.isOpen()) {
            user = (Users) session.createQuery("from Users where username=?").setParameter(0, username).uniqueResult();
        }

        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Users> getAllUser(Session session) {
        List<Users> listUser = new ArrayList<Users>();

        if (session != null && session.isOpen()) {
            listUser = session.createQuery("from Users").list();
        }

        return listUser;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Roles> getAllRole(Session session) {
        List<Roles> listRoles = new ArrayList<Roles>();

        if (session != null && session.isOpen()) {
            listRoles = session.createQuery("from Roles").list();
        }

        return listRoles;
    }

    @Override
    public Users findByUserId(int id, Session session) {
        Users user = new Users();

        if (session != null && session.isOpen()) {
            user = (Users) session.get(Users.class, id);
        }

        return user;
    }

    @Override
    public boolean deleteUser(Object user, Session session) {

        if (user != null) {
            session.delete(user);

            return true;
        }

        return false;
    }

    @Override
    public boolean updateUserInfo(Users user, Session session) {
        boolean result = false;
        if (Utils.isAdmin()) {

            if (!Utils.isExists(user.getUsername())) {
                LOGGER.error("Couldn't update user's info of user with username " + user.getUsername());
            } else {
                session.update(user);
                result = true;
            }
        } else {

            if (!Utils.isExists(user.getUsername()) || !isUserLoggedIn(user.getUsername())) {
                LOGGER.error("Couldn't update user's info of user with username " + user.getUsername());
            } else {
                session.update(user.getUsersInfo());
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean changePassword(String password, Session session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        JSONObject o = new JSONObject(password);
        String currentPass = o.getString("currentPass");
        String newPass = o.getString("newPass");
        String hQLquey = new StringBuilder("update Users set password=").append(":newpass")
                .append(" where username=:username").toString();
        if (authentication.isAuthenticated() && Utils.verifyPassword(username, currentPass, session)) {
            int status = session.createQuery(hQLquey).setString("newpass", Utils.encryptPass(newPass))
                    .setString("username", username).executeUpdate();

            if (status == 1) {

                return true;
            }
        }

        return false;
    }

    public boolean isUserLoggedIn(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.isAuthenticated() && authentication.getName().equals(username);
    }

}
