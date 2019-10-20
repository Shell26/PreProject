package dao;

import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDAO {
    private Connection connection;

    public UserDaoJDBCImpl() {
        this.connection = DBHelper.getConnection();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users");
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setAge(resultSet.getLong("age"));
            user.setName(resultSet.getString("name"));
            user.setRole(resultSet.getString("role"));
            user.setSecondName(resultSet.getString("secondName"));
            list.add(user);
        }
        resultSet.close();
        statement.close();
        return list;
    }

    @Override
    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (0, ?, ?, ?, ?)");
        preparedStatement.setLong(1, user.getAge());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(4, user.getSecondName());
        preparedStatement.setString(3, user.getRole());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }


    @Override
    public User getUserById(Long id) throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("select * from users where id = '" + id + "'");
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        User user = new User(resultSet.getLong(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        user.setId(resultSet.getLong(1));
        resultSet.close();
        statement.close();
        return user;
    }

        @Override
        public void deleteUser(Long id) throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ? ");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

    @Override
    public void updateUser(Long id, Long age, String name, String secondName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update users set age = ?, name = ?, secondName = ? where id = ? ");
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, secondName);
        preparedStatement.setLong(1, age);
        preparedStatement.setLong(4, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table if not exists users (id bigint not null auto_increment, age bigint, name varchar(256), secondName varchar(256) unique, primary key (id))");
        statement.close();
    }

    @Override
    public boolean isAdmin(String name, String secondName) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * form users where name = ? and secondName = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, secondName);
        ResultSet resultSet = preparedStatement.getResultSet();
        resultSet.next();
        if (resultSet.getString(4) == "user"){
            return false;
        }else {return true;}
    }

    @Override
    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}
