package db;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Properties;

public class MySQL {
    private static String DB_URL = "jdbc:mysql://localhost:3306/FP_DB";
    private static String USER_NAME = "fp";
    private static String PASSWORD = "fpUser@123";

    private static final Logger logger = Logger.getLogger(MySQL.class);
    private static Connection connection = null;

    public static boolean login(String name) {
        boolean result = false;
        Connection connection = getConnection();
        if(connection != null) {
            try {
                String sql = "select * from user where name = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    result = true;
                }
            } catch (SQLException e) {
                logger.error("Login failed");
                e.printStackTrace();
            }
        } else {
            logger.error("Could not connect to DB");
        }

        return result;
    }

    private static Connection getConnection() {
        if(connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                //connection = DriverManager.getConnection(dbURL, userName, password);
                Properties info = new Properties();
                info.setProperty("characterEncoding", "utf8");
                info.setProperty("user", USER_NAME);
                info.setProperty("password", PASSWORD);
                connection = DriverManager.getConnection(DB_URL, info);
                logger.info("Connect DB successfully");
            } catch (Exception ex) {
                logger.error("Connect DB failed");
            }
        }
        return connection;
    }
}