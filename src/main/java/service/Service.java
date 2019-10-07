package service;

import dao.UserDAO;
import exeption.DBExeption;
import model.User;

import java.sql.*;
import java.sql.SQLException;
import java.util.List;

public class Service {
    public Service(){

    }

    public void addUser(User user){
        try {
            getUserDAO().addUser(user);
//            user.setId(getUserDAO().userId(user));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers(){
        try{
            return getUserDAO().getAllUsers();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteUser(User user){
        try{
            getUserDAO().deleteUser(user);
        }catch (SQLException e){
        System.out.println(e.getMessage());
        }
    }

    public void updateUser (User user, String name, String secondName, Long age){
        try{
            getUserDAO().updateUser(user, name, secondName, age);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void createT(){
        UserDAO dao = getUserDAO();
        try {
            dao.createTable();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void cleanUp() {
        UserDAO dao = getUserDAO();
        try {
            dao.dropTable();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private static Connection getMysqlConnection(){
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("test1?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection;
            connection = DriverManager.getConnection(url.toString());
            return connection;
        }catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO(){
        return new UserDAO(getMysqlConnection());
    }
}
