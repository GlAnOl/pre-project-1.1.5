package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        try(Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE User (\n" +
                    "    id INT AUTO_INCREMENT,\n" +
                    "    name VARCHAR(45) NOT NULL,\n" +
                    "    lastName VARCHAR(45) NOT NULL,\n" +
                    "    age TINYINT NOT NULL,\n" +
                    "    PRIMARY KEY (id)\n" +
                    ")").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
        }

    }

    @Override
    public void dropUsersTable() {

        try(Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("drop table user").executeUpdate();
            session.getTransaction().commit();
        }catch (Exception e){
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = getSessionFactory().openSession()){
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try(Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

           list = session.createQuery("from User").getResultList();

            session.getTransaction().commit();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {

        try(Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        }

    }
}
