package com.throne.travel.dao;

import com.throne.travel.domain.User;

public interface UserDao {
    public User findUserByName(String username);

    public void saveUser(User user);

    void setActivateCode(String username, String uuid);

    String getActivateCode(String userid);

    void updateUserInfoByName(String username, String newInfo);

    User login(User u);
}
