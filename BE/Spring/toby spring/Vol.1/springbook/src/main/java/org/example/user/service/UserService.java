package org.example.user.service;

import org.example.user.dao.UserDAO;

public class UserService {
    UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }
}
