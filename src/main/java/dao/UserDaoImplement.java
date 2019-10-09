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

    @Override
    public User getUserById(Long id) throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute("select * from users where id = '" + id + "'");
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        User user = new User();
        user.setId(resultSet.getLong(1));
        user.setName(resultSet.getString(2));
        user.setSecondName(resultSet.getString(3));
        user.setAge(resultSet.getLong(4));
        resultSet.close();
        statement.close();
        return user;
    }
//    @Override
//    public void addUser(User user) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?)");
//        preparedStatement.setLong(1, user.getId());
//        preparedStatement.setString(2, user.getName());
//        preparedStatement.setString(3, user.getSecondName());
//        preparedStatement.setLong(4, user.getAge());
//        preparedStatement.executeUpdate();
//        preparedStatement.close();
//    }

//    @Override
//    public long userId(User user) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where name = ? and secondName = ? and age = ?");
//        preparedStatement.setString(1, user.getName());
//        preparedStatement.setString(2, user.getSecondName());
//        preparedStatement.setLong(3, user.getAge());
//        ResultSet resultSet = preparedStatement.getResultSet();
//        Long id = resultSet.getLong(1);
//        resultSet.close();
//        preparedStatement.close();
//        return id;
//    }
//                        почему не работает вариант выше?
        @Override
        public long userSetId(User user) throws SQLException {
            Statement statement = connection.createStatement();
            statement.execute("select * from users where name = '" + user.getName() + "' and secondName = '" + user.getSecondName() + "' and age = '" + user.getAge() + "'");
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            Long id = resultSet.getLong(1);
            resultSet.close();
            statement.close();
            return id;
        }

//    @Override
//    public void deleteUser(User user) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("delete from users where name = ? and secondName = ? and age = ?");
//        preparedStatement.setString(1, user.getName());
//        preparedStatement.setString(2, user.getSecondName());
//        preparedStatement.setLong(3, user.getAge());
//        preparedStatement.executeUpdate();
//        preparedStatement.close();
//
//    }
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
