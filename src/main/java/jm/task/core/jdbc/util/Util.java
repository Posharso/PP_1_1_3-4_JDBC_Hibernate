package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }


    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                registryBuilder.applySetting("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                registryBuilder.applySetting("hibernate.connection.url", DB_URL);
                registryBuilder.applySetting("hibernate.connection.username", USERNAME);
                registryBuilder.applySetting("hibernate.connection.password", PASSWORD);
                registryBuilder.applySetting("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                registryBuilder.applySetting("hibernate.show_sql", "true");

                StandardServiceRegistry registry = registryBuilder.build();

                Metadata metadata = new MetadataSources(registry)
                        .addAnnotatedClass(User.class)
                        .getMetadataBuilder()
                        .build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Не удалось создать SessionFactory", e);
            }
        }
        return sessionFactory;
    }
}
