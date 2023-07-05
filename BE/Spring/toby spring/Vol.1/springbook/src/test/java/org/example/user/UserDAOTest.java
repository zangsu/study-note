package org.example.user;

import org.example.user.dao.UserDAO;
import org.example.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
public class UserDAOTest {

    private UserDAO dao;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp(){
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDAO dao = context.getBean("userDAO", UserDAO.class);
        this.user1 = new User("toby", "이일민", "toby");
        this.user2 = new User("holyeye", "김영한", "holyeye");
        this.user3 = new User("zangsu", "장혁수", "zangsu");

    }

    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDAO dao = context.getBean("userDAO", UserDAO.class);

        User user1 = new User("toby", "이일민", "toby");
        User user2 = new User("holyeye", "김영한", "holyeye");

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User userGet1 = dao.get(user1.getId());
        assertThat(userGet1.getName()).isEqualTo(user1.getName());
        assertThat(userGet1.getPassword()).isEqualTo(user1.getPassword());

        User userGet2 = dao.get(user2.getId());
        assertThat(userGet2.getName()).isEqualTo(user2.getName());
        assertThat(userGet2.getPassword()).isEqualTo(user2.getPassword());
    }

    @Test
    public void count() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDAO dao = context.getBean("userDAO", UserDAO.class);


        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);
    }

}
