package service;

import factory.DaoFactory;
import dao.UserDAO;
import model.User;
import util.PropertyReader;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;


public class UserService {
    private static UserService instance;
    private static UserDAO userDAO;

    private UserService() throws FileNotFoundException {
//        userDAO  = new UserDaoJDBCImpl();
//        userDAO = new UserDaoHibernateImpl();
//        FileReader fr = new FileReader("C:/Users/socia/IdeaProjects/test1/src/property.txt");
//        Scanner scanner = new Scanner(fr);
//        String path = scanner.nextLine();
//        userDAO = UserDaoFactory.getUserDao(path);
    }


    public static UserService getInstance() throws IOException {
        if (instance == null){
            instance = new UserService();

        }
//        Properties properties = new Properties();
//        InputStream inputStream = null;
//        inputStream = new FileInputStream("C:/Users/socia/IdeaProjects/test1/src/main/resources/config.properties");
//        properties.load(inputStream);
//        String path = properties.getProperty("DB_TYPE");

//        FileReader fr = new FileReader("C:/Users/socia/IdeaProjects/test1/src/property.txt");
//        Scanner scanner = new Scanner(fr);
//        String path = scanner.nextLine();
        userDAO = DaoFactory.getUserDao(new PropertyReader().getPath()).getDao();
        return instance;
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

    public void updateUser(Long id, Long age, String name, String secondName) {
        try {
            userDAO.updateUser(id, age, name, secondName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isAdmin(String name, String secondName){
        try{
            return userDAO.isAdmin(name, secondName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean userIsExist(String name, String password){
        try{
            return userDAO.userIsExist(name, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
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




