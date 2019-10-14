package dao;

public class UserDaoFactory {

    public static UserDAO getUserDao(String type){
        if (type.equalsIgnoreCase("JDBC")){
            return new UserDaoJDBCImpl();
        }else if(type.equalsIgnoreCase("Hibernate")){
            return new UserDaoHibernateImpl();
        }else {
            return null;
        }
    }

}
