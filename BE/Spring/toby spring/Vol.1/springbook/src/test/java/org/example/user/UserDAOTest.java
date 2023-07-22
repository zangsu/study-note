package org.example.user;

import org.example.user.dao.UserDAO;
import org.example.user.domain.Level;
import org.example.user.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


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

    @Autowired
    DataSource dataSource;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp(){

        this.user1 = new User("toby", "이일민", "toby", Level.BASIC, 1, 0);
        this.user2 = new User("holyeye", "김영한", "holyeye", Level.SILVER, 55, 10);
        this.user3 = new User("zangsu", "장혁수", "zangsu", Level.GOLD, 100, 40);

    }
    @Test
    public void addAndGet() throws SQLException {

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User userGet1 = dao.get(user1.getId());
        checkSameUser(user1, userGet1);

        User userGet2 = dao.get(user2.getId());
        checkSameUser(user2, userGet2);
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

    @Test
    public void update() throws Exception{
        //given
        dao.deleteAll();

        //when
        dao.add(user1);
        dao.add(user2);

        //then
        user1.setName("문경덕");
        user1.setPassword("moonGD");
        user1.setLevel(Level.GOLD);
        user1.setLogin(100);
        user1.setRecommend(999);

        dao.update(user1);

        User getUser1 = dao.get(user1.getId());
        checkSameUser(user1, getUser1);
        User getUser2 = dao.get(user2.getId());
        checkSameUser(user2, getUser2);
    }

    @Test
    public void duplicatedKey() throws Exception{
        //given
        dao.deleteAll();

        //when
        dao.add(user1);

        //then
        Assertions.assertThrows(DuplicateKeyException.class, () -> dao.add(user1));
    }

    @Test
    public void SqlExceptionTranslate() throws Exception{
        //given
        dao.deleteAll();

        //when
        //then
        try {
            dao.add(user1);
            dao.add(user1);
        } catch (DuplicateKeyException exception) {
            SQLException sqlException = (SQLException) exception.getRootCause();
            SQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator(this.dataSource);

            org.assertj.core.api.Assertions.assertThat(
                    translator.translate(null, null, sqlException))
                    .isInstanceOf(DuplicateKeyException.class);
        }
    }

    private void checkSameUser(User user1, User user2) {
        assertThat(user1.getId()).isEqualTo(user2.getId());
        assertThat(user1.getName()).isEqualTo(user2.getName());
        assertThat(user1.getPassword()).isEqualTo(user2.getPassword());
        assertThat(user1.getLevel()).isEqualTo(user2.getLevel());
        assertThat(user1.getLogin()).isEqualTo(user2.getLogin());
        assertThat(user1.getRecommend()).isEqualTo(user2.getRecommend());
    }
}
