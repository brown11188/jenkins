/**
 *
 */
package com.enclaveit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.enclaveit.common.HibernateUtils;
import com.enclaveit.dao.CustomerDAO;
import com.enclaveit.model.Customers;

/**
 * @author varick
 *
 */
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDAO customerDao;

    public CustomerDAO getCustomerDao() {
        return customerDao;
    }

    public void setCustomerDao(CustomerDAO customerDao) {
        this.customerDao = customerDao;
    }

    /*
     * (non-Javadoc)
     * @see com.enclaveit.service.CustomerService#listCustomer()
     */
    @Override
    public List<Customers> listCustomer() {

        return customerDao.listCustomer(HibernateUtils.getSession());
    }

    /*
     * (non-Javadoc)
     * @see com.enclaveit.service.CustomerService#addCustomer(com.enclaveit.model.Customers)
     */
    @Override
    public boolean addCustomer(Customers customer) {

        return customerDao.addCustomer(customer, HibernateUtils.getSession());
    }

    /*
     * (non-Javadoc)
     * @see com.enclaveit.service.CustomerService#removeCustomer(java.lang.Object)
     */
    @Override
    public boolean removeCustomer(Object customer) {
        // TODO Auto-generated method stub
        return false;
    }

    /*
     * (non-Javadoc)
     * @see com.enclaveit.service.CustomerService#editCustomer(com.enclaveit.model.Customers)
     */
    @Override
    public boolean editCustomer(Customers customer) {
        // TODO Auto-generated method stub
        return false;
    }

}
