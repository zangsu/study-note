package org.example;

import org.example.user.dao.ConnectionMaker;
import org.example.user.dao.DaoFactory;
import org.example.user.dao.SimpleConnectionMaker;
import org.example.user.dao.UserDAO;
import org.example.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UserDAOTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDAO dao = context.getBean("userDAO", UserDAO.class);

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
