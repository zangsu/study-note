package org.example.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
    @Bean
    public UserDAOJdbc userDAO(){
        UserDAOJdbc userDAO = new UserDAOJdbc();
        userDAO.setDataSource(dataSource());
        return userDAO;
    }

   /* @Bean
    public ConnectionMaker connectionMaker(){
        return new SimpleConnectionMaker();

    }*/

    @Bean
    public DataSource dataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost/tobyspring");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

}
