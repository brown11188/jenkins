/**
 *
 */
package com.enclaveit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.enclaveit.common.HibernateUtils;
import com.enclaveit.common.Utils;
import com.enclaveit.model.Customers;

/**
 * @author varick
 *
 */
public class CustomerDAOImpl implements CustomerDAO{

    /* (non-Javadoc)
     * @see com.enclaveit.dao.CustomerDAO#listCustomer(org.hibernate.Session)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Customers> listCustomer(Session session) {
        List<Customers> listCustomer = new ArrayList<Customers>();

        if (session != null && session.isOpen()) {
            listCustomer = session.createQuery("from Customers").list();
        } else {
            try {
                session = HibernateUtils.openSession();
                listCustomer = session.createQuery("from Customers").list();
            } catch (HibernateException e) {
                throw e;
            } finally {
                session.close();
            }
        }

        return listCustomer;
    }

    /* (non-Javadoc)
     * @see com.enclaveit.dao.CustomerDAO#addCustomer(com.enclaveit.model.Customers, org.hibernate.Session)
     */
    @Override
    public boolean addCustomer(Customers customer, Session session) {

        if (customer != null && !Utils.isExistsCustomer(customer.getUsername())) {
            session.save(customer);
            session.flush();

            return true;
        }
        return false;
    }

    /* (non-Javadoc)
     * @see com.enclaveit.dao.CustomerDAO#removeCustomer(java.lang.Object, org.hibernate.Session)
     */
    @Override
    public boolean removeCustomer(Object customer, Session session) {

        if (customer != null) {
            session.delete(customer);

            return true;
        }

        return false;
    }

    /* (non-Javadoc)
     * @see com.enclaveit.dao.CustomerDAO#editCustomer(com.enclaveit.model.Customers, org.hibernate.Session)
     */
    @Override
    public boolean editCustomer(Customers customer, Session session) {

        if (customer != null) {
            session.update(customer);

            return true;
        }
        return false;
    }

}
