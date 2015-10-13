package com.enclaveit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.enclaveit.model.Beer;

public class BeerDAOImpl implements BeerDAO {
    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Beer> getBeerByCatelogy(int catelogy) {
        return (List<Beer>) getSessionFactory()
                .getCurrentSession()
                .createQuery("from Beer where category_id=? and archived = false")
                .setParameter(0, catelogy).list();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Beer> getAllBeerLst() {
        return (List<Beer>) getSessionFactory()
                .getCurrentSession()
                .createQuery("from Beer where archived = false").list();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Beer> getAllBeerLstNotArc() {
        return (List<Beer>) getSessionFactory()
                .getCurrentSession()
                .createQuery("from Beer order by id asc").list();
    }
    
    @Override
    public Beer getBeerById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Beer beer = (Beer) session.get(Beer.class, new Integer(id));
        logger.info("Beer loaded successfully, Beer details="+beer);
        return beer;
    }

    @Override
    public void addBeer(Beer beer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(beer);
        logger.info("Beer saved successfully, Beer Details="+beer);
    }

    @Override
    public void updateBeer(Beer beer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(beer);
        logger.info("Beer updated successfully, Beer Details="+beer);
    }
    
    @Override
    public void updateBeerArchived(int id, int category_id, Boolean archived){
        Session session = this.sessionFactory.getCurrentSession();
        Query query=  session.createQuery("update Beer set archived = :archived where id= :id and category_id = :category_id");
        query.setParameter("id", id);
        query.setParameter("category_id", category_id);
        query.setParameter("archived", archived);
        query.executeUpdate();
    }
    
    @Override
    public void removeBeer(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Beer beer = (Beer) session.load(Beer.class, new Integer(id));
        if(null != beer){
            session.delete(beer);
        }
        logger.info("Beer deleted successfully, Beer details="+beer);
    }
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @param sessionFactory
     *            the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Beer> getBeerLstHaveNotTried(String username) {
        return (List<Beer>) getSessionFactory()
                .getCurrentSession()
                .createQuery("from Beer where id NOT IN (SELECT beer.id FROM Passport WHERE users.username=?)")
                .setParameter(0, username).list();
    }
    
}
