package org.example.user;

import org.example.user.dao.UserDAO;
import org.example.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
@DirtiesContext
public class UserDAOTest {
    @Autowired
    UserDAO dao;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp(){

        this.user1 = new User("toby", "이일민", "toby");
        this.user2 = new User("holyeye", "김영한", "holyeye");
        this.user3 = new User("zangsu", "장혁수", "zangsu");

    }
    @Test
    public void addAndGet() throws SQLException {

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
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        Assertions.assertThrows(
                EmptyResultDataAccessException.class,
                ()->{
                    dao.get("unknownId");
                }
        );
    }

    @Test
    public void getAll() throws SQLException {
        dao.deleteAll();

        dao.add(user1);

        List<User> list = dao.getAll();
        assertThat(list.size()).isEqualTo(1);
        checkSameUser(user1, list.get(0));

        dao.add(user2);
        list = dao.getAll();
        assertThat(list.size()).isEqualTo(2);
        checkSameUser(user1, list.get(1));
        checkSameUser(user2, list.get(0));


        dao.add(user3);
        list = dao.getAll();
        assertThat(list.size()).isEqualTo(3);
        checkSameUser(user1, list.get(1));
        checkSameUser(user2, list.get(0));
        checkSameUser(user3, list.get(2));
    }

    @Test
    public void getAllException() throws SQLException {
        dao.deleteAll();

        List<User> list = dao.getAll();
        assertThat(list.size()).isEqualTo(0);
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
    }
}
