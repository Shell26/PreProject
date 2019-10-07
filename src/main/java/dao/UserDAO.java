package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection){
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM users");
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()){
            User user = new User();
            user.setName(resultSet.getString("name"));
            user.setSecondName(resultSet.getString("secondName"));
            user.setAge(resultSet.getLong("age"));
            list.add(user);
        }
        resultSet.close();
        statement.close();
        return list;
    }

    public void addUser(User user) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (0, ?, ?, ?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSecondName());
        preparedStatement.setLong(3, user.getAge());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public long userId(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name = ? and secondName = ? and age = ?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSecondName());
        preparedStatement.setLong(3, user.getAge());
        ResultSet resultSet = preparedStatement.getResultSet();
        Long id = resultSet.getLong(1);
        resultSet.close();
        preparedStatement.close();
        return id;
    }

    public void deleteUser(User user) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("delete from users where name = ? and secondName = ? and age = ?");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSecondName());
        preparedStatement.setLong(3, user.getAge());
        preparedStatement.executeUpdate();
        preparedStatement.close();

    }

    public void updateUser(User user, String name, String secondName, Long age) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("update users set name = ?, secondName = ?, age = ? where name = ? and secondName = ? and age = ?");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, secondName);
        preparedStatement.setLong(3, age);
        preparedStatement.setString(4, user.getName());
        preparedStatement.setString(5, user.getSecondName());
        preparedStatement.setLong(6, user.getAge());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table if not exists users (id bigint not null auto_increment, name varchar(256), secondName varchar(256), age bigint, primary key (id))");
        statement.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}
