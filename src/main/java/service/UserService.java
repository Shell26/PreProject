package service;

import dao.UserDAO;
import dao.UserDaoImplement;
import model.User;
import util.BDConnection;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    public UserService() {

    }

    private UserDaoImplement UserDAO(){
        return BDConnection.getUserDAO();
    }

    public void addUser(User user) {
        try {
            UserDAO().addUser(user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUserById(Long id) {
        try {
            return UserDAO().getUserById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return UserDAO().getAllUsers();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteUser(Long id) {
        try {
            UserDAO().deleteUser(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(Long id, String name, String secondName, Long age) {
        try {
            UserDAO().updateUser(id, name, secondName, age);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createT() {
        try {
            UserDAO().createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cleanUp() {
        try {
            UserDAO().dropTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}




