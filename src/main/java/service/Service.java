package service;

import dao.UserDAO;
import exeption.DBExeption;
import model.User;
import util.BDConnection;

import java.sql.*;
import java.sql.SQLException;
import java.util.List;

public class Service {
    public Service() {

    }

    public void addUser(User user) {
        try {
            new BDConnection().getUserDAO().addUser(user);
//            user.setId(getUserDAO().userId(user));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        try {
            return new BDConnection().getUserDAO().getAllUsers();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteUser(User user) {
        try {
            new BDConnection().getUserDAO().deleteUser(user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(User user, String name, String secondName, Long age) {
        try {
            new BDConnection().getUserDAO().updateUser(user, name, secondName, age);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createT() {
        UserDAO dao = new BDConnection().getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cleanUp() {
        UserDAO dao = new BDConnection().getUserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}




