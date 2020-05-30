package com.throne.travel.dao.impl;

import com.throne.travel.dao.UserDao;
import com.throne.travel.domain.User;
import com.throne.travel.util.JDBCUtils;
import com.throne.travel.util.JedisUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    Jedis jedis = JedisUtil.getJedis();

    @Override
    public String getActivateCode(String userid) {
        return jedis.get(userid);
    }

    @Override
    public User findUserByName(String username) {
        String sql = "SELECT * FROM tab_user WHERE username = ?";
        User u = null;
        try{
            u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){}
        return u;
    }

    @Override
    public User login(User u) {
        String sql = "SELECT * FROM tab_user WHERE username = ? and password = ?";
        User user = null;
        try{
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), u.getUsername(), u.getPassword());
        }catch (Exception e){}
        return user;
    }

    @Override
    public void updateUserInfoByName(String username, String newInfo) {
        String sql = "UPDATE tab_user set status = ? where username = ?";
        template.update(sql, newInfo, username);
    }

    @Override
    public void setActivateCode(String username, String uuid) {
        jedis.set(username, uuid);
        jedis.expire(username, 60*60);

    }

    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO tab_user (username, password, name, birthday, sex, telephone, email) " +
                "values(?,?,?,?,?,?,?)";
        template.update(
                sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail()
                );
    }
}

