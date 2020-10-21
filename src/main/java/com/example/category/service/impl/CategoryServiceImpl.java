package com.example.category.service.impl;

import com.example.category.dao.CategoryDAO;
import com.example.category.dataobject.CategoryDO;
import com.example.category.model.Category;
import com.example.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    public Category add(Category category) {

        if (category == null) {
            return null;
        }
        CategoryDO categoryDO = new CategoryDO(category);

        int size = categoryDAO.insert(categoryDO);
        if (size < 1) {
            return null;
        }
        category.setId(categoryDO.getId());
        return category;
    }
}
