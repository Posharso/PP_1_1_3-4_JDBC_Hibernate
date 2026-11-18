
    package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class Util {
        private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";


        private static final String USERNAME = "root";
        private static final String PASSWORD = "123";
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

        public static Connection getConnection() throws SQLException {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e) {
                throw new SQLException("JDBC-драйвер MySQL не найден", e);
            }
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
    }


