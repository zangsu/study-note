package org.example;

import org.example.user.dao.DaoFactory;
import org.example.user.dao.UserDAO;
import org.example.user.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

public class UserDAOTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //newDaoEqualsTest();
        //getDaoEqualsTest();
        daoLogicTest();


    }

    private static void daoLogicTest() throws ClassNotFoundException, SQLException {
        //ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
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

   /* public static  void newDaoEqualsTest(){

        DaoFactory factory = new DaoFactory();
        UserDAO dao1 = factory.userDAO();
        UserDAO dao2 = factory.userDAO();

        System.out.println(dao1);
        System.out.println(dao2);

    }

    public static void getDaoEqualsTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDAO dao1 = context.getBean("userDAO", UserDAO.class);
        UserDAO dao2 = context.getBean("userDAO", UserDAO.class);

        System.out.println(dao1);
        System.out.println(dao2);
    }*/
}
