package org.example.user.dao;

import com.mysql.cj.jdbc.exceptions.ConnectionFeatureNotAvailableException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
    @Bean
    public UserDAO userDAO(){
        UserDAO userDAO = new UserDAO();
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
