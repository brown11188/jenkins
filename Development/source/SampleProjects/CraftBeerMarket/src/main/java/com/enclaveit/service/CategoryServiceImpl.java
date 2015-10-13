package com.enclaveit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enclaveit.dao.CategoryDao;
import com.enclaveit.model.Category;

/**
 * @author Ace
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDAO;

    public void setCategoryDAO(CategoryDao categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    @Transactional
    public Boolean addCategory(Category category) {
        return this.categoryDAO.addCategory(category);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        this.categoryDAO.updateCategory(category);
    }

    @Override
    @Transactional
    public List<Category> listCategory(Integer offset, Integer maxResults) {
        return this.categoryDAO.listCategory(offset, maxResults);
    }

    @Override
    @Transactional
    public Category getCategoryById(int id) {
        return this.categoryDAO.getCategoryById(id);
    }

    @Override
    @Transactional
    public void removeCategory(int id) {
        this.categoryDAO.removeCategory(id);
    }
    
    @Override
    public Long count(){
        return this.categoryDAO.count();
    }

}
