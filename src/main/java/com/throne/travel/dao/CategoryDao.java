package com.throne.travel.dao;

import com.throne.travel.domain.Category;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

public interface CategoryDao {
    public List<Category> findAllCategories();

    public Set<Tuple> findAllCategoriesInRedis();

    public void setCategoriesToRedis(Category c);
}
