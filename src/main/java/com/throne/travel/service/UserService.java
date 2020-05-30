package com.throne.travel.service;

import com.throne.travel.domain.User;

public interface UserService {
    boolean register(User user);

    boolean verifyUserActivateCode(String userid, String uuid);

    void updateActivateStatus(String username, String newInfo);

    User login(User u);
}
