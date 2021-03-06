package com.enclaveit.brea.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;







// default package
// Generated Sep 9, 2015 1:45:50 PM by Hibernate Tools 3.4.0.CR1

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5997813541760459609L;
    private Integer id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Roles roles;
    private String username;
    private String password;
    private boolean enabled;
    private UsersInfo usersInfo;

    public Users() {
    }

    public Users(Roles roles, boolean enabled, UsersInfo usersInfo) {
        this.roles = roles;
        this.enabled = enabled;
        this.usersInfo = usersInfo;
    }

    public Users(Roles roles, String username, String password, boolean enabled) {
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public Users(Roles roles, String username, String password, boolean enabled,
            UsersInfo usersInfo) {
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.usersInfo = usersInfo;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Roles getRoles() {
        return this.roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UsersInfo getUsersInfo() {
        return this.usersInfo;
    }

    public void setUsersInfo(UsersInfo usersInfo) {
        this.usersInfo = usersInfo;
    }

}
