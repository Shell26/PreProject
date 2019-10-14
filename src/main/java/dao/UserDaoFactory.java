package dao;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class UserDaoFactory implements DaoFactory {

    static UserDAO getUserDaoJDBC() {
        return new UserDaoJDBCImpl();
    }

    static UserDAO getUserDaoHibernate() {
        return new UserDaoJDBCImpl();
    }

    public static UserDAO getUserDao(String type){
            if (type.equalsIgnoreCase("JDBC")) {
                return getUserDaoJDBC();
            } else if (type.equalsIgnoreCase("Hibernate")) {
                return getUserDaoHibernate();
            } else {
                return null;
            }
    }
}
