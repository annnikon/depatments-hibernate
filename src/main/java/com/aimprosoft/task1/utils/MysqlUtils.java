package com.aimprosoft.task1.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MysqlUtils {


    private static String URL;
    private static String USER;
    private static String PASS;

    static {
        try {
            Properties prop = new Properties();
            InputStream inputStream = MysqlUtils.class.getClassLoader().getResourceAsStream("db.properties");
            prop.load(inputStream);
            String driver = prop.getProperty("driver");
            URL = prop.getProperty("url");
            USER = prop.getProperty("user");
            PASS = prop.getProperty("password");
            Class.forName(driver);
        } catch (IOException e) {
            System.out.println("no db.properties file");

        } catch (ClassNotFoundException e) {
            System.out.println("no jar file for mysql driver");
        }


    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }



}
