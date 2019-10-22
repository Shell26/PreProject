package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDAO{
    private DBHelper dbHelper = DBHelper.getInstance();
    private Session session = dbHelper.getSessionFactory().openSession();

    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> allUsers = session.createQuery("from User").list();
        transaction.commit();
        session.close();
        return allUsers;
    }


    public void addUser(User user) {
        session.save(user);
        session.close();
    }


    public User getUserById(Long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User where id =:i");
        query.setParameter("i", id);
        List list = query.list();
        User user = (User) list.get(0);
        transaction.commit();
        session.close();
        return user;
    }

    public boolean userIsExist(String name, String password) throws SQLException {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where name =:n and password =:p");
        query.setParameter("n", name);
        query.setParameter("p", password);
        List list = query.list();
        transaction.commit();
        session.close();
        return list.isEmpty();
    }

    public void deleteUser(Long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete User u where u.id = :i");
        query.setParameter("i", id);
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
    }


    public void updateUser(Long id, Long age, String name, String password) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User u set u.name = :newName, u.age = :newAge, u.password = :newSecond where u.id = :i");
        query.setParameter("newName", name);
        query.setParameter("newSecond", password);
        query.setParameter("newAge", age);
        query.setParameter("i", id);
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public boolean isAdmin(String name, String password) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User where name = :nm, password =: sn");
        query.setParameter("nm", name);
        query.setParameter("sn", password);
        List list = query.list();
        User user = (User) list.get(0);
        transaction.commit();
        session.close();
        if (user.getRole() == "user"){
            return false;
        }else{return true;}
    }

    public void createTable(){ }

    public void dropTable(){
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from User");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }


}
