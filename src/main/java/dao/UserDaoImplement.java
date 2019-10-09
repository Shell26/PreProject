package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImplement implements UserDAO {

    private Connection connection;

    public UserDaoImplement(Connection connection) {
        this.connection = connection;
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
            user.setName(resultSet.getString("name"));
            user.setSecondName(resultSet.getString("secondName"));
            user.setAge(resultSet.getLong("age"));
            list.add(user);
        }
        resultSet.close();
        statement.close();
        return list;
    }

    @Override
    public void addUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (0, ?, ?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSecondName());
        preparedStatement.setLong(3, user.getAge());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    // Упростил, перенеся строки в конструктор юзера (строка 54).
    @Override
    public User getUserById(Long id) throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("select * from users where id = '" + id + "'");
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getLong(4));
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
    public void updateUser(Long id, String name, String secondName, Long age) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update users set name = ?, secondName = ?, age = ? where id = ? ");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, secondName);
        preparedStatement.setLong(3, age);
        preparedStatement.setLong(4, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table if not exists users (id bigint not null auto_increment, name varchar(256), secondName varchar(256), age bigint, primary key (id))");
        statement.close();
    }

    @Override
    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}
