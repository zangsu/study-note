package org.example.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JdbcContext {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException{
        Connection c = null;
        PreparedStatement ps = null;

        try{
            c = dataSource.getConnection();

            ps = stmt.makePreparedStatement(c);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }finally {
            if(ps != null){
                try{
                    ps.close();
                }catch (SQLException e){}
            }
            if(c != null){
                try{
                    c.close();
                } catch (SQLException e) {}
            }
        }
    }
    public void executeSql(final String query, String... parameter) throws SQLException{
        workWithStatementStrategy(
            new StatementStrategy() {
                @Override
                public PreparedStatement makePreparedStatement(Connection c) throws SQLException {

                    PreparedStatement ps = c.prepareStatement(query);
                    for(int i = 1; i<= parameter.length; i++){
                        ps.setString(i, parameter[i-1]);
                    }

                    return ps;
                }
            }
        );
    }

    public void executeSql(final String query) throws SQLException{
        workWithStatementStrategy(
                new StatementStrategy() {
                    @Override
                    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
                        return c.prepareStatement(query);
                    }
                }
        );
    }


}
