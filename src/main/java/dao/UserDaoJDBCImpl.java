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
            user.setPassword(resultSet.getString("password"));
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
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getRole());
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
    public void updateUser(Long id, Long age, String name, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update users set age = ?, name = ?, password = ? where id = ? ");
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, password);
        preparedStatement.setLong(1, age);
        preparedStatement.setLong(4, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public boolean userIsExist(String name, String password) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from users where name = '" + name + "' and password = '" + password + "'");
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        User user = new User(resultSet.getLong(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        user.setId(resultSet.getLong(1));
        if(user != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isAdmin(String name, String password) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from users where name = '" + name + "' and password = '" + password + "'");
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        User user = new User(resultSet.getLong(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        user.setId(resultSet.getLong(1));
        if (user.getRole().equals("admin")){
            return true;
        }else {return false;}
    }

    @Override
    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table if not exists users (id bigint not null auto_increment, age bigint, name varchar(256), password varchar(256), role varchar (256), primary key (id))");
        statement.close();
    }

    @Override
    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }
}
