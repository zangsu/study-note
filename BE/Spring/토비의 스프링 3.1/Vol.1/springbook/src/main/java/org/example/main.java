package org.example;

import org.example.user.dao.UserDAO;
import org.example.user.domain.User;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDAO dao = new UserDAO();

        User user = new User();
        user.setId("toby");
        user.setName("이일민");
        user.setPassword("toby");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + " 조회 성공");
    }
}
