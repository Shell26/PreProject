package service;

import dao.UserDAO;
import dao.UserDaoImplHibernate;
import dao.UserDaoImplJDBC;
import model.User;
import org.hibernate.SessionFactory;
import util.BDConnection;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
//        userDAO  = new UserDaoImplJDBC();
        userDAO = new UserDaoImplHibernate();
    }


    public void addUser(User user) {
        try {
            userDAO.addUser(user);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public User getUserById(Long id) {
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteUser(Long id) {
        try {
            userDAO.deleteUser(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUser(Long id, String name, String secondName, Long age) {
        try {
            userDAO.updateUser(id, name, secondName, age);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTable() {
        try {
            userDAO.createTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cleanUp() {
        try {
            userDAO.dropTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}




