package com.throne.travel.service.impl;

import com.throne.travel.dao.UserDao;
import com.throne.travel.dao.impl.UserDaoImpl;
import com.throne.travel.domain.User;
import com.throne.travel.service.UserService;
import com.throne.travel.util.MailUtils;
import com.throne.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    public boolean verifyUserActivateCode(String userid, String uuid) {
        String redisCode = dao.getActivateCode(userid);
        return uuid.equals(redisCode);
    }

    @Override
    public void updateActivateStatus(String username, String newInfo) {
        dao.updateUserInfoByName(username, newInfo);
    }

    @Override
    public User login(User u) {
        return dao.login(u);
    }

    @Override
    public boolean register(User user) {
        String username = user.getUsername();
        User u = dao.findUserByName(username);
        if (u != null) {
            return false;
        } else {
            // 用户信息入库
            dao.saveUser(user);

            // 设置激活码
            String uuid = UuidUtil.getUuid();
            dao.setActivateCode(username, uuid);
            // 发送邮件
            MailUtils.sendMail(
                    user.getEmail(),
                    "请点击链接完成激活: http://127.0.0.1:8080/travel/user/activateUser?username=" + user.getUsername() + "&code=" + uuid + "\n邮件30分钟内有效",
                    "请激活您的账号"
                    );
            return true;
        }
    }
}

