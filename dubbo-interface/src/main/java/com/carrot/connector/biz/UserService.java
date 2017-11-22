package com.carrot.connector.biz;

import com.carrot.connector.model.User;
public  interface UserService {
    User getUserByName(String name);
}
