package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO{
    public List<User> getAllUsers() throws SQLException;
    public void addUser(User user) throws SQLException;
    public User getUserById(Long id) throws SQLException;
    public void deleteUser(Long id) throws SQLException;
    public void updateUser(Long id, Long age, String name, String password) throws SQLException;
    public boolean isAdmin(String name, String password) throws SQLException;
    public boolean userIsExist(String name, String password) throws SQLException;
    public void createTable() throws SQLException;
    public void dropTable() throws SQLException;
}
