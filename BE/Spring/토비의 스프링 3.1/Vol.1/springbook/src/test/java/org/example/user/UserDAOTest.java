package org.example.user;

import org.example.user.dao.UserDAO;
import org.example.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
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

        User user2 = dao.get(user.getId());

        assertThat(user2.getName()).isEqualTo(user.getName());
        assertThat(user2.getPassword()).isEqualTo(user.getPassword());
    }
}
