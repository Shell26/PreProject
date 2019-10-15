package factory;


import dao.UserDAO;
import dao.UserDaoHibernateImpl;

public class HiberDaoFactory extends DaoFactory {

    @Override
    public UserDAO getDao() {
        return new UserDaoHibernateImpl();
    }
}
