package org.example.user.dao;

import com.mysql.cj.jdbc.exceptions.ConnectionFeatureNotAvailableException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDAO userDAO(){
        return new UserDAO(connectionMaker());
    }

    @Bean
    public ConnectionMaker connectionMaker(){
        return new SimpleConnectionMaker();

    }
}
