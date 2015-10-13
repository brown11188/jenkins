package com.enclaveit.model;

// default package
// Generated Sep 30, 2015 4:34:05 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * AccessToken generated by hbm2java
 */
@Entity
@Table(name = "access_token")
public class AccessToken implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "username")
    private Users users;
    
    @Column(name = "token")
    private String token;
    
    @Column(name = "token_type")
    private String tokenType;
    
    @Column(name = "expires_time")
    private Date expiresTime;

    public AccessToken() {
    }

    public AccessToken(int id) {
        this.id = id;
    }

    public AccessToken(int id, Users users, String token, String tokenType,
            Date expiresTime) {
        this.id = id;
        this.users = users;
        this.token = token;
        this.tokenType = tokenType;
        this.expiresTime = expiresTime;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Date getExpiresTime() {
        return this.expiresTime;
    }

    public void setExpiresTime(Date expiresTime) {
        this.expiresTime = expiresTime;
    }

}
