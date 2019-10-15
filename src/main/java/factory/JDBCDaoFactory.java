package factory;


import dao.UserDAO;
import dao.UserDaoJDBCImpl;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public UserDAO getDao() {
        return new UserDaoJDBCImpl();
    }
}

