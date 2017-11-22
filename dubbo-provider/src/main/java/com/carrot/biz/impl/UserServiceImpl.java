package com.carrot.biz.impl;

import com.carrot.connector.biz.UserService;
import com.carrot.connector.model.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Override
    public User getUserByName(String name) {
        User user = new User();
        user.setAge(20);
        user.setName(name);
        return user;
    }
}
