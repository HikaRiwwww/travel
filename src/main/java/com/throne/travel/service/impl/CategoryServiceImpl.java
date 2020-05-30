package com.throne.travel.service.impl;

import com.throne.travel.dao.CategoryDao;
import com.throne.travel.dao.impl.CategoryDaoImpl;
import com.throne.travel.domain.Category;
import com.throne.travel.service.CategoryService;

import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao = new CategoryDaoImpl();

    @Override
    public List<Category> findAllCategories() {
        // 先从redis查
        List<Category> categories;
        Set<Tuple> tuples = dao.findAllCategoriesInRedis();
        if (tuples == null || tuples.size() == 0) {
            categories = dao.findAllCategories();
            for (Category category : categories) {
                dao.setCategoriesToRedis(category);
            }
        } else {
            categories = new ArrayList<Category>();
            for (Tuple tuple : tuples) {
                Category c = new Category();
                c.setCname(tuple.getElement());
                c.setCid((int) tuple.getScore());
                categories.add(c);
            }
        }
        return categories;
    }
}
