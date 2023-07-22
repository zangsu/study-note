package org.example.user.dao;

import org.example.user.domain.User;

import java.util.List;

public interface UserDAO {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
}
