package org.example.user.dao;

public class DaoFactory {
    public UserDAO userDAO(){
        ConnectionMaker connectionMaker = new SimpleConnectionMaker();
        UserDAO userDAO = new UserDAO(connectionMaker);
        return userDAO;
    }
}
