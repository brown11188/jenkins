package com.enclaveit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.enclaveit.model.Category;

/**
 * @author Ace
 *
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {
    
    private static final Logger logger = LoggerFactory.getLogger(CategoryDaoImpl.class);
    
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
    
    @Override
    public Boolean addCategory(Category category) {
        if(this.isExistName(category.getName())){
            return true;
        }else{
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(category);
            logger.info("Category saved successfully, Category Details="+category);
            return false;
        }
    }

    @Override
    public void updateCategory(Category category) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(category);
        logger.info("Category updated successfully, Category Details="+category);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Category> listCategory(Integer offset, Integer maxResults) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Category> categoryList = session.createCriteria(Category.class).
                setFirstResult(offset!= null?offset:0).setMaxResults(maxResults!=null?maxResults:10).list();
        for(Category category : categoryList){
            logger.info("Category List::"+category);
        }
        return categoryList;
    }
    
    @Override
    public Long count(){
        return (Long) sessionFactory.openSession().createCriteria(Category.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
    
    @Override
    public Category getCategoryById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category category = (Category) session.load(Category.class, new Integer(id));
        logger.info("Category loaded successfully, Category details="+category);
        return category;
    }

    @Override
    public void removeCategory(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Category category = (Category) session.load(Category.class, new Integer(id));
        Query query = session.createQuery("delete from Beer where category_id = :id");
        query.setParameter("id", id);
         
        int result = query.executeUpdate();
         
        if (result >= 0) {
            if(null != category){
                session.delete(category);
            }
        }
        logger.info("Category deleted successfully, Category details="+category);
    }
    
   public Boolean isExistName(String name){
       Session session = this.sessionFactory.getCurrentSession();
       Query query=  session.createQuery("from Category where name= :name");
       query.setParameter("name", name);
       Category category = (Category) query.uniqueResult();
       if(category!=null){
          return true;
       }else{
         return false;
       } 
   }
}
