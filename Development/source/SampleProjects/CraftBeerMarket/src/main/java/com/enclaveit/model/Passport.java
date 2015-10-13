package com.enclaveit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// default package
// Generated Sep 29, 2015 1:48:29 PM by Hibernate Tools 3.4.0.CR1

/**
 * Passport generated by hbm2java
 */
@Entity
@Table(name = "passport")
public class Passport implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4509135112494367566L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "username")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "beer_id")
    private Beer beer;

    public Passport() {
    }

    public Passport(int id, Users users) {
        this.id = id;
        this.users = users;
    }

    public Passport(int id, Users users, Beer beer) {
        this.id = id;
        this.users = users;
        this.beer = beer;
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

    public Beer getBeer() {
        return this.beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

}