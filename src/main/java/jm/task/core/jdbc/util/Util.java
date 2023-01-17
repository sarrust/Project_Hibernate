package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static SessionFactory sessionFactory = null;
    public static SessionFactory getConnection() {
        try {
            Configuration configuration = new Configuration().addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.getCurrentSession();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
