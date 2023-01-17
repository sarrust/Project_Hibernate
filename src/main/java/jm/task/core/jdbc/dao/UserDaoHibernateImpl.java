package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getConnection();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE User" +
                    " (id int not null auto_increment, name VARCHAR(50), " +
                    "lastname VARCHAR(50), " +
                    "age int, " +
                    "PRIMARY KEY (id))").executeUpdate();
            transaction.commit();
            System.out.println("Таблица создана!");
        } catch (Exception e) {
            System.out.println("Ошибка! Не удалось создать таблицу.");
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE User").executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена!");
        } catch (Exception e) {
            System.out.println("Ошибка! Не удалось удалить таблицу.");
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));

            transaction.commit();
            System.out.println("Пользователь добавлен!");
        } catch (Exception e) {
            System.out.println("Ошибка! Не удалось добавить пользователя.");
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("Пользователь удален!");
        } catch (Exception e) {
            System.out.println("Ошибка! Не удалось добавить пользователя.");
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            list = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка! Не удалось прочитать таблицу.");
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            final List<User> list = session.createCriteria(User.class).list();

            for(Object a : list) {
                session.delete(a);
            }
            transaction.commit();
            System.out.println("Таблица полностью очищена!");
        } catch (Exception e) {
            System.out.println("Ошибка! Не удалось очистить таблицу.");
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }
}
