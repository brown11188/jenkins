package com.enclaveit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRoles implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3055322925659709550L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private int userRoleId;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private Users users;

    @Column(name = "role")
    private String role;

    public UserRoles() {
    }

    public UserRoles(int userRoleId, Users users) {
        this.userRoleId = userRoleId;
        this.users = users;
    }

    public UserRoles(int userRoleId, Users users, String role) {
        this.userRoleId = userRoleId;
        this.users = users;
        this.role = role;
    }

    public int getUserRoleId() {
        return this.userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
