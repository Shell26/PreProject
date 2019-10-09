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

    private UserDaoImplement BDConnection(){
        return new BDConnection().getUserDAO();
    }

    public void addUser(User user) {
        try {
            BDConnection().addUser(user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUserById(Long id) {
        try {
            return BDConnection().getUserById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return BDConnection().getAllUsers();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteUser(Long id) {
        try {
            BDConnection().deleteUser(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(Long id, String name, String secondName, Long age) {
        try {
            BDConnection().updateUser(id, name, secondName, age);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createT() {
        try {
            BDConnection().createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cleanUp() {
        try {
            BDConnection().dropTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}




