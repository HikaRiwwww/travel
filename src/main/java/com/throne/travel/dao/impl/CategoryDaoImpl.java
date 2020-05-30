package com.throne.travel.dao.impl;

import com.throne.travel.dao.CategoryDao;
import com.throne.travel.domain.Category;
import com.throne.travel.util.JDBCUtils;
import com.throne.travel.util.JedisUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.List;
import java.util.Set;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    private Jedis jedis = JedisUtil.getJedis();

    @Override
    public List<Category> findAllCategories(){
        String sql = "SELECT * FROM tab_category order by cid asc";
        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }


    @Override
    public Set<Tuple> findAllCategoriesInRedis(){
        return jedis.zrangeWithScores("categories", 0, -1);
    }

    @Override
    public void setCategoriesToRedis(Category c){
        jedis.zadd("categories", c.getCid(), c.getCname());
        jedis.expire("categories", 60*30);
    }
}
