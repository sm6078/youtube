package org.javaacademy.youtube;

import java.util.List;
import java.util.Properties;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.javaacademy.youtube.entity.Comment;
import org.javaacademy.youtube.entity.User;
import org.javaacademy.youtube.entity.Video;


public class Runner {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("hibernate.connection.url", "jdbc:postgresql://91.107.126.163:5433/youtube");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "postgres");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        properties.put(Environment.FORMAT_SQL, true);
        @Cleanup SessionFactory sessionFactory = new Configuration().addProperties(properties)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Comment.class)
                .addAnnotatedClass(Video.class)
                .buildSessionFactory();

        @Cleanup Session session = sessionFactory.openSession();
        //create new user
        //createUser(session, "hunter");
        //createUser(session, "line");
        //createUser(session, "ars");
        //createUser(session, "dad");

        //print User by id
        printUserById(session, 1);

        printAllUser(session);

    }

    private static void createUser(Session session, String name) {
        session.beginTransaction();
        User user = new User(name);
        session.persist(user);
        session.getTransaction().commit();
    }

    private static void printUserById(Session session, Integer id) {
        User user = session.get(User.class, id);
        System.out.println(user);
    }

    private static void printAllUser(Session session) {
        //HQL
        List<User> users = session.createQuery("from User where nickName like '%a%' ", User.class).list();
        users.forEach(System.out::println);
    }




}
