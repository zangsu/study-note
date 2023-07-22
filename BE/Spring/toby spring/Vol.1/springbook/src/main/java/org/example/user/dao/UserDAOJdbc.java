package org.example.user.dao;

import lombok.NoArgsConstructor;
import org.example.user.domain.Level;
import org.example.user.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@NoArgsConstructor
public class UserDAOJdbc implements UserDAO {
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setLevel(Level.valueOf(rs.getInt("level")));
            user.setLogin(rs.getInt("login"));
            user.setRecommend(rs.getInt("recommend"));

            return user;
        }
    };

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void add(final User user){
        this.jdbcTemplate.update("insert into users(id, name, password, level, login, recommend) values (?,?,?,?,?,?)",
                user.getId(), user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend());
    }

    public User get(String id){
        return this.jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, userMapper);
    }

    public void deleteAll(){
        this.jdbcTemplate.update("delete from users");
    }

    public int getCount(){
        return this.jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public void update(User user) {
        this.jdbcTemplate.update(
                "update users set name = ?, password = ?, level = ?, login = ?, recommend = ? where id = ?"
                , user.getName(), user.getPassword(), user.getLevel().intValue(), user.getLogin(), user.getRecommend(), user.getId()
        );
    }

    public List<User> getAll(){
        return this.jdbcTemplate.query("select * from users order by id", userMapper);
    }
}