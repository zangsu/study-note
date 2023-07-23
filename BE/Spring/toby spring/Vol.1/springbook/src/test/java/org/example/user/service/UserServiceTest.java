package org.example.user.service;

import org.assertj.core.api.Assertions;
import org.example.user.dao.UserDAO;
import org.example.user.domain.Level;
import org.example.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserDAO userDAO;

    @BeforeEach
    public void setUp(){
        userDAO.deleteAll();

        userDAO.add(new User("1toby", "이일민", "toby", Level.BASIC, 49, 0));
        userDAO.add(new User("2swg0605", "손원준", "swg_pw", Level.BASIC, 50, 0));
        userDAO.add(new User("3holyeye", "김영한", "holyeye", Level.SILVER, 55, 29));
        userDAO.add(new User("4moonGD", "문경덕", "moon_pw", Level.SILVER, 60, 39));
        userDAO.add(new User("5zangsu", "장혁수", "zangsu", Level.GOLD, 100, 40));
    }

    @Test
    public void bean() throws Exception{
        //given
        Assertions.assertThat(userService).isNotNull();
        //when

        //then
    }

    @Test
    public void upgradeLevels() throws Exception{
        //given
        List<User> users = userDAO.getAll();

        //when
        userService.upgradeLevels();

        //then
        checkLevel(users.get(0), Level.BASIC);
        checkLevel(users.get(1), Level.SILVER);
        checkLevel(users.get(2), Level.SILVER);
        checkLevel(users.get(3), Level.GOLD);
        checkLevel(users.get(4), Level.GOLD);

    }

    private void checkLevel(User user, Level expectedLevel){
        User userUpdate = userService.userDAO.get(user.getId());
        Assertions.assertThat(userUpdate.getLevel()).isEqualTo(expectedLevel);
    }
}