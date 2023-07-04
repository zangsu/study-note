package org.example.user;

import org.example.user.dao.UserDAO;
import org.example.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;


import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
public class UserDAOTest {

    @Test
    public void addAndGet() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDAO dao = context.getBean("userDAO", UserDAO.class);

        User user1 = new User("toby","이일민","toby");
        User user2 = new User("holyeye", "김영한", "holyeye");
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);


        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User userGet1 = dao.get(user1.getId());

        assertThat(user1.getName()).isEqualTo(userGet1.getName());
        assertThat(user1.getPassword()).isEqualTo(userGet1.getPassword());

        User userGet2 = dao.get(user2.getId());

        assertThat(user2.getName()).isEqualTo(userGet2.getName());
        assertThat(user2.getPassword()).isEqualTo(userGet2.getPassword());
    }

    @Test
    public void count() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDAO dao = context.getBean("userDAO", UserDAO.class);

        User user1 = new User("toby", "이일민", "toby");
        User user2 = new User("holyeye", "김영한", "holyeye");
        User user3 = new User("zangsu", "장혁수", "zangsu");

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);
    }

    @Test
    public void getUserFailure() throws SQLException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        UserDAO dao = context.getBean("userDAO", UserDAO.class);

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        Assertions.assertThrows(
                EmptyResultDataAccessException.class,
                ()->{
                    dao.get("unknownId");
                }
        );

    }
}
