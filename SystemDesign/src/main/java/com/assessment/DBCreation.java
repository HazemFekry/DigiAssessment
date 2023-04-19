package com.assessment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreation {
    final static String dBName = "system_design";

    public static void createDBIfNotExist() {
        Logger logger = LoggerFactory.getLogger(DBCreation.class);
        Connection connection = null;
        Statement statement = null;
        try {
            logger.debug("Creating database if not exist...");
            connection = DriverManager.getConnection("jdbc:postgresql://host.docker.internal:5432/", "hazem", "password");
            statement = connection.createStatement();
            statement.executeQuery("SELECT count(*) FROM pg_database WHERE datname = '"+dBName+"'");
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count <= 0) {
                statement.executeUpdate("CREATE DATABASE " + dBName);
                logger.debug("Database created.");
                statement.execute("insert into permission_groups values(1,'admin')");
                statement.execute("insert into permission_groups values(2,'notAdmin')");
                statement.execute("insert into permission values(1,0,'edit@mail.com',1)");
                statement.execute("insert into permission values(2,1,'view@mail.com',1)");
                statement.execute("insert into permission values(3,1,'notAdmin@mail.com',2)");
                logger.debug("basic data inserted.");
            } else {
                logger.debug("Database already exist.");
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }
    }
}
