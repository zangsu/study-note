package org.example.user.service;

import org.example.user.dao.UserDAO;
import org.example.user.domain.Level;
import org.example.user.domain.User;

import java.util.List;

public class UserService {
    UserDAO userDAO;

    static final int REQ_LOGIN = 50;
    static final int REQ_RECOMMEND = 30;

    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public void upgradeLevels(){
        List<User> users = userDAO.getAll();

        for (User user : users) {

            Boolean changed = null;
            if (user.getLevel() == Level.BASIC && user.getLogin() >= REQ_LOGIN) {
                user.setLevel(Level.SILVER);
                changed = true;
            } else if (user.getLevel() == Level.SILVER && user.getRecommend() >= REQ_RECOMMEND) {
                user.setLevel(Level.GOLD);
                changed = true;
            }else
                changed = false;

            if(changed)
                userDAO.update(user);
        }
    }
}
