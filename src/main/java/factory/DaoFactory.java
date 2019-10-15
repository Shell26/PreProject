package factory;

import dao.UserDAO;


public interface DaoFactory {

    UserDAO getDao();

    public static DaoFactory getUserDao(String type) {
        if (type.equalsIgnoreCase("JDBC")) {
            return new JDBCDaoFactory();
        }else if (type.equalsIgnoreCase("hibernate")){
            return new HiberDaoFactory();
        }else {
            return null;
        }
    }
}
