package util;

import dao.UserDAO;
import dao.UserDaoImplement;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnection {

    public BDConnection(){

    }

    public static UserDaoImplement getUserDAO(){
        return new UserDaoImplement(getMysqlConnection());
    }

    public static Connection getMysqlConnection(){
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
}
