package dao;

public interface DaoFactory {

    static UserDAO getUserDaoJDBC() {
        return null;
    }

    static UserDAO getUserDaoHibernate() {
        return null;
    }
}
