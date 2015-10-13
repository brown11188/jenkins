package com.enclaveit.dao;

import java.util.List;

import com.enclaveit.model.Category;

/**
 * @author Ace
 *
 */
public interface CategoryDao {

    public Boolean addCategory(Category category);

    public void updateCategory(Category category);

    public List<Category> listCategory(Integer offset, Integer maxResults);

    public Category getCategoryById(int id);

    public void removeCategory(int id);
    
    public Long count();
}
