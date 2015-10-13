/**
 *
 */
package com.enclaveit.dao;

import java.util.List;

import org.hibernate.Session;

import com.enclaveit.model.Customers;

/**
 * @author varick
 *
 */
public interface CustomerDAO {

    public List<Customers> listCustomer(Session session);
    public boolean addCustomer(Customers customer, Session session);
    public boolean removeCustomer(Object customer, Session session);
    public boolean editCustomer(Customers customer, Session session);
}
