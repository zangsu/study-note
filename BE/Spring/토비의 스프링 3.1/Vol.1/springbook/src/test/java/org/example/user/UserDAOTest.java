package org.example.user;

import org.example.user.dao.UserDAO;
import org.example.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

public class UserDAOTest {

    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDAO dao = context.getBean("userDAO", UserDAO.class);

        User user = new User();
        user.setId("toby");
        user.setName("이일민");
        user.setPassword("toby");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());


        if (!user.getName().equals(user2.getName())) {
            System.out.println("테스트 실패 (name)");
        } else if (!user.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패 (password)");
        } else {
            System.out.println("조회 테스트 성공");
        }
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
