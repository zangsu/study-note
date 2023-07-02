package org.example.user.dao;

import lombok.NoArgsConstructor;
import org.example.user.domain.User;

import java.sql.*;

@NoArgsConstructor
public class UserDAO {

    private ConnectionMaker connectionMaker;
    private Connection c;
    private User user;

    public UserDAO(ConnectionMaker connectionMaker) {

        this.connectionMaker = connectionMaker;
    }

    public void setConnectionMaker(ConnectionMaker connectionMaker){
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {

        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values (?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {

        this.c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id=?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        this.user = new User();
        this.user.setId(rs.getString(   "id"));
        this.user.setName(rs.getString("name"));
        this.user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

}
