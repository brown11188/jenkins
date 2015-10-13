/**
 *
 */
package com.enclaveit.service;

import java.util.List;

import com.enclaveit.model.Customers;

/**
 * @author varick
 *
 */
public interface CustomerService {

    public List<Customers> listCustomer();
    public boolean addCustomer(Customers customer);
    public boolean removeCustomer(Object customer);
    public boolean editCustomer(Customers customer);
}
